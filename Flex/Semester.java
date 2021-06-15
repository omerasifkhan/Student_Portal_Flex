package Flex;

import java.util.ArrayList;
import java.util.List;

public class Semester {
	
	String semID;
	String semYear;
	List<Course> offeredCourses;

	public Semester(String id, String sYear) {
		
		this.semID=id;
		this.semYear=sYear;
		this.offeredCourses=new ArrayList <Course> ();
		
	}
	
	public void addCourseToList(Course crs) {
		this.offeredCourses.add(crs);
	}

}
