/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flex;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


/**
 *
 * @author Aldehyde
 */
public class dbConnectivity {


    Connection con;
    Statement stmt;
    CallableStatement myStmt;

    dbConnectivity() //cons
    {
        try
        {
             String s = "jdbc:sqlserver://localhost:1433;databaseName=FLex";
             con=DriverManager.getConnection(s,"flexx","123");


            stmt = con.createStatement();
            myStmt=null;

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateStudent(Student obj) throws SQLException{
        try{

           int  rs=stmt.executeUpdate("Insert into Student values ( '"+obj.userEmail+"' , '"+ obj.stdRollNo+"'   ,   "+ obj.stdCGPA+"  )  " );

            System.out.println("int rs returned "+rs);

        }
        catch(SQLException e){
            System.out.println(e+" in dbonnectivity::populateStudent");
        }

    }
    void displayAllStudents(){
        System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the Student table~~~~~~~~~~~~~~~~~~\n");
        try
        {
            ResultSet rs=stmt.executeQuery("select * from Student");
            System.out.println("Email\tRoll Num\tCGPA");
             while(rs.next())
             {

                System.out.println(rs.getString(1)+"    "+rs.getString(2)+"     "+rs.getFloat(3));

             }
        }

        catch(SQLException e)
        {
            System.out.println(e+" in dbConnectivity::displayAllStudents");
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateAttendance(Course crs, Student std){
        try{
            int rs= 0;
            Date date=new Date();
            for(int i=1;i<=28;i++){
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-"+i);
                String strDate=formatter.format(date);
                if(i==1 || i==2){
                rs=stmt.executeUpdate("Insert into Attendances values( '"+crs.courseCode+"' , '"+
                                                                std.stdRollNo+"' , '"+strDate+"' , 'NA' )");
                }else if(i>=25){
                    rs=stmt.executeUpdate("Insert into Attendances values( '"+crs.courseCode+"' , '"+
                                                                std.stdRollNo+"' , '"+strDate+"' , 'A' )");
                }else {
                   rs= stmt.executeUpdate("Insert into Attendances values( '"+crs.courseCode+"' , '"+
                                                                std.stdRollNo+"' , '"+strDate+"' , 'P' )");
                }
            }

        }
        catch(SQLException e){
            System.out.println(e+" in dbCOnnectivity::populateAttendance");
        }
    }
    void displayAttendanceTable(){
       // System.out.println("Inside dAT");
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the Attendances table~~~~~~~~~~~~~~~~~~\n");
          //  System.out.println("Inside try");
            var rs=stmt.executeQuery("Select * From Attendances");
            //System.out.println("rs executed"+rs);
            /*Code varchar(50),
	RollNumber varchar(50),
	LectureDate Date, --YYYY-MM-DD
	attendanceStatus char(2),
*/
            System.out.println("CourseCode\tRollNumber\tLectureDate\tAttendanceStatus");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAttendanceTable");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateUserTable(User usr){
        try{
            int rs=0;
            /*Email varchar(50),
	ContactNo varchar(50),
	[Address] varchar(100),
	Name varchar(50),
	Blood_group varchar(3),
	Gender varchar(50),
	Nationality varchar(50),
	Campus varchar(50),
	[Password] varchar(50) not null,*/
            rs=stmt.executeUpdate("Insert into [User] values ('"+usr.userEmail+"','"+usr.userContactNo+"','"+usr.userAddress+
                                                   "','"+usr.userName+"','"+usr.userBloodGrp+"','"+usr.Gender+"','"+usr.userNatonality+"','"+usr.userCampus+"','"+usr.userPassword+"')");
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateUserTable");
        }
    }
    void displayAllUsers(){
        try
        {
         System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the User table~~~~~~~~~~~~~~~~~~\n");
            /*Email varchar(50),
	ContactNo varchar(50),
	[Address] varchar(100),
	Name varchar(50),
	Blood_group varchar(3),
	Gender varchar(50),
	Nationality varchar(50),
	Campus varchar(50),*/

            ResultSet rs=stmt.executeQuery("select * from [User]");
            System.out.println("Email\tContactNo\tAddress\tName\tBldGrp\tGender\tNationality\tCampus");
             while(rs.next())
             {

                System.out.println(rs.getString(1)+"\t "+rs.getString(2)+"\t "+rs.getString(3)+"\t"+rs.getString(4)+"\t"+
                                             rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7));

             }
        }

        catch(SQLException e)
        {
            System.out.println(e+" in dbConnectivity::displayAllUsers");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateFacultyTable(Faculty fac){
        try{

            /*Email varchar(50),	--Child class of User so have to import Email from user
	FacID int),*/
            int rs=stmt.executeUpdate("Insert into Faculty values ( '"+fac.userEmail+"',"+fac.facID+")");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateFacultyTable");
        }
    }
    void displayAllFaculty(){
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the Faculty table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From Faculty");
            System.out.println("Email\tFacID");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllFaculty");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateCourseTable(Course crs){
        try{
           /* Code varchar(50),
	Name varchar(50),
	CreditHours int*/

            int rs=stmt.executeUpdate("Insert into Course values ( '"+crs.courseCode+"','"+crs.courseName+"',"+crs.creditHrs+")");

        }
        catch(Exception e){
            System.out.println(e+" in dbConnectivity::populateCourseTable");
        }
    }
    void displayAllCourses(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the Course table~~~~~~~~~~~~~~~~~~\n");

          /*  Code varchar(50),
	Name varchar(50),
	CreditHours int*/


            ResultSet rs=stmt.executeQuery("Select*From Course");
            System.out.println("CourseCode\tCourseName\tCrdHrs");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
            }
        }
        catch(SQLException e){

            System.out.println(e+" in dbConnectivity::displayAllCourses");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateInstructorsTeachingTheCourseTable(Faculty fac,Course crs){
        try{
            /*Code varchar(50),
	Instructor int,*/

            int rs=stmt.executeUpdate("Insert into InstructorsTeachingTheCourse values ( '"+crs.courseCode+"',"+fac.facID+")");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateInstructorsTeachingTheCourseTable");
        }
    }
     void displayAllInstructorsTeachingTheCourse(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the InstructorsTeachingTheCourse table~~~~~~~~~~~~~~~~~~\n");

            ResultSet rs=stmt.executeQuery("Select*From InstructorsTeachingTheCourse");
            System.out.println("CourseCode\tFacID");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllInstructorsTeachingTheCourse");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateGraduateStudentTable(Graduate std){
        try{
            /*RollNumber varchar(50),	--Importing RollNo which is key of Primary class
	ResearchThesis varchar(50),*/

            int rs=stmt.executeUpdate("Insert Into GraduateStudent values ( '"+std.stdRollNo+"','"+std.researchThesis+"')");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateGraduateStudentTable");
        }
    }
    void displayAllGraduateStudents(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the GraduateStudent table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From GraduateStudent");
            System.out.println("RollNumber\tResearchThesis");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllGraduateStudents");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    void populateUndergraduateStudentTable(Undergraduate std){

        try{
            /*RollNumber varchar(50),	--Importing RollNo which is key of Primary class
	FYP varchar(50),*/
            int rs=stmt.executeUpdate("Insert into UndergraduateStudent values ( '"+std.stdRollNo+"','"+std.FYP+"')");

        }
        catch(SQLException e){
            System.out.println(e+" in db Connectivity::populateUndergraduateStudentTable");
        }
    }
    void displayAllUndergraduateStudents(){
        try{
            System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the UndergraduateStudent table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From UndergraduateStudent");
            System.out.println("RollNumber\tFYP");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllUndergraduateStudents");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    void populatePermanentFacultyTable(PermanentFaculty fac){
        try{
            /*salaryPerSemester int,
	facID int,*/
            int rs=stmt.executeUpdate("Insert into PermanentFaculty values ( "+fac.salaryPerSemester+","+fac.facID+")");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populatePermanentFacultyTable");
        }
    }
    void displayAllPermanentFaculty(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the PermanentFaculty table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From PermanentFaculty");
            System.out.println("salaryPerSemester\tfacID");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getInt(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllPermanentFaculty");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    void populateVisitingFacultyTable(VisitingFaculty fac){
        try{
            /*salaryPerLecture int,
	facID int,*/
            int rs=stmt.executeUpdate("Insert into VisitingFaculty values ( "+fac.salaryPerLecture+","+fac.facID+")");
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateVisitingFacultyTable");
        }
    }
    void displayAllVisitingFaculty(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the VisitingFaculty table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From VisitingFaculty");
            System.out.println("salaryPerLecture\tfacID");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getInt(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllVisitingFaculty");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    void populateStudentsTakingTheCourseTable(Course crs,Student std){
        try{
            /*Code varchar(50),
	Student varchar(50),*/
            int rs=stmt.executeUpdate("Insert into StudentsTakingTheCourse values ( '"+crs.courseCode+"','"+std.stdRollNo+"')");
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateStudentsTakingTheCourseTable");
        }
    }
    void displayAllStudentsTakingTheCourse(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the StudentsTakingTheCourse table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From StudentsTakingTheCourse");
            System.out.println("CourseCode\tRollNumber");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllStudentsTakingTheCourse");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateCourseResultTable(CourseResult crsrs){
        try{
            /*RollNumber varchar(50),
	Code varchar(50),

	GPA float,
	midMarks float,
	finalMarks float,*/
            int rs=stmt.executeUpdate("Insert into CourseResult values ( '"+crsrs.std.stdRollNo+"','"+crsrs.crs.courseCode+"',"+
                                                       crsrs.GPA+","+crsrs.midMarks+","+crsrs.finalMarks+")");
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateCourseResultTable");
        }
    }
    void displayAllCourseResults(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the CourseResult table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From CourseResult");
            System.out.println("RollNumber\tCourseCode\tGPA\tmidMarks\tfinalMarks");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getFloat(4)+"\t"+rs.getFloat(5));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllCourseResults");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    void populateListOfCoursesTeachedByTeacherTable(Faculty fac, Course crs){
        try{
            /*Code varchar(50),
	FacID int,*/
            int rs=stmt.executeUpdate("Insert into ListOfCoursesTeachedByTeacher values( '"+crs.courseCode+"',"+fac.facID+")");
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateListOfCoursesTeachedByTeacherTable");
        }
    }
    void displayAllListOfCoursesTeachedByTeacher(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the ListOfCoursesTeachedByTeacher table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From ListOfCoursesTeachedByTeacher");
            System.out.println("CourseCode\tfacID");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllListOfCoursesTeachedByTeacher");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateSemesterTable(Semester sem){
        try{
            /*ID varchar(50),
	[Year] int,
	CourseOffered varchar(50),*/
            for(int i=0;i<sem.offeredCourses.size();i++){
            int rs=stmt.executeUpdate("Insert into Semester values( '"+sem.semID+"','"+sem.semYear+"','"+sem.offeredCourses.get(i).courseCode+"')");
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateSemesterTable");
        }
    }
    void displayAllSemesters(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the Semester table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From Semester");
            System.out.println("SemID\tSemYear\tOfferedCourse");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllSemesters");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateCourseAssistantTable(CourseAssistant ca){
        try{
            /*Code varchar(50), --CourseCode
	facID int,
	TARollNum varchar(50),*/

            int rs=stmt.executeUpdate("Insert into CourseAssistant values( '"+ca.crs.courseCode+"',"+ca.fac.facID+",'"+ca.std.stdRollNo+"')");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateCourseAssistantTable");
        }
    }
    void displayAllCourseAssistants(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the CourseAssistant table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From CourseAssistant");
            System.out.println("CourseCode\tfacID\tTARollNumber");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(3));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllCourseAssistants");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    void populateQuizMarksListTable(CourseResult crsrs){
        try{
            /*RollNumber varchar(50),
	Code varchar(50),

	marksQ1 float,
	marksQ2 float,
	marksQ3 float,*/

            int rs=stmt.executeUpdate("Insert into QuizMarksList values( '"+crsrs.std.stdRollNo+"','"+crsrs.crs.courseCode+"',"+crsrs.quizMarks[0]+","+
                                                        crsrs.quizMarks[1]+","+crsrs.quizMarks[2]+")");

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::populateQuizMarksListTable");
        }
    }
    void displayAllQuizMarksList(){
        try{
             System.out.println("\n~~~~~~~~~~~~~~~~~Displaying the QuizMarksList table~~~~~~~~~~~~~~~~~~\n");
            ResultSet rs=stmt.executeQuery("Select*From QuizMarksList");
            System.out.println("RollNumber\tCourseCode\tmarksQ1\tmarksQ2\tmarksQ3");
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getFloat(4)+"\t"+rs.getFloat(5));
            }
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllQuizMarksList");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    Student getSpecificStudent(String email,FlexApplication subject){
        Student you2 = null;
        try
        {     
            myStmt = con.prepareCall("{call getStudentInfo(?)}");
            
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            
            while (rs.next()){
                String Email2 = rs.getString(1);
                String ContactNo = rs.getString(2);
                String Address = rs.getString(3);
                String Name = rs.getString(4);
                String BloodGroup = rs.getString(5);
                String Gender = rs.getString(6);
                String Nationality = rs.getString(7);
                String Campus = rs.getString(8);
                String Password = rs.getString(9);
                String RollNo = rs.getString(10);
                Float CGPA = rs.getFloat(11);   
                you2 = new Student(Name,Gender,Email2,Address,ContactNo,BloodGroup,null,Nationality,Campus,Password,RollNo,CGPA,subject);
            
            }

            return you2;
        }
        
        catch(SQLException e)
        {
            System.out.println(e+" in dbConnectivity::getSpecificStudent");
        }
        return you2;
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    int validateForStudentLogin(String tempEmail, String tempPass){        
        int returnCheck = -1;
        try{
            myStmt = con.prepareCall("{call StudentLogin(?,?,?)}");
            
            myStmt.setString(1, tempEmail);
            myStmt.setString(2, tempPass);
            myStmt.registerOutParameter(3, Types.INTEGER);
            myStmt.executeUpdate();
            
            returnCheck = myStmt.getInt(3);

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::validateForStudentLogin");
        }
        
        return returnCheck; //1 good and 0 bad
    } //Haider Wain
 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    Faculty getSpecificFaculty(String email){
        Faculty fyou2 = null;
        try
        {     
            myStmt = con.prepareCall("{call getFacultyInfo(?)}");
            
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            
            while (rs.next()){
                String Email2 = rs.getString(1);
                String ContactNo = rs.getString(2);
                String Address = rs.getString(3);
                String Name = rs.getString(4);
                String BloodGroup = rs.getString(5);
                String Gender = rs.getString(6);
                String Nationality = rs.getString(7);
                String Campus = rs.getString(8);
                String Password = rs.getString(9);
                int FacID = rs.getInt(10); 
                fyou2 = new Faculty(Name,Gender,Email2,Address,ContactNo,BloodGroup,null,Nationality,Campus,Password,FacID);
            
            }

            return fyou2;
        }
        
        catch(SQLException e)
        {
            System.out.println(e+" in dbConnectivity::displayAllUsers");
        }
        return fyou2;
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    int validateForFacultyLogin(String tempEmail, String tempPass){        
        int returnCheck = -1;
        try{
            myStmt = con.prepareCall("{call FacultyLogin(?,?,?)}");
            
            myStmt.setString(1, tempEmail);
            myStmt.setString(2, tempPass);
            myStmt.registerOutParameter(3, Types.INTEGER);
            myStmt.executeUpdate();
            
            returnCheck = myStmt.getInt(3);

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::displayAllQuizMarksList");
        }
        
        return returnCheck;
    } //Haider Wain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//    
    void updateUserPassword(String pass, String email){
        try
        {
            int rs = stmt.executeUpdate("Update [User] Set [User].[Password] = '" + pass + "' Where [User].Email = '" + email + "'");            
        }       
        catch(SQLException e)
        {
            System.out.println(e+" in dbConnectivity::updateUserPassword");
        }
    } //HaiderWain
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
   int validateAtteandance(String code, String rollno,String date,String status){        
        int returnCheck = -1;
        try{
            myStmt = con.prepareCall("{call addAttendance(?,?,?,?,?)}");
            
            myStmt.setString(1, code);
            myStmt.setString(2, rollno);
            myStmt.setString(3, date);
            myStmt.setString(4, status);
            myStmt.registerOutParameter(5, Types.INTEGER);
            myStmt.executeUpdate();
            
            returnCheck = myStmt.getInt(5);

        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::validateAtteandance");
        }
        
        return returnCheck; //1 good and 0 bad
    } //Hassaan
   
   //--------------------------------------------------------
   int getTotalMarks(String code,String rollno){
       int ret=-1;
       try{
           ResultSet rs = stmt.executeQuery("select finalMarks from CourseResult where RollNumber = '"+rollno+"' and Code = '"+code+"'");
           while(rs.next()){
               ret=rs.getInt(1);
           }
       }
       catch(SQLException e){
           System.out.println(e+" in dbConnectivity::getTotalMarks");
       }
       return ret;
   }//Hassaan
   
   
   //========================================================================================================
   int addStudentInCourse(String c,Student s)
    {
        int returnCheck =-1;
        try{
            myStmt = con.prepareCall("{call add_course(?,?,?)}");
            
            myStmt.setString(1, c);
            myStmt.setString(2, s.stdRollNo);
            myStmt.registerOutParameter(3, Types.INTEGER);
            myStmt.executeUpdate();
            
            returnCheck = myStmt.getInt(3);
            
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::addStudentInCourse");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return returnCheck;
    }   //Omer
   
   int drop_Course(String c,Student s)
    {
        int returnCheck =-1;
        try{
            myStmt = con.prepareCall("{call Drop_course(?,?,?)}");
            
            myStmt.setString(1, s.stdRollNo);
            myStmt.setString(2, c);
            myStmt.registerOutParameter(3, Types.INTEGER);
            myStmt.executeUpdate();
            
            returnCheck = myStmt.getInt(3);
            
        }
        catch(SQLException e){
            System.out.println(e+" in dbConnectivity::Drop course");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return returnCheck;
    }//Omer
 }