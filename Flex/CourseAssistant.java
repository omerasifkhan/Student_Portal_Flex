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

//Association class between permanent faculty and Student
public class CourseAssistant {
    
    Course crs;
    PermanentFaculty fac;
    Student std;
    
    CourseAssistant(Course c, PermanentFaculty f, Student s){
        crs=c;
        fac=f;
        std=s;
    }
    
}
