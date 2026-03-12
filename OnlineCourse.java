/**
 * Concrete subclass representing an online delivered course.
 * Extends Course with no capacity constraints.
 * Demonstrates inheritance and polymorphism.
 */
public class OnlineCourse extends Course {

    /**
     * Constructor for OnlineCourse
     * @param courseName Name of the course
     * @param year Year level
     * @param dayOfLecture Day of lecture
     * @param timeOfLecture Time of lecture
     * @param durationOfLecture Duration in hours
     */
    public OnlineCourse(String courseName, String year, String dayOfLecture, 
                        String timeOfLecture, double durationOfLecture) {
        // Call superclass constructor
        super(courseName, year, dayOfLecture, timeOfLecture, durationOfLecture);
    }

    @Override
    public String getDeliveryMode() {
        return "Online";
    }

    @Override
    public boolean hasCapacityLimit() {
        return false;
    }

    @Override
    public int getCapacity() {
        return -1; // No capacity limit
    }

    /**
     * Online courses always have available space
     * @return always true
     */
    public boolean hasAvailableSpace() {
        return true;
    }

    @Override
    public String toString() {
        return getCourseName() + " " + getDeliveryMode() + " " + getDayOfLecture() + " " + getTimeRange();
    }
}
