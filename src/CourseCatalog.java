import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseCatalog manages all available courses in the system.
 * Uses ArrayList from Java Collections Framework (JCF).
 * Demonstrates composition and encapsulation.
 */
public class CourseCatalog {
    // Private instance variable using JCF - composition
    private List<Course> courses;
    private static final String CSV_FILE = "courses.csv";

    /**
     * Constructor initializes the course catalog and loads from CSV
     */
    public CourseCatalog() {
        this.courses = new ArrayList<Course>();
        loadCoursesFromCSV();
    }

    /**
     * Load courses from CSV file
     * Demonstrates file I/O and object creation based on data
     */
    private void loadCoursesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse CSV line
                String[] data = line.split(",");
                if (data.length >= 7) {
                    String courseName = data[0].trim();
                    String capacityStr = data[1].trim();
                    String year = data[2].trim();
                    String deliveryMode = data[3].trim();
                    String day = data[4].trim();
                    String time = data[5].trim();
                    double duration = Double.parseDouble(data[6].trim());

                    // Create appropriate course type based on delivery mode - polymorphism
                    Course course;
                    if (deliveryMode.equalsIgnoreCase("Face-to-face")) {
                        int capacity = Integer.parseInt(capacityStr);
                        course = new FaceToFaceCourse(courseName, capacity, year, day, time, duration);
                    } else {
                        // Online course - no capacity constraint
                        course = new OnlineCourse(courseName, year, day, time, duration);
                    }

                    courses.add(course);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing course data: " + e.getMessage());
        }
    }

    /**
     * Search for courses by keyword (case-insensitive)
     * @param keyword search term
     * @return List of matching courses
     */
    public List<Course> searchByKeyword(String keyword) {
        List<Course> matchingCourses = new ArrayList<Course>();
        for (Course course : courses) {
            if (course.matchesKeyword(keyword)) {
                matchingCourses.add(course);
            }
        }
        return matchingCourses;
    }

    /**
     * Get all courses in the catalog
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<Course>(courses);
    }

    /**
     * Find a course by exact name
     * @param courseName name to search for
     * @return Course if found, null otherwise
     */
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get total number of courses
     * @return count of courses
     */
    public int getCourseCount() {
        return courses.size();
    }
}
