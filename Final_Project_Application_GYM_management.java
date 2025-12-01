


import javax.script.ScriptContext;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Final_Project_Application_GYM_management {
    public static void main(String[] arg){

        String url = "jdbc:postgresql://localhost:5432/Gym_management_final_project";
        String username = "postgres";
        String password = "admin";
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            if(connection==null){
                System.out.println("Unable to connect to the database");
            }

            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            //getAllMembers(statement);
            //registerMember(statement,"Eyad","The.rapist ","getthefilled@hole.egypt","0001-01-01","Unknown");
            //allFitnessRecords(statement,1);
            //getMember(statement,11);
            //addPersonalGoal(statement,80.92,30.22,10);
            //updatePersonalGoal(statement,90.22,20.30,10);
            //addTrainingRecord(statement,"2025-09-01",100.20,40.20,140.44,150,10);
            //updateMember(statement,"Eyad","ABCs","eyadabcds@gmail.com","2005-02-20","Male",10);
            //getAllFitnessRecords(statement,10);
            //getLatestFitnessRecord(statement,10);
            //getPersonalGoal(statement,10);
            //bookSessions(statement,"2025-05-23","10:00","12:00",2,6,5);
            //getSession(statement,1);
            System.out.println("  ---------------------------------------");
            System.out.println("--------Welcome to GET FIT GYM System---------");
            System.out.println("Please Select an option from the following list "+'\n');
            String option = "A";
            while(!Objects.equals(option, "q")){
                System.out.println(
                        "(1) Add a Member(Includes a Membership and first payment)" + '\n'+
                        "(2) Update Personal details(Only for Members)" +'\n'+
                        "(3) Add a Contact(Only for Members)" +'\n'+
                        "(4) Add a personal goal(Only for Members)" +'\n'+
                        "(5) Update personal goal(Only for Members)" +'\n'+
                        "(6) Add Today's Health metric" +'\n'+
                        "(7) Show all Health metrics" +'\n'+
                        "(8) Dashboard(Members only)" +'\n'+
                        "(9) Book a Session" +'\n'+
                        "(10) See all Session bookings"+ '\n'+
                        "(11) Schedule View(For Trainers)" +'\n'+
                        "(12) Member look up by name(Gets their current goal and lastest metric)-VIEW ONLY-" +'\n'+
                        "(13) Update a Session(Admin only)" +'\n'+
                        "(14) Make a payment"+'\n'+
                        "(15) Manage MemberSubscription"+'\n'+
                        "(16) Forgot your MemberID? get MemberID" +'\n'+
                        "(17) See all Payment Records" + '\n'+
                        "Enter q to Exit");

                option = scanner.nextLine();

                if(Objects.equals(option, "1")){
                    System.out.println("'*'-->is required");
                    String email, fname, lname,dob,gender;
                    System.out.print("*Please Enter your first name: ");
                    fname = scanner.nextLine();
                    System.out.print("*Please enter your last name: ");
                    lname = scanner.nextLine();
                    System.out.print("*Your email: ");
                    email = scanner.nextLine();
                    System.out.println("*Your date of birth(Format - YYYY-MM-DD): ");
                    dob  = scanner.nextLine();
                    System.out.print("Your gender: ");
                    gender = scanner.nextLine();
                    if (gender.isEmpty()) {
                        gender = null;
                    }
                   registerMember(statement,fname,lname,email,dob,gender);
                    continue;
                }

                if(Objects.equals(option,"2")){
                    int memberID;
                    String email, fname, lname,dob,gender;
                    System.out.println("Please enter you memberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please re-enter your details-");
                    System.out.println("'*'-->is required");
                    System.out.print("*Please Enter your first name: ");
                    fname = scanner.nextLine();
                    System.out.print("*Please enter your last name: ");
                    lname = scanner.nextLine();
                    System.out.print("*Your email: ");
                    email = scanner.nextLine();
                    System.out.println("*Your date of birth(Format - YYYY-MM-DD): ");
                    dob  = scanner.nextLine();
                    System.out.print("Your gender: ");
                    gender = scanner.nextLine();
                    if (gender.isEmpty()) {
                        gender = null;
                    }
                    updateMember(statement,fname,lname,email,dob,gender,memberID);
                    continue;

                }

                if(Objects.equals(option,"3")){
                    int memberID;
                    String contact;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter you new Contact: ");
                    contact = scanner.nextLine();
                    addContact(statement,memberID,contact);
                    continue;
                }
                if(Objects.equals(option,"4")){
                    double t_weight, t_bodyfat;
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter your target weight: ");
                    t_weight = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Please enter your target body fat: ");
                    t_bodyfat = scanner.nextDouble();
                    scanner.nextLine();
                    addPersonalGoal(statement,t_weight,t_bodyfat,memberID);
                    continue;
                }

                if(Objects.equals(option,"5")){
                    double t_weight, t_bodyfat;
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please Re-enter your goals-");
                    System.out.println("Please enter your new target weight: ");
                    t_weight = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Please enter your new target body fat: ");
                    t_bodyfat = scanner.nextDouble();
                    scanner.nextLine();
                    updatePersonalGoal(statement,t_weight,t_bodyfat,memberID);
                    continue;
                }

                if(Objects.equals(option,"6")){
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Hope you had a good workout today!!");
                    String date;
                    double c_weight,c_body_fat,heart_rate,height;
                    System.out.println("Please enter today's date: ");
                    date = scanner.nextLine();
                    System.out.println("Enter your current weight: ");
                    c_weight= scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter your current body fat percentage: ");
                    c_body_fat= scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter your current height: ");
                    height = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter your average heart rate today: ");
                    heart_rate = scanner.nextDouble();
                    scanner.nextLine();
                    addTrainingRecord(statement,date,c_weight,c_body_fat,heart_rate,height,memberID);
                    continue;
                }

                if(Objects.equals(option,"7")){
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    getAllTrainingRecords(statement,memberID);
                    continue;
                }

                if(Objects.equals(option,"8")){
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    memberDashboard(statement,memberID);
                    continue;
                }

                if(Objects.equals(option,"9")){
                    int memberID, trainerID, room_number;
                    String date,start_time,end_time;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Session date: ");
                    date = scanner.nextLine();
                    System.out.println("Start time: ");
                    start_time = scanner.nextLine();
                    System.out.println("End time: ");
                    end_time = scanner.nextLine();
                    System.out.println("Enter trainerID: ");
                    trainerID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter room number: ");
                    room_number = scanner.nextInt();
                    scanner.nextLine();

                    bookSessions(statement,date,start_time,end_time,trainerID,memberID,room_number);
                    continue;
                }

                if(Objects.equals(option,"10")){
                    int memberID;
                    System.out.println("Please enter your MemberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    getAllSession(statement,memberID);
                }

                if(Objects.equals(option,"11")){
                    int trainerID;
                    System.out.println("Please enter your trainerID: ");
                    trainerID = scanner.nextInt();
                    trainerScheduleView(statement,trainerID);
                }

                if(Objects.equals(option,"12")){
                    String fname, lname, email;
                    System.out.println("Please enter Member's First name: ");
                    fname = scanner.nextLine();
                    System.out.println("Please enter Member's Last name: ");
                    lname = scanner.nextLine();
                    System.out.println("Please enter Member's email: ");
                    email = scanner.nextLine();
                    int memberID = getActualMemberID(statement,fname,lname,email);
                    memberLookup(statement,memberID);
                }

                if(Objects.equals(option,"13")){
                    int room_number, sessionID, adminID;
                    System.out.println("Please Enter you AdminID: ");
                    adminID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please Enter the SessionID you would like to change: ");
                    sessionID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please Enter the Room number you would like to change to: ");
                    room_number = scanner.nextInt();
                    scanner.nextLine();
                    updateSessioRoom(statement,sessionID,room_number,adminID);
                }

                if(Objects.equals(option,"14")){
                    System.out.println("Please enter your MemberID: ");
                    int memberID = scanner.nextInt();
                    scanner.nextLine();
                    int subID = getSubscriptionID(statement,memberID);
                    String payment_date, payment_method, payment_status;
                    double amount;
                    System.out.println("Payment date: ");
                    payment_date = scanner.nextLine();
                    System.out.println("Payment Amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Payment Method(Credit, Debit, Cash): ");
                    payment_method = scanner.nextLine();
                    System.out.println("Payment Status(Declined, Approved): ");
                    payment_status = scanner.nextLine();
                    addMemberPayment(statement,subID,payment_date,payment_method,amount,payment_status);
                }

                if(Objects.equals(option,"15")){
                    int memberID, adminID;
                    String status, type;
                    System.out.println("Please Enter you AdminID: ");
                    adminID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please Enter the memberID: ");
                    memberID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please Enter the Membership type(Basic, Regular, Premium): ");
                    type = scanner.nextLine();
                    System.out.println("Please Enter the Membership Status(Active, Ended, Due, Paused): ");
                    status = scanner.nextLine();
                    updateMemberSubscription(statement,status,type,memberID,adminID);
                }

                if(Objects.equals(option,"16")){
                    String fname, lname, email;
                    System.out.println("Please enter your First name: ");
                    fname = scanner.nextLine();
                    System.out.println("Your Last name: ");
                    lname = scanner.nextLine();
                    System.out.println("Your email: ");
                    email = scanner.nextLine();
                    Integer memberID = getActualMemberID(statement,fname,lname,email);
                    System.out.println("Your MemberID is "+memberID);
                    if(memberID==null){
                        System.out.println("Member doesn't exist!");
                    }
                }


                if(Objects.equals(option,"17")){
                    System.out.println("Please enter you MemberID: ");
                    int memberID = scanner.nextInt();
                    scanner.nextLine();
                    getAllPaymentRecords(statement,memberID);

                }






            }


        } catch (Exception e) {
            throw new RuntimeException(e);

        }





    }


    static void getAllMembers(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Member");
            while (resultSet.next()) {
                System.out.print("MemberID: "+resultSet.getInt("memberID")+'\n');
                System.out.print("Name: "+resultSet.getString("fname"));
                System.out.print(" "+resultSet.getString("lname")+"\n");
                System.out.print("Email: "+resultSet.getString("email")+'\n');
                System.out.print("DOB: "+resultSet.getDate("dob")+ '\n');
                System.out.println("Gender: "+resultSet.getString("gender")+'\n');

            }
        }catch (Exception e){}

    }
    static  void getMember(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM Member WHERE memberID = '%d';",memberID);
;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.print("MemberID: "+resultSet.getInt("memberID")+'\n');
                System.out.print("Name: "+resultSet.getString("fname"));
                System.out.print(" "+resultSet.getString("lname")+"\n");
                System.out.print("Email: "+resultSet.getString("email")+'\n');
                System.out.print("DOB: "+resultSet.getDate("dob")+ '\n');
                System.out.println("Gender: "+resultSet.getString("gender")+'\n');

            }else{
                System.out.println("Member not found!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    static void updateMember(Statement statement, String fname, String lname, String email, String date, String gender,int memberID){

        String sql = String.format("UPDATE Member " +
                "SET fname = '%s', lname = '%s', email = '%s', dob = '%s', gender ='%s' " +
                "WHERE memberID = '%d';",fname,lname,email,date,gender,memberID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Personal detail updated!");
        } catch (SQLException e) {
            System.out.println("Unable to update personal details");
            throw new RuntimeException(e);

        }

    }


    /*
    FOR MEMBER REGISTRATION
        - after registration member must add a contact, subscription and make a payment
     */
    //add the member to the table - then add a contact - then add a subscription - then add a payment
    static void registerMember(Statement statement, String fname, String lname, String email, String date, String gender){
        String sql = String.format("INSERT INTO Member(email, fname, lname, dob, gender) VALUES " +
                "('%s','%s','%s','%s','%s')",email,fname,lname,date,gender);
        try{
            statement.executeUpdate(sql);
            System.out.println("Member added!");
        } catch (SQLException e) {
            System.out.println("Unable to add Member");
            throw new RuntimeException(e);

        }

        int memberID = getMemberID(statement,fname,lname);
        System.out.println("Your memberID is "+memberID);

        Scanner scanner = new Scanner(System.in);

        //adding contact
        System.out.println("*Please add a contact: ");
        String contact = scanner.nextLine();
        addContact(statement,memberID,contact);

        //adding membership
        String member_since, member_type, member_status;
        System.out.print("Enter Today's Date(Format - YYYY-MM-DD): ");
        member_since = scanner.nextLine();
        System.out.println("Enter Member Type(Basic(10.99$), Regular(14.99$), Premium(24.99$): ");
        member_type= scanner.nextLine();
        System.out.println("Enter Member Status(Active, Ended, Due, Paused): ");
        member_status =  scanner.nextLine();
        addMemberSubscription(statement, memberID,member_since,member_type,member_status);

        //make payment
        System.out.println("Please make a Payment");
        int subID = getSubscriptionID(statement,memberID);
        String payment_date, payment_method, payment_status;
        double amount;
        System.out.println("Payment date: ");
        payment_date = scanner.nextLine();
        System.out.println("Payment Amount: ");
        amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Payment Method(Credit, Debit, Cash): ");
        payment_method = scanner.nextLine();
        System.out.println("Payment Status(Declined, Approved): ");
        payment_status = scanner.nextLine();
        addMemberPayment(statement,subID,payment_date,payment_method,amount,payment_status);

    }
    //add contact
    static void addContact(Statement statement, int memberID, String contact){
        String sql = String.format("INSERT INTO Contact VALUES " +
                "('%d','%s')",memberID,contact);
        try{
            statement.executeUpdate(sql);
            System.out.println("Contact added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //add subscription
    static void addMemberSubscription(Statement statement,int memberID, String member_date, String sub_type, String m_status){

        String sql = String.format("INSERT INTO MemberSubscriptions(member_since,member_status,member_type,memberID) VALUES " +
                "('%s','%s','%s','%d')",member_date,m_status,sub_type,memberID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Member Subscription added!");
        } catch (Exception e) {
            System.out.println("Unable to add Member Subscription");
        }

    }
    //add payment
    static void addMemberPayment(Statement statement,int subID, String p_date, String p_method, double amount, String p_status){
        String sql = String.format("INSERT INTO PaymentRecords(date_recorded,amount,method,p_status,subscriptionID) VALUES " +
                "('%s','%f','%s','%s','%d')",p_date,amount,p_method,p_status,subID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Payment recorded");
        } catch (SQLException e) {
            System.out.println("Payment not recorded!");
        }

    }


    //Updating Personal Details
    static void addPersonalGoal(Statement statement, double target_weight, double target_body_fat,int memberID){
        String sql = String.format("INSERT INTO PersonalFitnessData(target_weight,target_body_fat, memberID) VALUES " +
                                    "('%f','%f','%d')",target_weight,target_body_fat,memberID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Goal added");
        } catch (SQLException e) {
            System.out.println("unable to add");
            throw new RuntimeException(e);

        }

    }
    static void updatePersonalGoal(Statement statement, double target_weight, double target_body_fat,int memberID){
        String sql = String.format("UPDATE Personalfitnessdata " +
                "SET target_weight = '%f', target_body_fat = '%f' " +
                "WHERE memberID = '%d';",target_weight,target_body_fat,memberID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Personal Goal updated!");
        } catch (SQLException e) {
            System.out.println("unable to update Personal Goal updated!");
            throw new RuntimeException(e);

        }

    }
    static void getPersonalGoal(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM Personalfitnessdata " +
                                    "WHERE memberID = '%d'",memberID);
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                //System.out.println("MemberID = "+resultSet.getInt("memberID"));
                System.out.println("Target weight = "+resultSet.getDouble("target_weight")+"Kg");
                System.out.println("Target Body fat = "+resultSet.getDouble("target_body_fat")+"% \n");


            }else{
                System.out.println("Member doesn't have a goal yet!");
            }
        } catch (SQLException e) {
            System.out.println("Member not found");
            throw new RuntimeException(e);
        }
    }
    static void addTrainingRecord(Statement statement, String t_date, double c_weight, double c_body_fat, double heart_rate, double height, int membreID){
        String sql = String.format("INSERT INTO FitnessDataRecords(date_recorded, c_weight, c_body_fat, heart_rate_avg, height_cm, memberID) VALUES " +
                                    "('%s','%f','%f','%f','%f','%d')",t_date,c_weight,c_body_fat,heart_rate,height,membreID);
        try{
            statement.executeUpdate(sql);
            System.out.println("Training record added");
        } catch (SQLException e) {
            System.out.println("unable to add Training record");
            throw new RuntimeException(e);

        }
    }
    static void getAllTrainingRecords(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM  FitnessDataRecords " +
                                    "WHERE memberID = '%s'",memberID);
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            int record = 0;
            while (resultSet.next()) {
                record+=1;
                System.out.println("Record "+record);
                System.out.print("Date: " + resultSet.getDate("date_recorded") + '\n');
                System.out.print("Weight: " + resultSet.getDouble("c_weight")+ '\n');
                System.out.print("Body fat%: " + resultSet.getDouble("c_body_fat") + "\n");
                System.out.print("Heart Rate(avg): " + resultSet.getDouble("heart_rate_avg") + '\n');
                System.out.println("Height: " + resultSet.getDouble("height_cm") + '\n');

            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    static void getLatestTrainingRecord(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM  FitnessDataRecords " +
                "WHERE memberID = '%s' " +
                "ORDER BY date_recorded DESC LIMIT 1",memberID);

        try{
            ResultSet resultSet = statement.executeQuery(sql);
            try {
                if(resultSet.next()) {
                    System.out.print("Date: " + resultSet.getDate("date_recorded") + '\n');
                    System.out.print("Weight: " + resultSet.getDouble("c_weight") + '\n');
                    System.out.print("Body fat%: " + resultSet.getDouble("c_body_fat") + "\n");
                    System.out.print("Heart Rate(avg): " + resultSet.getDouble("heart_rate_avg") + '\n');
                    System.out.println("Height: " + resultSet.getDouble("height_cm") + '\n');
                }

            }catch (Exception e){}

        } catch (RuntimeException e) {
            throw new RuntimeException(e);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }



    static void bookSessions(Statement statement, String date, String start_time, String end_time, int trainerID, int memberID, int room_number ){
        String sql = String.format("INSERT INTO Session(s_date, start_time, end_time, trainerID, memberID, room_number) VALUES " +
                                    "('%s','%s','%s','%d','%d','%d')",date,start_time,end_time,trainerID,memberID,room_number);
        try{
            statement.executeUpdate(sql);
            System.out.println("Session added!");
        } catch (SQLException e) {
            System.out.println("Session not added!");
            throw new RuntimeException(e);
        }
    }

    static void getAllSession(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM SessionsForEachMember WHERE memberID = '%d'",memberID);
        int session = 0;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                session+=1;
                System.out.println("Session: "+session+" SessionID: "+resultSet.getInt("sessionID"));
                System.out.print("Session date: "+resultSet.getDate("s_date") +'\t');
                System.out.print("Start time: "+resultSet.getTime("start_time")+'\t');
                System.out.print("End time: "+resultSet.getTime("end_time")+'\n');
                System.out.print("TrainerID: "+resultSet.getInt("trainerID")+" ");
                System.out.print("Trainer Name: "+resultSet.getString("fname")+" "+resultSet.getString("lname")+"\n");
                System.out.print("Room number: "+resultSet.getInt("room_number")+'\t');
                System.out.println("Room name: "+resultSet.getString("room_name")+'\n');

            }
        } catch (SQLException e) {
            System.out.println("No Sessions found!");
            throw new RuntimeException(e);

        }
    }
    static void getUpcomingSession(Statement statement, int memberID){
        String sql = String.format("SELECT * FROM SessionsForEachMember " +
                                     "WHERE memberID = '%d' AND s_date >= CURRENT_DATE " +
                                    "ORDER BY s_date DESC",memberID);
        int session = 0;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                session+=1;
                System.out.println("Session: "+session+" SessionID: "+resultSet.getInt("sessionID"));
                System.out.print("Session date: "+resultSet.getDate("s_date") +'\t');
                System.out.print("Start time: "+resultSet.getTime("start_time")+'\t');
                System.out.print("End time: "+resultSet.getTime("end_time")+'\n');
                System.out.print("TrainerID: "+resultSet.getInt("trainerID")+" ");
                System.out.print("Trainer Name: "+resultSet.getString("fname")+" "+resultSet.getString("lname")+"\n");
                System.out.print("Room number: "+resultSet.getInt("room_number")+'\t');
                System.out.println("Room name: "+resultSet.getString("room_name")+'\n');

            }
        } catch (SQLException e) {
            System.out.println("No Sessions found!");
            throw new RuntimeException(e);

        }
    }

    static void memberDashboard(Statement statement, int memberID) {
        System.out.println("------------------------");
        System.out.println("---Member DashBoard----");
        System.out.println("------------------------\n");
        System.out.println("-------Member INFO-------");
        getMember(statement,memberID);
        System.out.println("-------Personal Goal-------");
        getPersonalGoal(statement,memberID);
        System.out.println("-------Latest Health Record------- ");
        getLatestTrainingRecord(statement,memberID);
        System.out.println("-------Upcoming Session/s--------");
        getUpcomingSession(statement,memberID);

    }

    static void trainerScheduleView(Statement statement,int trainerID){
        String sql = String.format("SELECT * FROM TrainerPTSessionsView " +
                                    "WHERE trainerID = '%d' AND s_date >= CURRENT_DATE",trainerID);
        System.out.println("-----------------------------------");
        System.out.println("------Upcoming PT Sessions---------");
        System.out.println("-----------------------------------");
        int session = 0;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            System.out.println("Trainer Name: "+resultSet.getString("trainer_fname")+' '+resultSet.getString("trainer_lname"));
            do{
                session+=1;
                System.out.println("Session: "+session+" SessionID: "+resultSet.getInt("sessionID"));
                System.out.print("Session date: "+resultSet.getDate("s_date") +'\t');
                System.out.print("Start time: "+resultSet.getTime("start_time")+'\t');
                System.out.print("End time: "+resultSet.getTime("end_time")+'\n');
                System.out.print("MemberID: "+resultSet.getInt("memberID")+" ");
                System.out.print("Member Name: "+resultSet.getString("member_fname")+" "+resultSet.getString("member_lname")+"\n");
                System.out.print("Room number: "+resultSet.getInt("room_number")+'\t');
                System.out.println("Room name: "+resultSet.getString("room_name")+'\n');
            }while(resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    static Integer getActualMemberID(Statement statement, String fname, String lname,String emaill){
        try {
            //case insensitive
            String sql = String.format("Select memberID FROM Member" +
                    " WHERE fname ILIKE '%s' AND lname ILIKE '%s' AND email ILIKE '%s'",fname,lname,emaill);

            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int memberID = resultSet.getInt("memberID");
                System.out.println(memberID);

                return memberID;

            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Member ID not found");
            return null;
        }

    }

    static void memberLookup(Statement statement, int memberID){
        System.out.println("----Current Goal-----");
        getPersonalGoal(statement,memberID);
        System.out.println("-----Last Metric-----");
        getLatestTrainingRecord(statement,memberID);
    }

    static void updateSessioRoom(Statement statement, int sessionID,int room_number,int adminID){
        String sql = String.format("UPDATE Session SET room_number = '%d' WHERE sessionID = '%d'",room_number,sessionID);

        try{
            statement.executeUpdate(sql);
            System.out.println("Session Updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql_add_admin = String.format("INSERT INTO SessionAdmin VALUES ('%d','%d')",adminID,sessionID);
        try{
            statement.executeUpdate(sql_add_admin);
            System.out.println("Row added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    static void updateMemberSubscription(Statement statement, String status, String type, int memberID, int adminID){
        String sql = String.format("UPDATE MemberSubscriptions SET member_status = '%s', SET member_type = '%s'" +
                                    " WHERE memberID = '%d'",status,type,memberID);

        try{
            statement.executeUpdate(sql);
            System.out.println("MemberSubscription Updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int subID = getSubscriptionID(statement,memberID);
        String sql_add_admin = String.format("INSERT INTO SubscriptionAdmin VALUES ('%d','%d')",adminID,subID);
        try{
            statement.executeUpdate(sql_add_admin);
            System.out.println("Row added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    static void getAllPaymentRecords(Statement statement, int memberID){
        int subID = getSubscriptionID(statement, memberID);

        String sql = String.format("SELECT * FROM PaymentRecords " +
                                    "WHERE subscriptionID = '%d'",subID);
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            int payment =0;
            while(resultSet.next()){
                payment+=1;
                System.out.println("Payment "+payment);
                System.out.println("Payment Date: "+resultSet.getDate("date_recorded"));
                System.out.println("Amount: "+resultSet.getDouble("amount"));
                System.out.println("Payment Method: "+resultSet.getString("method"));
                System.out.println("Payment Status: "+resultSet.getString("p_status")+'\n');
            }
        } catch (SQLException e) {
            System.out.println("Unable to get records!");
            throw new RuntimeException(e);
        }
    }







    //get MemberID
    static Integer getMemberID(Statement statement, String fname, String lname){
        try {
            String sql = String.format("Select memberID FROM Member" +
                    " WHERE fname = '%s' AND lname = '%s'",fname,lname);

            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int memberID = resultSet.getInt("memberID");
                return memberID;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Member ID not found");
            return null;
        }

    };

    //get SubscriptionID
    static Integer getSubscriptionID(Statement statement, int memberID){
        String sql = String.format("SELECT subscriptionID FROM MemberSubscriptions " +
                "WHERE memberID = '%d'",memberID);
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int subID = resultSet.getInt("subscriptionID");
                return subID;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println("Unable to find MemberSubscription");
            return null;
        }
    }




}
