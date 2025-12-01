--1st query
INSERT INTO Member(email, fname, lname, dob, gender) VALUES
('gazihossain@gmail.com','Gazi','Hossain','2005-07-28','Male'),
('johnpork@gmail.com','John','Pork','1988-01-20','Male'),
('jamesbr@gmail.com','James','Bread','2002-03-23','Male'),
('alic34@gmail.com','Alice','pizza','2001-10-24',NULL),
('emmatsom@gmail.com','Emma','Thomason','2005-12-4','Female'),
('gary@gmail.com','Gary','Gary','2002-02-02','Male'),
('alisonbg@gmail.com','Alison','Burgers','2003-02-14','Male'),
('carla@gmail.com','Carla','Wong','2004-02-20','Female');


INSERT INTO Trainers(fname, lname, gender,dob) VALUES
('Arik','Ahmad','Male','2006-01-03'),
('Tahmid','Ahnaf','Male','2001-11-30'),
('Ruksana','Rahman','Female','1978-09-06'),
('Faisal','Rana','Male','1976-01-10'),
('Jessica','Jones','Female','2004-10-24'),
('Freddy','Fries',NULL,'1999-11-27');


INSERT INTO AdministrativeStaff(fname, lname) VALUES
('Noah','Asford'),
('Oxford','Hills'),
('Brad','Patt'),
('Marcus','Lai'),
('Emma','Wong');

--2nd query
INSERT INTO Contact VALUES
(1,'(124)-414-1244'),
(1,'(144)-411-1294'),
(2,'(154)-119-5244'),
(3,'(117)-614-1314'),
(4,'(184)-424-1274'),
(5,'(174)-154-5274'),
(6,'(113)-614-1214'),
(6,'(114)-419-1004'),
(7,'(123)-469-1064'),
(7,'(343)-119-4006');

INSERT INTO PersonalFitnessData(target_weight,target_body_fat, memberID) VALUES
(80.00,20.00,1),
(70.00,19.24,2),
(60.00,10.23,3),
(NULL,7.00,4),
(100.00,35.00,5),
(65.00,NULL,6),
(NULL,NULL,7);

INSERT INTO FitnessDataRecords(date_recorded, c_weight, c_body_fat, heart_rate_avg, height_cm, memberID) VALUES
('2025-10-02',88.8,25.67,178.41,178.51,1),
('2025-11-12',86.8,24.67,180.52,179,1),
('2025-1-22',108.8,40.69,135,160,2),
('2025-2-01',100.7,35.17,125,130,2),
('2025-6-07',87.2,30.7,186,181,2),
('2025-6-12',53.3,17.53,146,163,3),
('2025-8-25',68.3,21.27,157,143,4),
('2025-6-13',57.4,20.61,146,174,7),
('2025-7-5',125.2,40.62,147,147,6),
('2025-9-23',88.8,30.47,124,143,6);

INSERT INTO Room(room_number,room_name) VALUES
(1, 'Main GYM'),
(2, 'Session Room 1'),
(3, 'Session Room 2'),
(4, 'Session Room 3'),
(5, 'Session Room 4'),
(6, 'Session Room 5'),
(7, 'Session Room 6'),
(8, 'Session Room 7'),
(9, 'Session Room 8'),
(10, 'Session Room 9'),
(11, 'Yoga Room'),
(12, 'Cardio Room'),
(13, 'Office Room'),
(14, 'Management');








INSERT INTO Session(s_date, start_time, end_time, trainerID, memberID, room_number) VALUES
('2025-12-01','12:00','14:00',1,1,2),
('2025-12-02','12:00','14:00',5,1,1),
('2025-12-01','10:00','12:00',2,4,11),
('2025-12-01','9:00','10:00',1,7,12),
('2025-12-01','14:00','16:00',3,5,7),
('2025-12-01','8:00','11:00',4,2,8);





INSERT INTO SessionAdmin VALUES
(1,3),
(5,2),
(2,4),
(5,4),
(2,5);

INSERT INTO MemberSubscriptions(member_since,member_status,member_type,memberID) VALUES
('2025-01-01','Active','Regular',1),
('2025-01-01','Due','Premium',2),
('2025-01-01','Ended','Basic',3),
('2025-01-01','Active','Premuim',4),
('2025-01-01','Active','Regular',5),
('2025-01-01','Paused','Regular',6),
('2025-01-01','Active','Premium',7);

INSERT INTO SubscriptionAdmin VALUES
(1,5),
(2,2),
(6,3),
(4,1),
(4,2);

INSERT INTO PaymentRecords(date_recorded,amount,method,p_status,subscriptionID) VALUES
('2025-02-24',24.99,'Debit','Approved',1),
('2025-03-15',44.99,'Debit','Approved',1),
('2025-10-14',14.99,'Cebit','Approved',2),
('2025-07-01',64.99,'CASH','Approved',4),
('2025-02-24',34.99,'Cebit','Approved',6),
('2025-02-24',50.00,'CASH','Approved',3),
('2025-05-4',12.99,'Debit','Approved',7),
('2025-03-8',355.99,'Debit','Declined',7);

TRUNCATE TABLE Member
RESTART IDENTITY CASCADE;
