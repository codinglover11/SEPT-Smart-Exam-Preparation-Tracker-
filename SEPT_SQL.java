import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class SEPT {
    static Scanner sc = new Scanner(System.in);
    static SEPT_JDBC jdbcHelper = new SEPT_JDBC();

    final static void start(){
        System.out.println("        WELCOME TO SEPT (SMART EXAM PREPARATION TRACKER)");
        int choice=0;
        while (choice!=6){
            System.out.println("Press-1. Add Subjects");
            System.out.println("Press-2. Log Study Time");
            System.out.println("Press-3. Show Study Report");
            System.out.println("Press-4. Export Backup");
            System.out.println("Press-5. Import Backup");
            System.out.println("Press-6. Exit");
            System.out.print("Please Enter Your Choice: ");
            choice= sc.nextInt();
            sc.nextLine();
            if(choice==1){
                AddSubject();
            } else if (choice==2) {
                LogStudy();
            } else if (choice==3) {
                ShowReport();
            } else if (choice==4 || choice==5) {
                System.out.println("Coming Soon.......");
            } else if(choice==6){
                System.out.println("THANK YOU FOR USING SEPT. EXITING SUCCESSFULLY.....");
            } else {
                System.out.println("Invalid Choice.");
            }
        }
    }

    static void AddSubject(){
        System.out.println("Welcome to SEPT! You can add your Subject: ");
        System.out.print("Add Subject: ");
        String subj= sc.nextLine();
        if (subj==null || subj.trim().isEmpty()) {
            System.out.println("Invalid input. Please enter valid data.");
        } else {
            jdbcHelper.insertSubject(subj);
        }
    }

    static void LogStudy() {
        System.out.println("Available Subjects: ");
        jdbcHelper.showData();
        System.out.print("Enter Subject Number: ");
        int num=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Start date (YYYY-MM-DD):");
        String dateStr = sc.nextLine();
        if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Date format looks okay");
        } else {
            System.out.println("Invalid date format. Use YYYY-MM-DD");
        }
        sc.nextLine();
        System.out.print("Enter End date (YYYY-MM-DD):");
        String dateStr2 = sc.nextLine();
        if (dateStr2.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Date format looks okay");
        } else {
            System.out.println("Invalid date format. Use YYYY-MM-DD");
        }
        System.out.print("Enter hours studied: ");
        int hr= sc.nextInt();
        if(hr<0){
            System.out.println("Hour cannot be less than 0");
        }else{
        jdbcHelper.insertStudyLog(dateStr,dateStr2,hr,num);}
    }



    static void ShowReport(){
        System.out.println("Welcome To get The Reports of the Subject:");
        System.out.println("Study Reports...");
        System.out.println("id  "+"Subjects   "+"START_TIME    "+"END_DATE     "+"STATUS    "+"HOUR_STUDIES");
       jdbcHelper.Report();
    }


    public static void main(String[] args) {
        start();
    }
}

class SEPT_JDBC {
    private static final String url = "jdbc:mysql://localhost:3306/sept";
    private static final String user = "root";
    private static final String pass = "1234";

    public void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver load failed: " + e.getMessage());
        }
    }

    public void insertSubject(String subject) {
        try (Connection cn = DriverManager.getConnection(url, user, pass)) {
            String query = "INSERT INTO SEPT_DATA (Subject) VALUES (?)";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, subject);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(subject + " added successfully to DB.");
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void insertStudyLog(String start, String end, int hour,int id) {
        try (Connection cn = DriverManager.getConnection(url, user, pass)) {
            String query = "UPDATE SEPT_DATA SET HOUR_STUDIED = ?, START_DATE = ?, END_DATE = ? WHERE id = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, hour);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setInt(4, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Study log added successfully to DB.");
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void showData() {
        try (Connection cn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT id, Subject FROM SEPT_DATA";
            PreparedStatement ps = cn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ids = rs.getInt("id");
                String subject = rs.getString("Subject");
                System.out.println("ID: " + ids + ", Subject: " + subject);
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
    public void Report(){
        try{
            Connection cn = DriverManager.getConnection(url,user,pass);
            String query="Select * from SEPT_DATA";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String sub=rs.getString("Subject");
                String Std_date= rs.getString("START_DATE");
                String end_date= rs.getString("END_DATE");
                String Status=rs.getString("STATUS");
                int Hour= rs.getInt("HOUR_STUDIED");
                System.out.print(id+"   "+sub+"     "+Std_date+"    "+end_date+"    "+Status+"  "+Hour);
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}


