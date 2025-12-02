# COMP3005---Final-Project---Gazi-Hossain---101305532
Final project for COMP3005, This project implements a complete fitness club management system with 
  - Entity Relationship modelling,
  - Relational Mapping Diagram,
  - Fully normalized schema(2NF and 3N)
  - PostgreSQL as the DBMS(backend)
  - Java JDBC application(frontend)

#Project Contents:
##1. ER Diagram
- Complete database relationship diagram

---

##2. DDL scripts 
- **Tables(12 Total):** Member, Contact, Trainers, Room, Session, AdministrativeStaff, SessionAdmin,
                    MemberSubscription, SubscriptionAdmin, PersonalFitnessData, FitnessDataRecords,
                    PaymentRecords.
- **Constraints** - Primary keys, foreign keys, identity columns and cascades.
- **Indexes** - Member(first name, last name), Trainer(first name, last name), Room(Room name) and Session(Date, time)
- **Views** - SessionsforEachMember and TrainerPTSessionsView
- **Triggers** - check_room(Prevents Double Bookings), check_fitness_date(Prevent more than 1 FitnessDateRecord per day) and check_payment_date(Prevents more than 1 payment per member)

##3. DML Script
  - Includes a minimum of 5 rows for each Table(Run 3 different queries for complete insertion)

4. Java JDBC Application
  - Contains the full application code which implements -
        >> MemberRegistration - with a membership subscription and a payment
        >> MemberProfile management - updates personal details for members, adding more contacts
        >> HealthHistory - gets members theirs metric entries, personal goals
        >> MemberDashborad - shows member details, their latest health stats, personal goal and upcoming sessions.
        >> PTscheduling - schedules sessions for members(safe from double bookings)
        >> TrainPTVIew - gets all upcoming scheduled sessions for trainers
        >> MemberSearch - gets trainers the most recent health stats and goal of a member they search(case-insensitive)
        >> RoomUpdate - updates Room number for a session
        >> PaymentManagement - allows to make a payment
        >> MembershipManagement - allows to update status of a member.

#How to Run:
1. Make sure to have a database in pgAdmin with the given schema(DDL.sql)
2. Update the connection detail(DB url, username and password) in the source code to match your local setup
3. Make sure to have the proper Drivers(Postgresql for java) installed to connect database
4. Use intellij/Vscode etc to run the application file.

#NOTES:
1. Make sure Postgres is running locally
2. Date format must be YYYY-MM-DD
3. Time format must be HH:MM
4. ILIKE is used for case-insensitive searching
5. LIMIT 1 is used to get the mose recent record
