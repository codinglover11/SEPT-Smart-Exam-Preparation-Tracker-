import java.util.Scanner;

public class SEPT {
    static Scanner sc = new Scanner(System.in);
    final static void start(){

        System.out.println("        WELCOME TO SEPT(SMART EXAM PREPARATION TRACKER          )");
        int choice=0;
        while (choice!=6){
            System.out.println("Press-1. Add Subjects");
            System.out.println("Press-2. Log Study Time");
            System.out.println("Press-3. Show Study Report");
            System.out.println("Press-4. Export Backup");
            System.out.println("Press-5. Import Backup");
            System.out.println("Press-6. Exit");
            System.out.print("Pleas Enter Your Choice: ");
            choice= sc.nextInt();
            sc.nextLine();
            if(choice==1){
                AddSubject();
            } else if (choice==2) {
                LogStudy();
            } else if (choice==3) {
                ShowReport();
            } else if (choice==4) {
                System.out.println("Coming Soon.......");
            } else if (choice==5) {
                System.out.println("Coming Soon.......");
            }else {
                System.out.println("Invalid Key SEPT Exits....");
            }}
        System.out.println("THANKYOU FOR USING SEPT VISIT's AGAIN");
        System.out.println("YOU ARE EXITING SUCCESSFULLY.....");
    }
    static void AddSubject(){
        System.out.print("Welcome to SEPT you can add your Subject: ");
        String subj= sc.nextLine();
        if (subj==null || subj.trim().isEmpty()) {
            System.out.println("Invalid input. Please enter valid data.");
        } else {
            System.out.println(subj+" Added Successfully");
        }
    }
    static void LogStudy() {
        System.out.println("Available Subjects: ");
        System.out.print("Enter Subject Number: ");
        int num=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter date (YYYY-MM-DD):");
        String dateStr = sc.nextLine();
        if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Date format looks okay");
        } else {
            System.out.println("Invalid date format. Use YYYY-MM-DD");
        }
        System.out.print("Enter hours studied: ");
        int hr= sc.nextInt();
        if(hr<0){
            System.out.println("Hour cannot be less than 0");
        }

    }

    static void ShowReport(){
        System.out.println("Welcom To get The Reports of the Subject:");
        System.out.println("Study Reports...");
        System.out.println("Data      | Subject    | Hours");
        System.out.println("20XX      | DSA    | 12");
    }
    public static void main(String[] args) {
        start();

    }
}

