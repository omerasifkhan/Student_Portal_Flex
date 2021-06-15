package Flex;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	public String courseCode;
	public String courseName;
	public List<Faculty> facultyTeaching;
	public List<Student> studentsRegistered;
	public int creditHrs;
	
	
	public Course(String cc, String cn,List<Faculty> fList
				  ,int crdhrs) {
		
		this.courseCode=cc;
		this.courseName=cn;
		this.creditHrs=crdhrs;
		this.facultyTeaching=fList;
		this.studentsRegistered=new ArrayList<>();
		
	}
	
	public void addTeacherToList(Faculty fac) {
		facultyTeaching.add(fac);
	}
	public void addStudentTiList(Student std) {
		this.studentsRegistered.add(std);
	}

}
