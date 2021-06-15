package Flex;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Faculty extends User{
	
	int facID;
                  List <String> listOfCoursesTeaching;

	public Faculty(String name, String gender, String email,
			String address, String CN, String BG, String cnic,
			String nationality, String campus, String pass, int id) {
		
		super(name,gender,email,address,CN,BG,cnic,nationality,
				campus,pass);
		facID=id;
                                    listOfCoursesTeaching=new ArrayList<>();
                                    //-----------------------------------------------------------------//
                                    dbConnectivity db=new dbConnectivity();
                                    try{
                                        /*String cc, String cn,List<Faculty> fList
				  ,int crdhrs*/
                                        ResultSet rs=db.stmt.executeQuery("Select Code From InstructorsTeachingTheCourse Where Instructor = "+this.facID);
                                        while(rs.next()){
                                            this.addCourse(rs.getString(1));
                                        }
                                    }
                                    
                                    catch(SQLException e){
                                        System.out.println(e+" in Faculty::Faculty()");
                                    }
		
	}
    /**
     *
     * @param crs
     */
                  public void addCourse(String crs){
                        this.listOfCoursesTeaching.add(crs);
                    }
}
	


