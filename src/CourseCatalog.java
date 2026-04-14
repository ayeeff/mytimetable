import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseCatalog manages all available courses loaded from courses.csv.
 *
 * JCF choices:
 *   - HashMap<String, Course> courseMap  — O(1) lookup by exact course name.
 *     Used by findCourseByName() and to populate the list below.
 *   - ArrayList<Course> courses          — ordered list for keyword search
 *     and sequential iteration. Populated once at load time and never mutated.
 */
public class CourseCatalog {

    /** Keyed by lowercase course name for case-insensitive exact lookup. */
    private Map<String, Course> courseMap;

    /** Insertion-order list used for keyword search iteration. */
    private List<Course> courses;

    private static final String CSV_FILE = "courses.csv";

    public CourseCatalog() {
        this.courseMap = new HashMap<String, Course>();
        this.courses   = new ArrayList<Course>();
        loadCoursesFromCSV();
    }

    private void loadCoursesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header row
                }

                String[] data = line.split(",");
                if (data.length >= 7) {
                    String courseName  = data[0].trim();
                    String capacityStr = data[1].trim();
                    String year        = data[2].trim();
                    String deliveryMode = data[3].trim();
                    String day         = data[4].trim();
                    String time        = data[5].trim();
                    double duration    = Double.parseDouble(data[6].trim());

                    Course course;
                    if (deliveryMode.equalsIgnoreCase("Face-to-face")) {
                        int capacity = Integer.parseInt(capacityStr);
                        course = new FaceToFaceCourse(courseName, capacity, year, day, time, duration);
                    } else {
                        course = new OnlineCourse(courseName, year, day, time, duration);
                    }

                    courses.add(course);
                    courseMap.put(courseName.toLowerCase(), course); // O(1) insert
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing course data: " + e.getMessage());
        }
    }

    /**
     * Returns all courses whose name contains the keyword (case-insensitive).
     * Iterates the ArrayList — O(n) linear scan is appropriate here because
     * partial-string matching cannot be accelerated with a HashMap.
     */
    public List<Course> searchByKeyword(String keyword) {
        List<Course> matching = new ArrayList<Course>();
        for (Course course : courses) {
            if (course.matchesKeyword(keyword)) {
                matching.add(course);
            }
        }
        return matching;
    }

    /**
     * O(1) exact-name lookup via HashMap.
     * Returns null if not found.
     */
    public Course findCourseByName(String courseName) {
        return courseMap.get(courseName.toLowerCase());
    }

    public List<Course> getAllCourses() {
        return new ArrayList<Course>(courses);
    }

    public int getCourseCount() {
        return courses.size();
    }
}
