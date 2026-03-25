import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseCatalog manages all available courses
 * Uses Java Collections Framework (ArrayList)
 */
public class CourseCatalog {
    private List<Course> courses;
    private static final String CSV_FILE = "courses.csv";

    public CourseCatalog() {
        this.courses = new ArrayList<Course>();
        loadCoursesFromCSV();
    }

    private void loadCoursesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // skip header
                }

                String[] data = line.split(",");
                if (data.length >= 7) {
                    String courseName = data[0].trim();
                    String capacityStr = data[1].trim();
                    String year = data[2].trim();
                    String deliveryMode = data[3].trim();
                    String day = data[4].trim();
                    String time = data[5].trim();
                    double duration = Double.parseDouble(data[6].trim());

                    Course course;
                    if (deliveryMode.equalsIgnoreCase("Face-to-face")) {
                        int capacity = Integer.parseInt(capacityStr);
                        course = new FaceToFaceCourse(courseName, capacity, year, day, time, duration);
                    } else {
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

    public List<Course> searchByKeyword(String keyword) {
        List<Course> matching = new ArrayList<Course>();
        for (Course course : courses) {
            if (course.matchesKeyword(keyword)) {
                matching.add(course);
            }
        }
        return matching;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<Course>(courses);
    }

    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    public int getCourseCount() {
        return courses.size();
    }
}
