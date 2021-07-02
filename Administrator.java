import java.util.*;
public class Administrator {
    Scanner sc = new Scanner(System.in);
    Vector <Student> v = new Vector <Student> ();
    protected static int totalStudents = 0; // total students in the Uninversity
    private boolean firstStudent = true; // this is for entering the first student without any checking if he/she has already been registered 
    
    public Administrator() {
        
    }
    
    public void run() {
        while (true) {
            System.out.println("Please select what you wish to do");
            System.out.println("Press (1) for REGISTERING a student");
            System.out.println("Press (2) for ADVISING of a student");
            System.out.println("Press (3) for VIEWING INFO of a student");
            System.out.println("Press (4) to EXIT");
            int x = 0;
            boolean flag = false;
            Scanner sc2 = new Scanner(System.in);
            String test = sc2.nextLine();
            try {
                x = Integer.parseInt(test);
            } catch (Exception e) {
                System.out.println("This is not a valid option\n");
                break;
            }
            if (x == 1) {
                System.out.println("Please enter the NAME of the student");
                String str = sc2.nextLine();
                System.out.println("Please enter the ID of the student");
                int id = sc.nextInt();
                sc.nextLine();
                v.add(new Student(str, id)); // registering the student in the department
                
                for (int i = 0; i < v.size()-1; ++i) {
                    if (v.get(i).getID() == v.lastElement().getID()) {
                        System.out.println("This student has already been registered once\n");
                        v.remove(v.size()-1);
                        flag = true;
                        break;
                    }
                    else if (i == v.size()-2) {
                        System.out.println("ADDING DONE!!\n");
                    }
                }
                if (firstStudent) {
                    firstStudent = false;
                    System.out.println("ADDING DONE!!\n\n");
                }
                if (flag) break;
            } else if (x == 2) {
                System.out.println("Please enter the ID of the student");
                int id = sc.nextInt();
                for (int i = 0; i < v.size(); ++i) {
                    if (v.get(i).getID() == id) {
                        System.out.println("Press (1) for ADDING a course");
                        System.out.println("Press (2) for DROPPING a course");
                        int command = sc.nextInt();
                        if (command == 1) {
                            v.get(i).courseAdd(); // calling the function which adds the course
                            break;
                        } else if (command == 2) {
                            v.get(i).courseDrop();
                            break;
                        }
                    }
                    else if (i == v.size()-1) {
                        System.out.println("This ID is not registered in this Department\n"); // if id is not yet inputed
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            } else if (x == 3){ 
                System.out.println("Please enter the ID of the student");
                int id = sc.nextInt();
                for (int i = 0; i < v.size(); ++i) {
                    if (v.get(i).getID() == id) {
                        v.get(i).printDetails();
                    }
                    else if (i == v.size()-1) {
                        System.out.println("This student is NOT Registered in this Course");
                    }
                }
            } else if (x == 4) {
                break;
            } else if (x > 4) {
                System.out.println("This is not a valid option\nPlease select a Department again");
                break;
            }
            sc2.close();
        }
    }
}