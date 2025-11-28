--Auto Genarated memberID, gender can be null
CREATE TABLE IF NOT EXISTS Member(
    memberID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20) NOT NULL,
    dob date NOT NULL,
    gender VARCHAR(20)
);

--contact - +1 (123) - 456 - 789 #20 chars
CREATE TABLE IF NOT EXISTS Contact(
    memberID INT NOT NULL,
    contact VARCHAR(20) NOT NULL,
    PRIMARY KEY(memberID, contact),
    FOREIGN KEY(memberID)
        REFERENCES Member(memberID)
        ON DELETE CASCADE
);

--can be null for weight(5,2) and body fat(4,2)
CREATE TABLE IF NOT EXISTS PersonalFitnessData(
    goalID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    target_weight NUMERIC(5,2),
    target_body_fat NUMERIC(4,2),
    memberID INT NOT NULL,
    FOREIGN KEY(memberID)
        REFERENCES Member(memberID)
        ON DELETE CASCADE
);

--weak entity
--(24th - 20) - record
--(24th, 30) - record

CREATE TABLE IF NOT EXISTS FitnessDataRecords(
    date_recorded date NOT NULL,
    c_weight NUMERIC(5,2),
    c_body_fat NUMERIC(4,2),
    heart_rate_avg NUMERIC(5,2),
    height_cm NUMERIC(5,2),
    memberID INT NOT NULL,
    PRIMARY KEY(memberID, date_recorded),
    FOREIGN KEY(memberID)
        REFERENCES Member(memberID)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Trainers(
    trainerID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20) NOT NULL,
    gender VARCHAR(20),
    dob date NOT NULL

);

CREATE TABLE IF NOT EXISTS Room(
    room_number INT PRIMARY KEY,
    room_name VARCHAR(50)
);

--trainer can be null
CREATE TABLE IF NOT EXISTS Session(
    sessionID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    s_date date NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,
    trainerID INT,
    memberID INT NOT NULL,
    room_number INT NOT NULL,
    FOREIGN KEY(trainerID)
        REFERENCES Trainers(trainerID),
    FOREIGN KEY(memberID)
        REFERENCES Member(memberID),
    FOREIGN KEY(room_number)
        REFERENCES Room(room_number)

);

CREATE TABLE IF NOT EXISTS AdministrativeStaff(
    adminID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fname VARCHAR(20) NOT NULL,
    lname VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS SessionAdmin(
    adminID INT NOT NULL,
    sessionID INT NOT NULL,
    PRIMARY KEY(adminID,sessionID),
    FOREIGN KEY(adminID)
        REFERENCES AdministrativeStaff(adminID),
    FOREIGN KEY(sessionID)
        REFERENCES Session(sessionID)
);

--for status - Active, Ended, DUE, Banned, paused
--for type - basic, regular, premium
CREATE TABLE IF NOT EXISTS MemberSubscriptions(
    subscriptionID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    member_since date NOT NULL,
    member_status VARCHAR(7) NOT NULL,
    member_type VARCHAR(7) NOT NULL,
    memberID INT NOT NULL,
    FOREIGN KEY(memberID)
        REFERENCES Member(memberID)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS SubscriptionAdmin(
    subscriptionID INT NOT NULL,
    adminID INT NOT NULL,
    PRIMARY KEY(subscriptionID,adminID),
    FOREIGN KEY(subscriptionID)
        REFERENCES MemberSubscriptions(subscriptionID)
        ON DELETE CASCADE,
    FOREIGN KEY(adminID)
        REFERENCES AdministrativeStaff(adminID)        
);

--max amount 9999.99
--method - Credit, Debit, Cash
--status - Declinded, Approved
CREATE TABLE IF NOT EXISTS PaymentRecords(
    date_recorded date NOT NULL,
    amount NUMERIC(6,2) NOT NULL,
    method VARCHAR(6) NOT NULL,
    p_status VARCHAR(9) NOT NULL,
    subscriptionID INT NOT NULL,
    PRIMARY KEY(subscriptionID, date_recorded),
    FOREIGN KEY(subscriptionID)
        REFERENCES MemberSubscriptions(subscriptionID)
        ON DELETE CASCADE
);


CREATE INDEX member_last_name_first_name_idx
ON Member(lname, fname);


CREATE INDEX trainer_last_name_first_name_idx
ON Trainers(lname, fname);

CREATE INDEX room_name_index
ON Room(room_name);

CREATE INDEX session_times_idx
ON Session(s_date,start_time,end_time);


CREATE VIEW TrainerPTSessionsView AS
    SELECT s.sessionID,
           m.memberID,
           m.fname AS member_fname,
           m.lname AS member_lname,
           s.s_date,
           s.start_time,
           s.end_time,
           t.fname AS trainer_fname,
           t.lname AS trainer_lname,
           r.room_number,
           r.room_name
    FROM Session s
    JOIN Member m ON s.memberID = m.memberID
    JOIN Trainers t ON s.trainerID = t.trainerID
    JOIN Room r ON r.room_number = s.room_number;
    

--room checks
CREATE FUNCTION check_room()
returns TRIGGER
language plpgsql
AS
$$
begin 
    IF EXISTS(
        SELECT 1
        FROM Session s
        WHERE NEW.room_number = s.room_number
            AND NEW.s_date = s.s_date 
            AND NEW.start_time >= s.start_time
            AND NEW.end_time <= s.end_time
    )THEN
        RAISE EXCEPTION
            'Room % is already booked please select a different Room number',New.room_number;
    END IF;
    RETURN NEW;
end;
$$;

CREATE TRIGGER check_room_availablity 
    BEFORE INSERT
    ON Session
    FOR EACH ROW
    EXECUTE PROCEDURE check_room();

    
--1 health metric data perday
CREATE FUNCTION check_date_fitness()
returns TRIGGER
language plpgsql
AS 
$$
begin
    IF EXISTS(
        SELECT 1
        FROM FitnessDataRecords fdr
        WHERE NEW.memberID = fdr.memberID
        AND   NEW.date_recorded = fdr.date_recorded
    )THEN
        RAISE EXCEPTION
            'Maximum limit reached for Today''s Health Data Record! Please try again tomorrow.';
    END IF;
    RETURN NEW;
end;
$$;

CREATE TRIGGER check_limit_for_health_data
    BEFORE INSERT
    ON FitnessDataRecords
    FOR EACH ROW
    EXECUTE PROCEDURE check_date_fitness();

--1 payment perday
CREATE FUNCTION check_date_payment()
returns TRIGGER
language plpgsql
AS
$$
begin
    IF EXISTS(
        SELECT 1
        FROM PaymentRecords p
        WHERE NEW.date_recorded = p.date_recorded
        AND   NEW.subscriptionID = p.subscriptionID
    )THEN
        RAISE EXCEPTION
            'Maximum limit reached for Today''s payment, Please try tomorrow.';
    END IF;
    RETURN NEW;
end;
$$;