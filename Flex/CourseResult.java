/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flex;

/**
 *
 * @author Aldehyde
 */

//An association class between Student and a Course
public class CourseResult {
    String Grade;
    float GPA;
    float quizMarks[];
    float midMarks;
    float finalMarks;
    
    
    Student std; //aggregated
    Course crs; //aggregated
    
   public CourseResult(String grd,float gpa, float[] qArr
                        ,float mid, float fin, Student s,Course c){
       
       Grade=grd;
       GPA=gpa;
       quizMarks=qArr;
       midMarks=mid;
       finalMarks=fin;
             
       
       std=s;
       crs=c;
       
    }
}
