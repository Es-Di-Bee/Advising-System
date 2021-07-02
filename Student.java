import java.util.*;
public class Student extends Administrator implements CourseManager {
      Scanner sc2 = new Scanner(System.in);
      private String name = "default name";
      private int id = 0;
      private int studentNo = 0;
      private int courseTaken = 0;
      public final static int courseLimit = 2; // this is the maximum number of courses a student can take
      public Vector <Course> v2 = new Vector <Course> ();
      private boolean firstCourse = true; // this is for entering the first course of that student without any checking if he/she has already taken that course
      boolean breakingFlag = false;
      public Student() {
            
      }
      public Student(String name, int id) {
            super();
            ++totalStudents; // increasing the total number of students everytime an object is created
            this.name = name;
            this.id = id;
            studentNo = totalStudents; // this is to keep track of the student no so that info about that particular student can be easily accessed
      }
      public void courseAdd() {
            while (true) {
                  System.out.println("Please enter the NAME of the course you want to Add");
                  String str = sc2.nextLine();
                  System.out.println("Please enter the SECTION of the course you want to Add");
                  String str2 = sc2.nextLine();
                  System.out.println("Please enter the CLASS TIME of the course you want to Add.\nFORMAT: HH:00 or HH:30");
                  String str3 = sc2.nextLine();
                  v2.add(new Course(str, str2, str3)); // adding a new course for this student
                  ++courseTaken; 
                  
                  if (checkCourseLimit()) {
                        System.out.println("Course Limit Reached.\nPlease DROP a course to add a NEW Course\n");
                        v2.remove(v2.size()-1);
                        break;
                  } else {
                        for (int i = 0; i < v2.size()-1; ++i) {
                              if (v2.get(i).getName().equals(v2.lastElement().getName())) {
                                    System.out.println("This course is already taken by this student\n");
                                    --courseTaken;
                                    v2.remove(v2.size()-1);
                                    break;
                              }
                              else if (i == v2.size()-2) {
                                    checkTimeCollision();
                              }
                        }
                        if (firstCourse) {
                              firstCourse = false;
                              System.out.println("Course Registration Done\n");
                              break;
                        }
                  }
                  if (breakingFlag) break;
            }
      }
      
      public void courseDrop() {
            System.out.println("Please enter the name of the Course you wish to Drop for this particular student");
            String str = sc2.nextLine();
            for (int i = 0; i < v2.size(); ++i) {
                  if (v2.get(i).getName().equals(str)) {
                        v2.remove(i);
                        courseTaken-=2;;
                        System.out.println("Course successfully Dropped\n");
                        break;
                  } else if (i == v2.size()-1) {
                        System.out.println("This course is not taken by this student\n");
                        break;
                  }
            }
      }
      
      public void checkTimeCollision() {
            for (int i = 0; i < v2.size()-1; ++i) {
                  if (v2.get(i).getTime().equals(v2.lastElement().getTime())) {
                        System.out.println("There is already a course added in this particular time\n");
                        --courseTaken;
                        v2.remove(v2.size()-1);
                        break;
                  }
                  else if (i == v2.size()-2) {
                        System.out.println("Course registration Done\n");
                        breakingFlag = true;
                        break;
                  }
            }
      } 
      
      public boolean checkCourseLimit() {
            if (courseTaken > courseLimit) {
                  return true;
            } else {
                  return false;
            }
      }
      
      public int getID() {
            return id;
      }
      
      public void printDetails() {
            System.out.println("NAME: " + this.name + "\nID: " + this.id + "\nStudentNo: " + this.studentNo + "\nCourses Taken: " + this.courseTaken + "\n");    
            System.out.println("Advised Courses:\n_______________\n");
            for (int i = 0; i < v2.size(); ++i) {
                  System.out.println("Course Name: " + v2.get(i).getName());
                  System.out.println(" Course Sec: " + v2.get(i).getSection());
                  System.out.println("Course Time: " + v2.get(i).getTime());
                  System.out.println();
            }
            System.out.println();
      }
      
}