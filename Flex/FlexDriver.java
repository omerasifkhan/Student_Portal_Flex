/**
 * 
 */
package Flex;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Aldehyde
 *
 */
public class FlexDriver {



public static void main(String[] args) throws SQLException {

    

    FlexApplication Flex=FlexApplication.getFlexApp();
  
    while(true){
        menu(Flex);
    }
     
    
    //---------Use Cases that work------------// (Student and Faculty Login are basically 1 use case)
    //Flex.StudentLogin();// Haider Wain
    //Flex.Logout(); //Haider Wain
    //Flex.FacultyLogin(); //Haider Wain
    //Haider ka 4th Use Case //Haider Wain
    //Flex.checkTranscript();// Basim Ahmed
    //Flex.FeeDetails(); //Basim Ahmed
    //Flex.PrintChallan();//Basim Ahmed
    //Flex.CheckAttendance(); //Umar Azam
    //Flex.UploadMarks(); //Umar Azam
    //Flex.EditMarks();//Umar Azam
    //Flex.viewCurrentFacultyDetails(); Hassaan Zulqarnain
    }

    public static void menu(FlexApplication Flex){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~Welcome to Flex Menu~~~~~~~~~~~~~~~~~~~~~");
        Scanner newIn=new Scanner(System.in);
        System.out.println("\nAre you a Faculty or a Student? Type either 'Faculty' or 'Student'");
        String choice=newIn.nextLine();
        if(choice.equalsIgnoreCase("Faculty")){
            if(Flex.FacultyLogin()==1){
                FacultyMenu(Flex);
            }
            else {
                System.out.println("Failed to Login.\n");
            }
        }else if(choice.equalsIgnoreCase("Student")){
            if(Flex.StudentLogin()==1){
                StudentMenu(Flex);
            }
            else{
                System.out.println("Failed to login.\n");
            }
        }else{
            System.out.println("Your choice was not according to the constraint!");
        }
    }
    
    public static void FacultyMenu(FlexApplication Flex){
        int s=-1; boolean repeat=true;
        Scanner newIn=new Scanner(System.in);
        while(repeat){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~Welcome to Flex FacultyMenu~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1)Upload Marks of a Student\t2)Edit Marks of a Student\n3)Add Student Attendance\t4)Change Faculty Password\n5)View Current Logged in User's info\t6)Generate Grade\n7)See Attendance of Students\t8)Logout\t0)Exit");
        System.out.print("Enter you Choice: ");
        s=newIn.nextInt();
        switch(s){
            case 1:
                Flex.UploadMarks();//Umar Azam1
                break;
            case 2:
                Flex.EditMarks();//Umar Azam2
                break;
            case 3:
                Flex.addAttendance(); //Hassaan1
                break;
            case 4:
                Flex.changeFacultyInfo(); //Haider2
                break;
            case 5:
                Flex.viewCurrentFacultyDetails(); //hassaan2
                break;
            case 6:
                Flex.generateGrade();  //Hassaan3
                break;
            case 7:
                Flex.see_students_attendance(); //Hamzah3
                break;
            case 8:
                Flex.Logout(); //Haider3
                repeat=false;
                break;
            case 0:
                Flex.Logout(); //Haider3
                repeat=false;
                break;
            default:
                System.out.println("Invalid Choice!");
                break;
    }
        }
    }
    
    public static void StudentMenu(FlexApplication Flex){
        int s=-1; boolean repeat=true;
        Scanner newIn=new Scanner(System.in);
        while(repeat){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~Welcome to Flex StudentMenu~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1)Check Attendance\t2)See Marks\n3)Check Transcript\t4)Register Courses\n5)View Currently Registered Courses\t6)View Future Registerable\n7)Print Challan\t8)Fee Detail\n9)Drop Course\t10)Change Student Password\n11)Logout\t0)Exit");
        System.out.print("Enter you Choice: ");
        s=newIn.nextInt();
        switch(s){
            case 1:
                Flex.CheckAttendance();//Umar Azam3
                break;
            case 2:
                Flex.viewMarks(); //Omer1
                break;
            case 3:
                Flex.checkTranscript(); //Basim1
                break;
            case 4:
                Flex.RegisterCourse(); //Omer2
                break;
            case 5:
                Flex.current_registered_courses(); //Hamzah1
                break;
            case 6:
                Flex.future_registerable_courses(); //Hamzah2
                break;
            case 7:
                Flex.PrintChallan(); //Basim2
                break;
            case 8:
                Flex.FeeDetails(); //Basim3
                break;
            case 9:
                Flex.dropCourse(); //Omer3
                break;
            case 10:
                Flex.changeUserInfo();
                break;
            case 11:
                Flex.Logout(); //Haider3
                repeat=false;
                break;
            case 0:
                Flex.Logout(); //Haider3
                repeat=false;
                break;
            default:
                System.out.println("Invalid Choice!");
                break;
    }
        }
    }
}
