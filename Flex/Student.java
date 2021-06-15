package Flex;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//This class is a part of Observer Pattern, Observer in Observer Pattern
public class Student extends User {
	
    //--Observer Pattern Data Members--//
    FlexApplication subject;
    List<String> Notifications;
    //--------------------------------------------------------//
    public String stdRollNo;
    public float stdCGPA;

    
    public Student(String name, String gender, String email,
                    String address, String CN, String BG, String cnic,
                    String nationality, String campus, String pass,
                    String RN, float cgpa,FlexApplication subject){

            super(name,gender,email,address,CN,BG,cnic,nationality,
                            campus,pass);
            stdRollNo=RN;
            stdCGPA=cgpa;
            
            this.subject=subject;
            this.subject.register(this);
            Notifications=new ArrayList<>();
    }
//----------------------------------------------------------------------Observer Pattern Functions----------------------------------------------------------------------------//
    public void update(String msg){
        this.Notifications.add(msg);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//        
}
