import java.util.List;
import java.util.Scanner;

/**
 * MyTimetable - Main application class
 * COSC1295 Assignment 1 - Final Week 6 Submission
 * 
 * All features implemented:
 * - Search and enroll with capacity checking
 * - Display enrolled courses
 * - Withdraw from courses
 * - Proper use of JCF, inheritance, polymorphism, encapsulation
 *
 * Duplicate-enrolment prevention is now handled inside Student.enroll()
 * via the LinkedHashSet.
 */

public class MyTimetable {
    private CourseCatalog catalog;
    private Student currentStudent;
    private Scanner scanner;

    public MyTimetable() {
        this.catalog = new CourseCatalog();
        this.scanner = new Scanner(System.in);
        this.currentStudent = new Student("S001", "Student");
    }

    public static void main(String[] args) {
        MyTimetable system = new MyTimetable();
        system.run();
    }

    public void run() {
        System.out.println("Welcome to MyTimetable!");

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    searchAndEnroll();
                    break;
                case "2":
                    showEnrolledCourses();
                    break;
                case "3":
                    withdrawFromCourse();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-4.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("> Select from main menu");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("   1) Search by keyword to enroll");
        System.out.println("   2) Show my enrolled courses");
        System.out.println("   3) Withdraw from a course");
        System.out.println("   4) Exit");
        System.out.print("Please select: ");
    }

    private void searchAndEnroll() {
        System.out.print("Please provide a keyword: ");
        String keyword = scanner.nextLine().trim();

        List<Course> matchingCourses = catalog.searchByKeyword(keyword);

        if (matchingCourses.isEmpty()) {
            System.out.println("No courses found matching '" + keyword + "'.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> Select from matching list");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < matchingCourses.size(); i++) {
            System.out.println("   " + (i + 1) + ") " + matchingCourses.get(i).getCourseName());
        }
        System.out.println("   " + (matchingCourses.size() + 1) + ") Go to main menu");
        System.out.print("Please select: ");

        String selection = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(selection);
            if (choice >= 1 && choice <= matchingCourses.size()) {
                Course selectedCourse = matchingCourses.get(choice - 1);

                // Student.enroll() returns false for both duplicates and full courses
                if (currentStudent.enroll(selectedCourse)) {
                    System.out.println("You have enrolled in the course " + selectedCourse.getCourseName() + "!");
                } else if (currentStudent.isEnrolled(selectedCourse)) {
                    System.out.println("You are already enrolled in " + selectedCourse.getCourseName() + ".");
                } else {
                    System.out.println("Sorry, " + selectedCourse.getCourseName() + " is full.");
                }

            } else if (choice != matchingCourses.size() + 1) {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void showEnrolledCourses() {
        if (currentStudent.hasNoCourses()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("You have enrolled into the following course(s):");
        System.out.println("--------------------------------------------------------------------------------");
        List<Course> courses = currentStudent.getEnrolledCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("   " + (i + 1) + ") " + courses.get(i).toString());
        }
    }

    private void withdrawFromCourse() {
        if (currentStudent.hasNoCourses()) {
            System.out.println("You don't have any courses enrolled.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Please choose a course to withdraw:");
        System.out.println("--------------------------------------------------------------------------------");
        List<Course> courses = currentStudent.getEnrolledCourses();

        for (int i = 0; i < courses.size(); i++) {
            System.out.println("   " + (i + 1) + ") " + courses.get(i).toString());
        }
        System.out.print("Please select: ");

        String selection = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(selection);
            if (choice >= 1 && choice <= courses.size()) {
                Course courseToWithdraw = courses.get(choice - 1);
                if (currentStudent.withdraw(courseToWithdraw)) {
                    System.out.println("You have withdrawn from " + courseToWithdraw.getCourseName() + "!");
                } else {
                    System.out.println("Failed to withdraw from course.");
                }
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
