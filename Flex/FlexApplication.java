package Flex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//This class will be implemented on Singleton Pattern
//Also This class is also a part of Observer Pattern

//This class is Singleton and Subject in Observer Pattern
public class FlexApplication {
    
    //--Observer Pattern Data Members--//
    List<Student> observers;
    int state;
    //--------------------------------------------------------//
    public static Student you;
    public static Faculty fyou;
    dbConnectivity db;

    

    private static FlexApplication flexApp;

    
    private FlexApplication(){
            observers=new ArrayList<>();
            db=new dbConnectivity();
    }
    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
//----------------------------------------------------------------------Observer Pattern Functions----------------------------------------------------------------------------//
    public void register(Student std){
        observers.add(std);
    }
    
    public void unregister(Student std){
        observers.remove(std);
    }
    
    public void notifyObserver(Student std,String msg){
        if(observers.contains(std)){
            std.Notifications.add(msg);
        }
    }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//    
    public static FlexApplication getFlexApp()  {

            if(flexApp==null) {
                    flexApp=new FlexApplication();
            }

            return flexApp;

    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public void populateDatabase() throws SQLException{
        
                      
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//            
            //Making 6 mock users (3 students and 3 faculty) and adding them to database
            User JohnWick=new Graduate ("John Wick ","Male","withapencil@continental.com ","Continental Street"
                                                                     ,"03314343434"," O-","1234-4321","American","Lahore","Killerwithapencil"
                                                                     ,"L171234" , (float) 3.21,"Software for patients with Alzheimers",this);
            
            User Batman=new Undergraduate("Bruce Wayne","Male","darkknight@gotham.com","Wayne Manor",
                                                              "03131234567","B-","4321-1234","American","Lahore","iambatman",
                                                               "L174321",(float)4.00,"Robotic Arm for Doctors",this);
            User BlackWidow=new Undergraduate("Natasha Romanoff","Female","nopowers@marvel.com","Russia",
                                                                     "090078601","AB-","1111-2222","American","Lahore","fallendownandcantgetup",
                                                                      "L171111",(float)3.00,"Compiler for dumdums",this);
            
            this.observers.add((Student)JohnWick);
            this.observers.add((Student)Batman);
            this.observers.add((Student)BlackWidow);
            
            User ProfX=new PermanentFaculty("Charles Xavier","Male","profx@xmen.com","Jean Grey School,Virginia",
                                                                    "03131313131","O-","9999-9999","American","Lahore","brainiacX",1,(long)80000);
            
            User Wolvie=new VisitingFaculty("Logan","Male","claws@xmen.com","Vagabond","03337654321","A+","9999-9999","Canadian",
                                                                "Lahore","angriboi",2,(long)5000);
            User Heisenberg=new PermanentFaculty("Walter White","Male","heisenberg@meth.com","Alberquerque",
                                                                             "01230987654","AB-","6666-6666","American","Lahore","thedanger",3,
                                                                             (long)100000);
            
           User [] tempArr=new User[6];
           tempArr[0]=JohnWick;
           tempArr[1]=Batman;
           tempArr[2]=BlackWidow;
           tempArr[3]=ProfX;
           tempArr[4]=Wolvie;
           tempArr[5]=Heisenberg;
           
           //populating user table in database
           for(int i=0;i<6;i++){
               db.populateUserTable(tempArr[i]);
           }
            db.displayAllUsers();
            
            //populating Student table in database
            for(int i=0;i<3;i++){
                db.populateStudent((Student)tempArr[i]);
            }
            db.displayAllStudents();
            
            //Populating Graduate Student table in database
            db.populateGraduateStudentTable((Graduate)JohnWick);
            db.displayAllGraduateStudents();
            
            //populating undergradstudent table in database
            db.populateUndergraduateStudentTable((Undergraduate)Batman);
            db.populateUndergraduateStudentTable((Undergraduate)BlackWidow);
            db.displayAllUndergraduateStudents();
            
            //populating faculty in faculty table in database            
            for(int i=3;i<6;i++){
               db.populateFacultyTable((Faculty)tempArr[i]);
               //Faculty temp=(Faculty)tempArr[i];
               //ResultSet rs=db.stmt.executeQuery("Select FacID from Faculty Where Email='"+temp.userEmail+"'");
               //temp.facID=(int)rs.getInt(1);
            }
            db.displayAllFaculty();
            
            //populating permanent faculty table in database
            db.populatePermanentFacultyTable((PermanentFaculty)ProfX);
            db.populatePermanentFacultyTable((PermanentFaculty)Heisenberg);
            db.displayAllPermanentFaculty();
            
            //populating visting faculty in table in database
            db.populateVisitingFacultyTable((VisitingFaculty)Wolvie);
            db.displayAllVisitingFaculty();
            
            
            //making A list of teachers who are teaching assembly
            List<Faculty> AssemblyTeachers=new ArrayList<>();
            AssemblyTeachers.add((Faculty)ProfX);
            AssemblyTeachers.add((Faculty)Heisenberg);
            
            //Making Assembly (Course)
            Course Assembly=new Course("EE213","Assembly",AssemblyTeachers,3);
            Assembly.studentsRegistered.add((Student)BlackWidow);
            Assembly.studentsRegistered.add((Student)JohnWick);
            
            //making a list of teachers who are teaching DB
            List<Faculty> DBTeachers=new ArrayList<>();
            DBTeachers.add((Faculty)Wolvie);
            DBTeachers.add((Faculty)ProfX);
            
            //Making DB (Course)
            Course DB=new Course("CS203","Database Systems",DBTeachers,3);
            DB.studentsRegistered.add((Student)JohnWick);
            DB.studentsRegistered.add((Student)Batman);
            
            
            //making a list of teachers who are teaching DS
            List<Faculty> DSTeachers=new ArrayList<>();
            DSTeachers.add((Faculty)Heisenberg);
            DSTeachers.add((Faculty)Wolvie);
            
            //Making DS (Course)
            Course DS=new Course("CS201","Data Structures",DSTeachers,3);
            DS.studentsRegistered.add((Student)Batman);
            DS.studentsRegistered.add((Student)BlackWidow);
            
            //populating Course Table in Database
            Course crsArr[]=new Course[3];
            crsArr[0]=Assembly;
            crsArr[1]=DB;
            crsArr[2]=DS;
            for(int i=0;i<3;i++){
                db.populateCourseTable(crsArr[i]);
            }           
            db.displayAllCourses();
            
            //Populating InstructorsTeachingTheCourse table in database
            for(int i=0;i<2;i++){
                db.populateInstructorsTeachingTheCourseTable(Assembly.facultyTeaching.get(i), Assembly);
            }
            for(int i=0;i<2;i++){
                db.populateInstructorsTeachingTheCourseTable(DB.facultyTeaching.get(i), DB);
            }
            for(int i=0;i<2;i++){
                db.populateInstructorsTeachingTheCourseTable(DS.facultyTeaching.get(i), DS);
            }
            
            db.displayAllInstructorsTeachingTheCourse();
            
            //populating StudentsTakingTheCourse table in database
            for(int i=0;i<Assembly.studentsRegistered.size();i++){
            db.populateStudentsTakingTheCourseTable(Assembly, Assembly.studentsRegistered.get(i));
            }
            for(int i=0;i<DB.studentsRegistered.size();i++){
            db.populateStudentsTakingTheCourseTable(DB, DB.studentsRegistered.get(i));
            }
            for(int i=0;i<DS.studentsRegistered.size();i++){
            db.populateStudentsTakingTheCourseTable(DS, DS.studentsRegistered.get(i));
            }
            
            db.displayAllStudentsTakingTheCourse();
            
            //Making CourseResult objects for every Course,Student pair
            
            /*float gpa, float[] qArr
                        ,float mid, float fin, Student s,Course c*/
            float[] qArr=new float[3];
            qArr[0]=7;
            qArr[1]=5;
            qArr[2]=9;
            CourseResult BWASM=new CourseResult("A",(float)4.00,qArr,(float)25,(float)80,(Student)BlackWidow,Assembly);
            CourseResult JWASM=new CourseResult("A+",(float)4.00,qArr,(float)30,(float)82,(Student)JohnWick,Assembly);
            
            CourseResult JWDB=new CourseResult("B",(float)3.00,qArr,(float)20,(float)65,(Student)JohnWick,DB);
            CourseResult BMDB=new CourseResult("A",(float)4.00,qArr,(float)30,(float)75,(Student)Batman,DB);
            
            CourseResult BMDS=new CourseResult("B-",(float)2.67,qArr,(float)15,(float)55,(Student)Batman,DS);
            CourseResult BWDS=new CourseResult("A-",(float)3.67,qArr,(float)22,(float)75,(Student)BlackWidow,DS);
           
            db.populateCourseResultTable(BWASM);
            db.populateCourseResultTable(JWASM);
            db.populateCourseResultTable(JWDB);
            db.populateCourseResultTable(BMDB);
            db.populateCourseResultTable(BMDS);
            db.populateCourseResultTable(BWDS);
            
            db.displayAllCourseResults();
            
            //populating ListOfCoursesTeachedByTeacher table in database
            Faculty [] facArr=new Faculty[3];
            
            facArr[0]=(Faculty)ProfX;
            facArr[1]=(Faculty)Wolvie;
            facArr[2]=(Faculty)Heisenberg;
            
            facArr[0].addCourse(Assembly.courseCode);
            facArr[0].addCourse(DB.courseCode);
            
            facArr[1].addCourse(DB.courseCode);
            facArr[1].addCourse(DS.courseCode);
            
            //System.out.println("Oye Yahan Dekh! "+ facArr[1].listOfCoursesTeaching.get(1).courseCode+" 2  "+facArr[1].listOfCoursesTeaching.get(1).courseName);
            facArr[2].addCourse(Assembly.courseCode);
            facArr[2].addCourse(DS.courseCode);
            
            for(int i=0;i<3;i++){
                if(i==0){
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], DB);
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], Assembly);
                }else if(i==1){
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], DB);
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], DS);
                }else if(i==2){
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], Assembly);
                    db.populateListOfCoursesTeachedByTeacherTable(facArr[i], DS);
                }
            }
            
            db.displayAllListOfCoursesTeachedByTeacher();
            
            //Making 3 mock Semesters
            
            Semester fall=new Fall("Fall2019","2019");
            fall.offeredCourses.add(Assembly);
            
            Semester spring=new Spring("Spring2019","2019");
            spring.offeredCourses.add(DB);
            
            Semester summer=new Summer("Summer2019","2019");
            summer.offeredCourses.add(DS); 
            
            //populating Semester table in database
            
            db.populateSemesterTable(fall);
            db.populateSemesterTable(spring);
            db.populateSemesterTable(summer);
            
            db.displayAllSemesters();
            
            //Making 3 mock CourseAssistant objects (PermanentFaculty,Course) pair.
            
            CourseAssistant asmTA=new CourseAssistant(Assembly,(PermanentFaculty)Heisenberg,(Student)Batman);
            CourseAssistant DBTA=new CourseAssistant(DB,(PermanentFaculty)ProfX,(Student)BlackWidow);
            CourseAssistant DSTA=new CourseAssistant(DS,(PermanentFaculty)Heisenberg,(Student)JohnWick);
            
            //populating CourseAssitsant table in database
            
            db.populateCourseAssistantTable(asmTA);
            db.populateCourseAssistantTable(DBTA);
            db.populateCourseAssistantTable(DSTA);
            
            db.displayAllCourseAssistants();
            
            //populating QuizMarksList table in database
            
            db.populateQuizMarksListTable(BWASM);
            db.populateQuizMarksListTable(JWASM);
            db.populateQuizMarksListTable(JWDB);
            db.populateQuizMarksListTable(BMDB);
            db.populateQuizMarksListTable(BMDS);
            db.populateQuizMarksListTable(BWDS);
            
            db.displayAllQuizMarksList();
            
            
            //populating Attendances table in database
            
            db.populateAttendance(Assembly, (Student)JohnWick);
            db.populateAttendance(Assembly, (Student)BlackWidow);
            db.populateAttendance(DB, (Student)JohnWick);
            db.populateAttendance(DB, (Student)Batman);
            db.populateAttendance(DS, (Student)Batman);
            db.populateAttendance(DS, (Student)BlackWidow);
            
            db.displayAttendanceTable();
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public void CheckAttendance(){
        
        int validation=db.validateForStudentLogin(you.userEmail,you.userPassword);
        if(validation==1){
        try{
            
            Scanner newIn=new Scanner(System.in);
            List<String> codeArr=new ArrayList<>();
            ResultSet rs=db.stmt.executeQuery("select distinct * from StudentsTakingTheCourse where StudentsTakingTheCourse.Student = '"+you.stdRollNo+"'");
            System.out.println("Your CourseCodes of Registered Courses are:");
            while(rs.next()){
                String coursecode=rs.getString(1);
                codeArr.add(coursecode);
            }
            
            for(int i=0;i<codeArr.size();i++){
                System.out.println(codeArr.get(i));
            }
            System.out.println("Please Enter your desired CourseCode:\n");
           //newIn.next();
            String enter=newIn.nextLine();
            System.out.println("Your entered courseCode is: "+enter);
            //newIn.next();
            rs=null;
            rs=db.stmt.executeQuery("Select LectureDate, attendanceStatus From Attendances Where RollNumber= '"+you.stdRollNo+"' and Code= '"+enter+"'");
            System.out.println("LectureDate\tattendanceStatus");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::CheckAttendance");
        }
        }else if(validation==0){
            System.out.println("You (Student) are not currently Logged in!");
        }
    }//Umar Azam
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//   
    public void UploadMarks(){
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~Upload Marks~~~~~~~~~~~~~~~~~~~\n");
        int validation=db.validateForFacultyLogin(fyou.userEmail, fyou.userPassword);
        if(validation==1){
            System.out.println("The list of courses that you're teaching are:\nCourseCode\tCourseName");
            //System.out.println("fyou.list.size() = "+fyou.listOfCoursesTeaching.size());
         //Yahan se   
            for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){//loop1
                System.out.print(fyou.listOfCoursesTeaching.get(i)+"\t");
                try{
                ResultSet rs=db.stmt.executeQuery("Select Name from Course where Code = '"+fyou.listOfCoursesTeaching.get(i)+"'");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                }
                catch(SQLException e){
                    System.out.println(e+" in FlexApplication::UploadMarks::loop1");
                }
            }
         //Yahan tk only printing
         boolean repeat=true;
         while(repeat){
            Scanner newIn=new Scanner (System.in);
            System.out.println("Enter your desired courseCode of which you want to upload marks: ");
            String enter=newIn.nextLine();
            if(fyou.listOfCoursesTeaching.contains(enter)){
            System.out.println("The course code you entered is: "+enter);
            System.out.println("What type of marks do you want to upload? Enter 'Quiz', 'Mid' or 'Final'");
            String type=newIn.nextLine();
            if(type.equalsIgnoreCase("quiz")){
                //call quiz func here
                QuizMarks(enter);
            }else if(type.equalsIgnoreCase("mid")){
                //call mid func here
                MidMarks(enter);
            }else if(type.equalsIgnoreCase("final")){
                //call final func here
                FinalMarks(enter);
            }else{}
            System.out.println("If you want to upload marks of another student, type 'true'; else type 'false'");
            repeat=newIn.nextBoolean();
            }else{
                System.out.println("The course code you entered is: "+enter+" doesn't match any CourseCode that you're faculty is teaching");
                repeat=false;
            }
        }
        }else if(validation==0){
            System.out.println("You (Faculty) aren't logged in");
        }
    } //Umar Azam
    private void QuizMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            //Student temp=null;
            //String tempStr=null;
            if(rollNums.contains(student)){
            //rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            //while(rs.next()){
               // tempStr=rs.getString(1);
            //}
            //temp=db.getSpecificStudent(tempStr, this);
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float[] tempQuiz=new float[3];
            System.out.print("Enter Quiz1 Marks: "); tempQuiz[0]=newIn.nextFloat();
            System.out.print("Enter Quiz2 Marks: "); tempQuiz[1]=newIn.nextFloat();
            System.out.print("Enter Quiz3 Marks: "); tempQuiz[2]=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update QuizMarksList set marksQ1 = "+tempQuiz[0]+
                                                     ", marksQ2 = "+tempQuiz[1]+", marksQ3= "+tempQuiz[2]+
                                                     " where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("Your Quiz marks for "+student+" have been uploaded!");
            String msg;
            msg = "Your teacher has uploaded your Quiz marks!";
            rs2=db.stmt.executeUpdate("Insert Into NotificationsForStudents values( '"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has uploaded your Quiz marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select * From QuizMarksList where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("RollNumber\tCourseCode\tQuiz1\tQuiz2\tQuiz3");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getFloat(4)+"\t"+rs.getFloat(5));
            }
            }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::QuizMarks");
        }
    }//Umar Azam
    private void MidMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            /*Student temp=null;
            String tempStr=null;*/
            if(rollNums.contains(student)){
            /*rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            while(rs.next()){
                tempStr=rs.getString(1);
            }
            temp=db.getSpecificStudent(tempStr, this);*/
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float tempMid=0;
            System.out.print("Enter Mid Marks: "); tempMid=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update CourseResult set midMarks = "+tempMid+" where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("Your Mid marks for "+student+" have been uploaded!");
            String msg;
            msg = "Your teacher has uploaded your Mid marks!";
            rs2=db.stmt.executeUpdate("Insert Into NotificationsForStudents values( '"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has uploaded your Mid marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select RollNumber, Code, midMarks From CourseResult where RollNumber ='"+student+"' and Code= '"+enter+"'");
            while(rs.next()){
                System.out.println("RollNumber: "+rs.getString(1)+"\nCourseCode: "+rs.getString(2)+"\nmidMarks: "+rs.getString(3));
            }
            }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::MidMarks");
        }
    }//Umar Azam
    private void FinalMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            /*Student temp=null;
            String tempStr=null;*/
            if(rollNums.contains(student)){
            /*rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            while(rs.next()){
                tempStr=rs.getString(1);
            }
            temp=db.getSpecificStudent(tempStr, this);*/
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float tempFinal=0;
            System.out.print("Enter Final Marks: "); tempFinal=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update CourseResult set finalMarks = "+tempFinal+" where RollNumber = '"+student+"' and Code = '"+enter+"'");
            rs2=0;
            System.out.println("Your Final marks for "+student+" have been uploaded!");
            String msg=null;
            msg="Your teacher has uploaded you Final marks!";
            rs2=db.stmt.executeUpdate("Insert into NotificationsForStudents values('"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has uploaded your Final marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select RollNumber, Code, finalMarks From CourseResult where RollNumber ='"+student+"' and Code= '"+enter+"'");
            System.out.println("RollNumber\tCourseCode\tfinalMarks");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
             }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::FinalMarks");
        }
    }//Umar Azam
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void EditMarks(){
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~Edit Marks~~~~~~~~~~~~~~~~~~~\n");
        int validation=db.validateForFacultyLogin(fyou.userEmail, fyou.userPassword);
        if(validation==1){
            System.out.println("The list of courses that you're teaching are:\nCourseCode\tCourseName");
            //System.out.println("fyou.list.size() = "+fyou.listOfCoursesTeaching.size());
         //Yahan se
            for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){//loop1
                System.out.print(fyou.listOfCoursesTeaching.get(i)+"\t");
                try{
                ResultSet rs=db.stmt.executeQuery("Select Name from Course where Code = '"+fyou.listOfCoursesTeaching.get(i)+"'");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                }
                catch(SQLException e){
                    System.out.println(e+" in FlexApplication::EditMarks::loop1");
                }
            }
         //Yahan tk only printing
         boolean repeat=true;
         while(repeat){
            Scanner newIn=new Scanner (System.in);
            System.out.println("Enter your desired courseCode of which you want to edit marks: ");
            String enter=newIn.nextLine();
            if(fyou.listOfCoursesTeaching.contains(enter)){
            System.out.println("The course code you entered is: "+enter);
            System.out.println("What type of marks do you want to edit? Enter 'Quiz', 'Mid' or 'Final'");
            String type=newIn.nextLine();
            if(type.equalsIgnoreCase("quiz")){
                //call quiz func here
                QuizEditMarks(enter);
            }else if(type.equalsIgnoreCase("mid")){
                //call mid func here
                MidEditMarks(enter);
            }else if(type.equalsIgnoreCase("final")){
                //call final func here
                FinalEditMarks(enter);
            }else{}
            System.out.println("If you want to edit marks of another student, type 'true'; else type 'false'");
            repeat=newIn.nextBoolean();
            }else{
                System.out.println("The course code you entered is: "+enter+" doesn't match any CourseCode that you're faculty is teaching");
                repeat=false;
            }
        }
        }else if(validation==0){
            System.out.println("You (Faculty) aren't logged in");
        }
    }//Umar Azam
    private void QuizEditMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            /*Student temp=null;
            String tempStr=null;*/
            if(rollNums.contains(student)){
           /* rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            while(rs.next()){
                tempStr=rs.getString(1);
            }
            temp=db.getSpecificStudent(tempStr, this);*/
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float[] tempQuiz=new float[3];
            System.out.print("Enter Quiz1 Marks: "); tempQuiz[0]=newIn.nextFloat();
            System.out.print("Enter Quiz2 Marks: "); tempQuiz[1]=newIn.nextFloat();
            System.out.print("Enter Quiz3 Marks: "); tempQuiz[2]=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update QuizMarksList set marksQ1 = "+tempQuiz[0]+
                                                     ", marksQ2 = "+tempQuiz[1]+", marksQ3= "+tempQuiz[2]+
                                                     " where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("Your Quiz marks for "+student+" have been edited!");
            String msg;
            msg = "Your teacher has edited your Quiz marks!";
            rs2=db.stmt.executeUpdate("Insert Into NotificationsForStudents values( '"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has edited your Quiz marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select * From QuizMarksList where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("RollNumber\tCourseCode\tQuiz1\tQuiz2\tQuiz3");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getFloat(4)+"\t"+rs.getFloat(5));
            }
            }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::QuizEditMarks");
        }
    }//Umar Azam
    private void MidEditMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            /*Student temp=null;
            String tempStr=null;*/
            if(rollNums.contains(student)){
            /*rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            while(rs.next()){
                tempStr=rs.getString(1);
            }
            temp=db.getSpecificStudent(tempStr, this);*/
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float tempMid=0;
            System.out.print("Enter Mid Marks: "); tempMid=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update CourseResult set midMarks = "+tempMid+" where RollNumber = '"+student+"' and Code = '"+enter+"'");
            System.out.println("Your Mid marks for "+student+" have been edited!");
            String msg;
            msg = "Your teacher has edited your Mid marks!";
            rs2=db.stmt.executeUpdate("Insert Into NotificationsForStudents values( '"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has edited your Mid marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select RollNumber, Code, midMarks From CourseResult where RollNumber ='"+student+"' and Code= '"+enter+"'");
            while(rs.next()){
                System.out.println("RollNumber: "+rs.getString(1)+"\nCourseCode: "+rs.getString(2)+"\nmidMarks: "+rs.getString(3));
            }
            }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::MidEditMarks");
        }
    }//Umar Azam
    private void FinalEditMarks(String enter){
        try{
            System.out.println("The Students enrolled in your entered courseCode are:");
            ResultSet rs=db.stmt.executeQuery("Select Student From StudentsTakingTheCourse where Code= '"+enter+"'");
            System.out.println("Student's RollNumber");
            List<String> rollNums=new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString(1));
                rollNums.add(rs.getString(1));
            }
            Scanner newIn=new Scanner(System.in);
            System.out.println("Please enter your desired student's RollNumber:");
            String student=newIn.nextLine();
            /*Student temp=null;
            String tempStr=null;*/
            if(rollNums.contains(student)){
            /*rs=db.stmt.executeQuery("Select Email from Student where RollNumber= '"+student+"'");
            while(rs.next()){
                tempStr=rs.getString(1);
            }
            temp=db.getSpecificStudent(tempStr, this);*/
            System.out.println("Your entered RollNumber is: "+student);
            rs=null;
            float tempFinal=0;
            System.out.print("Enter Final Marks: "); tempFinal=newIn.nextFloat();
            int rs2=db.stmt.executeUpdate("update CourseResult set finalMarks = "+tempFinal+" where RollNumber = '"+student+"' and Code = '"+enter+"'");
            rs2=0;
            System.out.println("Your Final marks for "+student+" have been edited!");
            String msg;
            msg = "Your teacher has edited your Final marks!";
            rs2=db.stmt.executeUpdate("Insert Into NotificationsForStudents values( '"+student+"','"+msg+"')");
            /*if(this.observers.contains(temp)){
                for(int i=0;i<this.observers.size();i++){
                    if(temp==this.observers.get(i)){
                        this.observers.get(i).update("Your teacher of Course "+enter+" has edited your Final marks!");
                    }
                }                
            }*/
            rs=db.stmt.executeQuery("Select RollNumber, Code, finalMarks From CourseResult where RollNumber ='"+student+"' and Code= '"+enter+"'");
            System.out.println("RollNumber\tCourseCode\tfinalMarks");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
            }else{
                System.out.println("Your entered RollNumber is: "+student+" doesn't match any student's RollNumber that you're teaching in "+enter);
            }
        }
        catch(SQLException e){
            System.out.println(e+" in FlexApplication::FinalEditMarks");
        }
    }//Umar Azam
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public int StudentLogin(){
        System.out.println("~~~~~~~~Welcome to Student Login~~~~~~~~");
        String tempEmail;
        String tempPass;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nPlease enter your credentials\n");
        System.out.println("Enter Email: ");
        tempEmail = scanner.nextLine();
        
        System.out.println("Enter Password: ");
        tempPass = scanner.nextLine();
     
        int returnCheck;
        dbConnectivity db = new dbConnectivity();
        returnCheck = db.validateForStudentLogin(tempEmail, tempPass);
        
        if (returnCheck == 1){
            System.out.println("\n***Login Succesful***\n");          
            you = db.getSpecificStudent(tempEmail,this); 
            
            String myTempNot=null;
            try{
            ResultSet rs=db.stmt.executeQuery("Select Notification From NotificationsForStudents where RollNumber=  '"+you.stdRollNo+"'");
            while(rs.next()){
                myTempNot=rs.getString(1);
                you.update(myTempNot);
            }
            if(you.Notifications.size()>0){
                System.out.println("~~~~~~~~~~You have following notifications~~~~~~~~~~\n");
                for(int i=0;i<you.Notifications.size();i++){
                    System.out.println("Notification #"+(i+1)+": "+you.Notifications.get(i));
                    //you.Notifications.remove(you.Notifications.get(i));
                }
            }
            int rss=db.stmt.executeUpdate("Delete From NotificationsForStudents where RollNumber= '"+you.stdRollNo+"'");
            }
            catch(SQLException e){
                System.out.println(e+" in FlexApplication::StudentLogin");
            }
            return 1;
        }
        else if (returnCheck == 0){
            System.out.println("\n***Login Failed. Wrong Credentials Entered***\n");
            return 0;
        }
        return 0;
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public void viewCurrentUserDetails(){
        System.out.println("Your Details: \n");
        System.out.println("Email: " + you.userEmail);
        System.out.println("Name: " + you.userName);
        System.out.println("RollNo: " + you.stdRollNo);
        System.out.println("Password: " + you.userPassword);
        System.out.println("ContactNo: " + you.userContactNo);
        System.out.println("Address: " + you.userAddress);
        System.out.println("Blood Group: " + you.userBloodGrp);
        System.out.println("Gender: " + you.Gender);
        System.out.println("Nationality: " + you.userNatonality);
        System.out.println("Campus: " + you.userCampus);
        System.out.println("CGPA: " + you.stdCGPA);
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public void Logout(){
        if (you != null){
            you = null;
            System.out.println("Student has been logged out!");
        }
        
        if (fyou != null){
            fyou = null;
            System.out.println("Faculty has been logged out!");
        }
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public int FacultyLogin(){
        
        System.out.println("~~~~~~~~Welcome to Faculty Login~~~~~~~~");
        String tempEmail;
        String tempPass;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nPlease enter your credentials\n");
        System.out.println("Enter Email: ");
        tempEmail = scanner.nextLine();
        
        System.out.println("Enter Password: ");
        tempPass = scanner.nextLine();
     
        int returnCheck;
        dbConnectivity db = new dbConnectivity();
        returnCheck = db.validateForFacultyLogin(tempEmail, tempPass);
        
        if (returnCheck == 1){
            System.out.println("\n***Login Succesful***\n");          
            fyou = db.getSpecificFaculty(tempEmail); 
            return 1;
        }
        else if (returnCheck == 0){
            System.out.println("\n***Login Failed. Wrong Credentials Entered***\n");
            return 0;
        }
        return 0;
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void viewCurrentFacultyDetails(){
        System.out.println("\nFaculty Details: \n");
        System.out.println("Email: " + fyou.userEmail);
        System.out.println("Name: " + fyou.userName);
        System.out.println("FacID: " + fyou.facID);
        System.out.println("Password: " + fyou.userPassword);
        System.out.println("ContactNo: " + fyou.userContactNo);
        System.out.println("Address: " + fyou.userAddress);
        System.out.println("Blood Group: " + fyou.userBloodGrp);
        System.out.println("Gender: " + fyou.Gender);
        System.out.println("Nationality: " + fyou.userNatonality);
        System.out.println("Campus: " + fyou.userCampus);
        for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){
            System.out.println(fyou.listOfCoursesTeaching.get(i));
        }
    } //Hassaan Zulqarnain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void changeUserInfo(){
        Scanner scanner = new Scanner(System.in);
        dbConnectivity db = new dbConnectivity();
        
        System.out.println("Enter old password:");
        String oldPass = scanner.nextLine();
        
        System.out.println("Enter new password:");
        String newPass = scanner.nextLine();
        
        System.out.println("Confirm new password:");
        String confirmPass = scanner.nextLine();
        
        if (you != null){
            if (newPass.equals(confirmPass)){
                if (you.userPassword.equals(oldPass)){
                    you.userPassword = newPass;
                    db.updateUserPassword(newPass, you.userEmail);
                    System.out.println("Password changed Successfully");
                }
                else
                    System.out.println("You entered wrong old password. Stop hacking.\n");
            }
            else 
            {
                System.out.println("Your new password and confirm passowrd dont match.\n");
            }
        }
        
        else{
            System.out.println("User isnt logged in. Do you want to login first? (Y/N): ");
        }
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
     public void changeFacultyInfo(){
        Scanner scanner = new Scanner(System.in);
        dbConnectivity db = new dbConnectivity();
        
        System.out.println("Enter old password:");
        String oldPass = scanner.nextLine();
        
        System.out.println("Enter new password:");
        String newPass = scanner.nextLine();
        
        System.out.println("Confirm new password:");
        String confirmPass = scanner.nextLine();
        
        if (fyou != null){
            if (newPass.equals(confirmPass)){
                if (fyou.userPassword.equals(oldPass)){
                    fyou.userPassword = newPass;
                    db.updateUserPassword(newPass, fyou.userEmail); 
                    System.out.println("Password changed Successfully");
                }
                else
                    System.out.println("You entered wrong old password. Stop hacking.\n");
            }
            else 
            {
                System.out.println("Your new password and confirm passowrd dont match.\n");
            }
        }
        
        else
        {
            System.out.println("User isnt logged in. Do you want to login first? (Y/N): ");
        }
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
     public void checkTranscript(){
         int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
         if(validation==1){
             try{
                 ResultSet rs=db.stmt.executeQuery("Select sum(CourseResult.GPA* CourseList.CreditHours) from CourseResult join CourseList ON (CourseResult.Code = CourseList.Code) Where RollNumber = '"+you.stdRollNo+"'");
                 System.out.print("SGPA: ");
                 float totalGPA=0; float totalCrdHrs=0;
                 while(rs.next()){
                     totalGPA=rs.getFloat(1);
                 }
                 rs=db.stmt.executeQuery("Select sum(CourseList.CreditHours) from CourseResult join CourseList ON (CourseResult.Code = CourseList.Code) Where RollNumber = '"+you.stdRollNo+"'");
                 while(rs.next()){
                     totalCrdHrs=rs.getFloat(1);
                 }
                 float SGPA=totalGPA/totalCrdHrs;
                 System.out.println(SGPA);
                 rs=db.stmt.executeQuery("Select CourseList.Code,CourseList.Name,CourseList.CreditHours,CourseResult.GPA from CourseResult join CourseList ON (CourseResult.Code = CourseList.Code) Where RollNumber = '"+you.stdRollNo+"'");
                 System.out.println("CourseCode\tCourseName\tCreditHours\tGPA");
                 while(rs.next()){
                     System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4));
                 }
             }
             catch(SQLException e){
                 System.out.println(e+" in FlexApplication::checkTranscript");
             }
         }else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
         }
     }//Basim Ahmed
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//     
     public void FeeDetails(){
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
            if(validation==1){
                try{
                    ResultSet rs = db.stmt.executeQuery("Select CourseList.Code,CourseList.Name,CourseList.CreditHours, (CourseList.CreditHours*17500) from StudentsTakingTheCourse join CourseList on (CourseList.Code = StudentsTakingTheCourse.Code) where StudentsTakingTheCourse.Student='"+you.stdRollNo+"'");
                    System.out.println("CourseCode\tCourseName\tCreditHours\tFee");
                    while(rs.next()){
                    System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4));
                    }    
                }
                catch(SQLException e){
                  System.out.println(e+" in FlexApplication:: in FeeDetails");  
                }
            }
            else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
            }
}//Basim Ahmed
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    public void PrintChallan(){
     int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
            if(validation==1){
                try{
                    String due=null;
                    ResultSet rs = db.stmt.executeQuery("Select FeeStatus from FeeChallan where RollNumber = '"+you.stdRollNo+"'");
                    while(rs.next()){
                    due = rs.getString(1);
                    }
                    if(due.equalsIgnoreCase("Pending")){
                    rs = db.stmt.executeQuery("Select [User].Name,sum(CourseList.CreditHours)*17500,FeeChallan.DueDate from StudentsTakingTheCourse join CourseList on (CourseList.Code = StudentsTakingTheCourse.Code) join Student on (StudentsTakingTheCourse.Student = Student.RollNumber) join [User] on (Student.Email = [User].Email) join Feechallan on (Student.RollNumber = FeeChallan.RollNumber) where Student.RollNumber ='"+you.stdRollNo+"' group by [User].Name, FeeChallan.DueDate");
                    //System.out.println(rs+" <-yeh value rs ki hy");
                    System.out.println("Name:\tFee\tDueDate");
                    while(rs.next()){
                    System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getDate(3));
                    }  
                    int rs1=db.stmt.executeUpdate("update FeeChallan set FeeStatus = 'Paid' where RollNumber = '"+you.stdRollNo+"'");
                }
                    else{
                    System.out.println("Fee Challan has Already been paid");
                    }
                }
                catch(SQLException e){
                  System.out.println(e+" in FlexApplication::Print Fee Challan");  
                }
            }
            else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
            }
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//        
//======================================================================================//    
       
    public void addAttendance(){
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~Add Student Attendance~~~~~~~~~~~~~~~~~~~\n");
        String student;
        int validation=db.validateForFacultyLogin(fyou.userEmail, fyou.userPassword);
        if(validation==1){
            System.out.println("The list of courses that you're teaching are:\nCourseCode\tCourseName");
            
            for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){//loop1
                System.out.print(fyou.listOfCoursesTeaching.get(i)+"\t");
                try{
                    ResultSet rs=db.stmt.executeQuery("Select Name from Course where Code = '"+fyou.listOfCoursesTeaching.get(i)+"'");
                    while(rs.next()){
                        System.out.println(rs.getString(1));
                    }
                }
                catch(SQLException e){
                    System.out.println(e+" in FlexApplication::addAttendance::loop1");
                }
            }
         
            boolean repeat=true;
            while(repeat){
                Scanner newIn=new Scanner (System.in);
                System.out.println("Enter your desired courseCode of which you want to add attendance: ");
                String enter=newIn.nextLine();
                if(fyou.listOfCoursesTeaching.contains(enter)){
                    System.out.println("The course code you entered is: "+enter);
                    try{
                        ResultSet rs=db.stmt.executeQuery("select Student from StudentsTakingTheCourse where Code = '"+enter+"'");
                        System.out.println("The list of students in: "+enter+" are as follows:");
                        System.out.println("RollNumber");
                            while(rs.next()){
                            System.out.println(rs.getString(1));
                            }
                        System.out.print("Enter the rollNumber of which you want to add attendance: ");
                        student=newIn.nextLine();
                        System.out.println("The rollNumber you entered is: "+student);
                        System.out.print("Enter the lectureDate in (yyyy-MM-dd) format: ");
                        String myDate=newIn.nextLine();
                        System.out.print("Enter the attendanceStatus ('P' for present, 'A' for absent and 'L' for late: ");
                        String status=newIn.nextLine();
                        System.out.println("The attendanceStatus you entered is: "+status);
                        
                        int result = db.validateAtteandance(enter, student, myDate, status);

                        if(result == 1){
                            //int rs2=db.stmt.executeUpdate("Insert into Attendances values( '"+enter+"','"+student+"','"+myDate+"','"+status+"')");             
                            System.out.println("The attendance of "+student+" on lecture date "+myDate+" in course "+enter+" has been added!");
                        }
                        else{
                            System.out.println("The attendance of "+student+" on lecture date "+myDate+" in course "+enter+" already exists!");
                        }

                        rs=null;
                        rs=db.stmt.executeQuery("Select * From Attendances where Code = '"+enter+"' and RollNumber = '"+student+"'");
                        System.out.println("CourseCode\tRollNumber\tlectureDate\tattendanceStatus");
                        while(rs.next()){
                            System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e+" in FlexApplication::addAttendance");
                    }
                }
                System.out.println("If you want to add attendance of another student, type 'true'; else type 'false'");
                repeat=newIn.nextBoolean();
            }
        }
        else if (validation == 0){
            System.out.println("\nWrong Login Credentials.\n");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }//Hassaan
    
    void generateGrade(){
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~Generate Grade of Student~~~~~~~~~~~~~~~~~~~\n");
        int validation=db.validateForFacultyLogin(fyou.userEmail, fyou.userPassword);
        
        if(validation == 1){
            System.out.println("The list of courses that you're teaching are:\nCourseCode\tCourseName");
            
            for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){//loop1
                System.out.print(fyou.listOfCoursesTeaching.get(i)+"\t");
                try{
                    ResultSet rs=db.stmt.executeQuery("Select Name from Course where Code = '"+fyou.listOfCoursesTeaching.get(i)+"'");
                    while(rs.next()){
                        System.out.println(rs.getString(1));
                    }
                }
                catch(SQLException e){
                    System.out.println(e+" in FlexApplication::generateGrade::loop1");
                }
            }
            boolean repeat=true;
            while(repeat){
                Scanner newIn=new Scanner (System.in);
                System.out.println("Enter your desired courseCode of which you want to Generate Grade: ");
                String enter=newIn.nextLine();
                if(fyou.listOfCoursesTeaching.contains(enter)){
                    System.out.println("The course code you entered is: "+enter);
                    try{
                        ResultSet rs=db.stmt.executeQuery("select Student from StudentsTakingTheCourse where Code = '"+enter+"'");
                        System.out.println("The list of students in: "+enter+" are as follows:");
                        System.out.println("RollNumber");
                            while(rs.next()){
                            System.out.println(rs.getString(1));
                            }
                        System.out.print("Enter the rollNumber of which you want to see grade of: ");
                        String rollno = newIn.nextLine();
                        System.out.println("The rollNumber you entered is: "+rollno);
                        int totalmarks = db.getTotalMarks(enter, rollno);
                        if(totalmarks >=90){
                            System.out.println("Your grade is A+");
                        }
                        if(totalmarks >=85 && totalmarks <90){
                            System.out.println("Your grade is A");
                        }
                        if(totalmarks >=80 && totalmarks <85){
                            System.out.println("Your grade is A-");
                        }
                        if(totalmarks >=75 && totalmarks <80){
                            System.out.println("Your grade is B+");
                        }
                        if(totalmarks >=70 && totalmarks <75){
                            System.out.println("Your grade is B");
                        }
                        if(totalmarks >=65 && totalmarks <70){
                            System.out.println("Your grade is B-");
                        }
                        if(totalmarks >=60 && totalmarks <65){
                            System.out.println("Your grade is C+");
                        }
                        if(totalmarks >=55 && totalmarks <60){
                            System.out.println("Your grade is C");
                        }
                        if(totalmarks >=50 && totalmarks <55){
                            System.out.println("Your grade is C-");
                        }
                        if(totalmarks >=45 && totalmarks <50){
                            System.out.println("Your grade is D");
                        }
                        if(totalmarks <45 && totalmarks > 0){
                            System.out.println("Your grade is F");
                        }
                        if(totalmarks < 0){
                            System.out.println("Error,while connecting to database");
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e+" in FlexApplication::addAttendance");
                    }
                        
                }
                System.out.println("If you want to Generate Grade of another student, type 'true'; else type 'false'");
                repeat=newIn.nextBoolean();
            }
        }

        else{
            System.out.println("Login Credentials were Incorrect");
        }
    }//Hassaan

    //=========================================================================================================
    void RegisterCourse(){
        
        System.out.println("Future Possbile Courses.");
        this.future_registerable_courses();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What course do you want to register in?");
        String courseYouWant = scanner.nextLine();
        
        int bobo = db.addStudentInCourse(courseYouWant, you);
        switch (bobo) {
            case 1:
                System.out.println("Course Registered Successfully.\n");
                break;
            case 2:
                System.out.println("Error Occured, Student already present");
                break;
            case 0:
                System.out.println("Error!!. ");
                break;
            case 3:
                System.out.println("Course doesnt exist. Try a different course code.\n");
                break;
            default:
                System.out.println(bobo);
                System.out.println("Error!!. Something went wrong.");
                break;
        }
    }//Omer
    
    void dropCourse()
    {
        System.out.println("Possbile courses that you can drop.");
        this.current_registered_courses();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which course do you want to drop?");
        String courseYouWantToDrop = scanner.nextLine();
        
        int bobo = db.drop_Course(courseYouWantToDrop, you);
        switch (bobo) 
        {
            case 1:
                System.out.println("Course is Dropped Successfully.\n");
                break;
            case 2:
                System.out.println("Error Occured, Student is not present in this course");
                break;
            case 0:
                System.out.println("Error!!. Course does not exist.");
                break;
            default:
                System.out.println(bobo);
                System.out.println("Error!!. Something went wrong.");
                break;

        }//Omer
    }
    
    public void viewMidMarks() 
    {
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
        if(validation==1)
        {
            try
            {
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter course code to check obtained marks in MIDS");
                String CourseCode = scanner.nextLine();
                System.out.println("-----Obtained marks in MIDS-----");

                System.out.println("Mids Marks");
                
                ResultSet rs=db.stmt.executeQuery("select midMarks from CourseResult where RollNumber= '"+you.stdRollNo+"' and Code = '"+CourseCode+"'");
                while(rs.next())
                 {

                    System.out.println(rs.getInt(1));

                 }
                //System.out.println("int rs returned "+rs);

            }
            catch(SQLException e){
                System.out.println(e+" In Student:: Check Mid Marks");
            }
        }
        else if(validation==0){
            System.out.println("You (Student) are not currently logged in!");
        }

    }
    public void viewMarks() 
    {
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
        if(validation==1)
        {
            try
            {
                ResultSet rs2 = db.stmt.executeQuery("Select Code from StudentsTakingTheCourse Where Student = '" + you.stdRollNo + "'");
                System.out.println("Course you are registered in.");
                while(rs2.next()){
                    System.out.println(rs2.getString(1));
                }
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter course code to view obtained marks");
                String CourseCode = scanner.nextLine();
                System.out.println("-----Obtained marks-----");
                

               // System.out.println("Mids Marks");
                
                ResultSet rs=db.stmt.executeQuery("select QuizMarksList.marksQ1,QuizMarksList.marksQ2,QuizMarksList.marksQ3, CourseResult.midMarks,CourseResult.finalMarks from CourseResult join QuizMarksList on CourseResult.RollNumber=QuizMarksList.RollNumber where CourseResult.RollNumber='"+you.stdRollNo+"' and CourseResult.Code='"+CourseCode+"'");
                System.out.println("Quiz1\tQuiz2\tQuiz3\tMid\tFinal");
                while(rs.next())
                 {

                    System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5));

                 }
            }
            catch(SQLException e)
            {
                System.out.println(e+" In Student:: view Marks");
            }
                
        }
        else if(validation==0)
        {
            System.out.println("You (Student) are not currently logged in!");
        }
            
    }
        
    void viewQuizMarks()
    {
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
        if(validation==1)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter course code to check obtained marks in QUIZES");
                String CourseCode = scanner.nextLine();
                System.out.println("-----Obtained marks in Quizes-----");
                ResultSet rs=db.stmt.executeQuery("select marksQ1,marksQ2,marksQ3 from QuizMarksList where RollNumber='"+you.stdRollNo+"' and Code='"+CourseCode+"'");
                while(rs.next())
                 {

                    System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3));

                 }

            }
            catch(SQLException e)
            {
                System.out.println(e+" In Student:: Quiz Marks");
            }
            
        }
        else if(validation==0){
            System.out.println("You (Student) are not currently logged in!");
        }
    }
    
    void viewFinalMarks()
    {
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
        if(validation==1)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter course code to check obtained marks in FINALS");
                String CourseCode = scanner.nextLine();
                System.out.println("-----Obtained marks in FINALS-----");
                ResultSet rs=db.stmt.executeQuery("select finalMarks from CourseResult where RollNumber= '"+you.stdRollNo+"' and Code = '"+CourseCode+"'");
                while(rs.next())
                 {

                    System.out.println(rs.getInt(1));

                 }

            }
            catch(SQLException e)
            {
                System.out.println(e+" In Student:: FINALS Marks");
            }
            
        }
        else if(validation==0){
            System.out.println("You (Student) are not currently logged in!");
        }
    }
    
    
    //===================================================================================================
    public void future_registerable_courses(){
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
            if(validation==1){
                try{
                    ResultSet rs = db.stmt.executeQuery("Select Code,Name,CreditHours,[Type] from CourseList Except select CourseList.Code,CourseList.Name,CourseList.CreditHours,CourseList.[Type] from StudentsTakingTheCourse join CourseList On (CourseList.Code = StudentsTakingTheCourse.Code)where StudentsTakingTheCourse.Student = '"+you.stdRollNo+"'");
                    System.out.println("CourseCode\tCourseName\tCreditHours\tType");
                    while(rs.next()){
                    System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
                    }    
                }
                catch(SQLException e){
                  System.out.println(e+" in FlexApplication:: in FeeDetails");  
                }
            }
            else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
            }
    }
    public void current_registered_courses(){
        int validation=db.validateForStudentLogin(you.userEmail, you.userPassword);
            if(validation==1){
                try{
                    ResultSet rs = db.stmt.executeQuery("select Distinct StudentsTakingTheCourse.Code,CourseList.Name,CourseList.CreditHours,CourseList.[Type] from StudentsTakingTheCourse join CourseList On (CourseList.Code = StudentsTakingTheCourse.Code) where StudentsTakingTheCourse.Student = '"+you.stdRollNo+"'");
                    System.out.println("Course Code\tCourseName\tCredit Hours\tType");
                    while(rs.next()){
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
                    }    
                }
                catch(SQLException e){
                  System.out.println(e+" in FlexApplication:: in currentRegisteredCoursses");  
                }
            }
            else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
            }
        }
    public void see_students_attendance(){
        int validation=db.validateForFacultyLogin(fyou.userEmail, fyou.userPassword);
            if(validation==1){
                try{
                    System.out.println("The list of courses that you're teaching are:\nCourseCode\tCourseName");
            //System.out.println("fyou.list.size() = "+fyou.listOfCoursesTeaching.size());
         //Yahan se   
            for(int i=0;i<fyou.listOfCoursesTeaching.size();i++){//loop1
                System.out.print(fyou.listOfCoursesTeaching.get(i)+"\t");
                try{
                ResultSet rs=db.stmt.executeQuery("Select Name from Course where Code = '"+fyou.listOfCoursesTeaching.get(i)+"'");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                }
                catch(SQLException e){
                    System.out.println(e+" in FlexApplication::UploadMarks::loop1");
                }
            }
         //Yahan tk only printing
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("enter code for the course : ");
                    String course_code = scanner.nextLine();
                    
                    ResultSet rs = db.stmt.executeQuery("Select RollNumber,LectureDate,attendanceStatus from Attendances where Code = '"+course_code+"' group by RollNumber,LectureDate,attendanceStatus order by LectureDate ASC");
                    System.out.println("Roll_no\tLecture_date\tAttendance");
                    while(rs.next()){
                    System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
                    }    
                }
                catch(SQLException e){
                  System.out.println(e+" in FlexApplication:: in see_students_attendance");  
                }
            }
            else if(validation==0){
             System.out.println("You (Student) are not currently logged in!");
            }
    }


    
}
