import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudentIds;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudentIds = new ArrayList<>();
    }

    public boolean hasSlot() {
        return registeredStudentIds.size() < capacity;
    }
}

class Student {
    String studentId;
    String name;
    List<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class Course_Registration_System {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, Course> courseDatabase = new HashMap<>();
    static Map<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        loadSampleData();
        System.out.println("Welcome to the Course Registration System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // clear invalid input
            }

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void loadSampleData() {
        courseDatabase.put("CSE101", new Course("CSE101", "Introduction to Programming", "Learn basics of Java", 3, "Mon 9AM-11AM"));
        courseDatabase.put("MAT201", new Course("MAT201", "Calculus II", "Advanced integration techniques", 2, "Tue 10AM-12PM"));
        courseDatabase.put("PHY105", new Course("PHY105", "Physics Fundamentals", "Mechanics and Waves", 2, "Wed 1PM-3PM"));

        studentDatabase.put("S001", new Student("S001", "Alice"));
        studentDatabase.put("S002", new Student("S002", "Bob"));
    }

    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courseDatabase.values()) {
            System.out.println("---------------------------");
            System.out.println("Code: " + c.courseCode);
            System.out.println("Title: " + c.title);
            System.out.println("Description: " + c.description);
            System.out.println("Schedule: " + c.schedule);
            System.out.println("Capacity: " + (c.capacity - c.registeredStudentIds.size()) + " slots left");
        }
    }

    static void registerCourse() {
        System.out.print("\nEnter your Student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        listCourses();
        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (!course.hasSlot()) {
            System.out.println("No slots available for this course.");
            return;
        }

        if (student.registeredCourses.contains(courseCode)) {
            System.out.println("You are already registered for this course.");
            return;
        }

        course.registeredStudentIds.add(studentId);
        student.registeredCourses.add(courseCode);
        System.out.println("Registration successful!");
    }

    static void dropCourse() {
        System.out.print("\nEnter your Student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (student.registeredCourses.isEmpty()) {
            System.out.println("You are not registered in any courses.");
            return;
        }

        System.out.println("Your Registered Courses:");
        for (String code : student.registeredCourses) {
            System.out.println("- " + code);
        }

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.next();
        if (!student.registeredCourses.contains(courseCode)) {
            System.out.println("You are not registered in this course.");
            return;
        }

        Course course = courseDatabase.get(courseCode);
        if (course != null) {
            course.registeredStudentIds.remove(studentId);
        }
        student.registeredCourses.remove(courseCode);
        System.out.println("Course dropped successfully.");
    }
}
