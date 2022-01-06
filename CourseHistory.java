// name: An Le
// Class: CSC 121
// Prof: Khadija Stewart
// Proeject 3 

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



//NOTES:  You will need the import statements that appear at the top of this file, so you should
//leave them in place.  Follow the list of steps on the project writeup to complete the CourseHistory
//class that is started below.  This entire block of comments should be deleted when you are done. 


public class CourseHistory
{
    private ArrayList<CompletedCourse> courseList;
    
    public CourseHistory()
    {
        courseList = new ArrayList<CompletedCourse>();
        String department;    //For example "CSC"
        String courseNumber;  //For example 121
        String semesterTaken; //For example 10708  or 20708
        String credit;        //The number of credits the course is worth, for example 1.0, .5, .25 
        String grade;         //For example 4.0, 3.67, 3.33, 3.0, 2.67, 2.33, 2.0, 1.67, 1.33, 1.0, 0.0
        String competency;    //Can be 'W', 'Q', 'S', or 'X' (for none)
        String distArea;      //Can be AH, SS, SM, LA or NONE (for no distArea). 

        
        try
       {
           FileReader reader = new FileReader("finishedcourses.txt");
           Scanner in = new Scanner(reader);
       
           while(in.hasNextLine())   
           {  department = in.nextLine();  
              courseNumber = in.nextLine();
              semesterTaken = in.nextLine();
              credit = in.nextLine();  
              grade = in.nextLine();
              competency = in.nextLine();
              distArea = in.nextLine();  
              CompletedCourse theCourse = new CompletedCourse( department,  courseNumber,  semesterTaken, credit,   grade,  competency,  distArea);
              courseList.add(theCourse);
                //You will be replacing this line 
              
          }
          in.close();  //Close the file when we are done reading it
       } catch (IOException exception)
       {
          System.out.println("Error processing file: " + exception);
       }   
    }
    
    //this method display the courses 
    public void displayCourseHistory()
    {
        System.out.println("Course History");
        for(int i=0; i<courseList.size(); i++){
            courseList.get(i).displayCourse();
        }
        
    }
    // this method provides the total credits earned and the total GPA
    public void summaryReport()
    {
        double totalCredits = 0;
        double totalGPA = 0;
        double GPA = 0;
        System.out.println("Summary Report");
        for(int i=0; i<courseList.size(); i++){
            if(courseList.get(i).getGrade() > 0){
                totalCredits += courseList.get(i).getCredit();
                totalGPA +=  courseList.get(i).getGrade()*courseList.get(i).getCredit();
            }  
          
        }
        GPA = totalGPA/totalCredits;
        System.out.println("totalCredits " + totalCredits);
        System.out.println("totalGPA " + GPA);
    }
    
    //this method gives us the course in the distribution area with the total of completed credits in each distribution area
    public void distAreaReport()
    {
        
        System.out.println("Distribution Area Report");
        
        double sumAH = DDAhelper("AH");
        System.out.println("Completed AH credits: "+ sumAH);
        
        double sumSS = DDAhelper("SS");
        System.out.println("Completed SS credits: "+ sumSS);
        
        double sumSM = DDAhelper("SM");
        System.out.println("Completed SM credits: "+ sumSM);
        
        double sumLA = DDAhelper("LA");
        System.out.println("Completed LA credits: "+ sumLA);
        
    }
    
    public double DDAhelper(String dist)
    {
        double sum = 0;
        for(int i=0; i<courseList.size(); i++)
        {
            if(courseList.get(i).getDistArea().equals(dist) && courseList.get(i).getGrade() > 0){
                courseList.get(i).displayCourse();
                sum += courseList.get(i).getCredit();
            }
        }
        return sum;
    }
    
    //this method tell us the status of competencies' completion
    public void compReport()
    {
        System.out.println("Competency Report");
        int statusW = compHelper("W");
        int statusQ = compHelper("Q");
        int statusS = compHelper("S");
        int sum = statusW + statusQ + statusS;
        System.out.println();
        
        if(sum == 3)
            System.out.println("All competencies completed");
        if(sum>0 && sum <3)
            System.out.println("Competencies Partially Completed."  );
        if(sum==0)
            System.out.println("No competencies completed");
    }
    
    public int compHelper(String competency)
    {
        int count = 0;
        String status;
        for(int i=0; i<courseList.size();i++){
            if(courseList.get(i).getGrade() > 0 && 
            courseList.get(i).getCompetency().equals(competency))
            {
                count ++;
            }
        }
        if(count > 0)
        {
            return 1;
        }
        return 0;
    }
    // this method gives us the full report
    public void fullReport()
    {
        System.out.println("Full Report");
        summaryReport();
        distAreaReport();
        compReport();
    }
    // this method sort the course by GPA (from highest to lowest)
    public void sortListGPA()
    {
        ArrayList<CompletedCourse> temp = new ArrayList<CompletedCourse>();
        for(int i = 0; i < courseList.size(); i++){
            temp.set(i, courseList.get(i));
        }
        
        
        int length = courseList.size();
        for(int i=0; i<length-1;i++){
            for(int j=0; j<length-1; j++){
               if(temp.get(j).getGrade() < temp.get(j+1).getGrade()){
                   CompletedCourse tempo = courseList.get(j);
                   temp.set(j,temp.get(j+1)); 
                   temp.set(j+1, tempo);
                }
            }
        }
        
        for(int i = 0; i < temp.size() ; i++){
            temp.get(i).displayCourse();
        }
    }
}












