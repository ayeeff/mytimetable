/**
 * OnlineCourse - concrete class for online delivery courses
 * No capacity constraints
 */
public class OnlineCourse extends Course {

    public OnlineCourse(String courseName, String year, String dayOfLecture, 
                        String timeOfLecture, double durationOfLecture) {
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
        return -1;  // indicates no limit
    }

    public boolean hasAvailableSpace() {
        return true;
    }

    @Override
    public String toString() {
        return getCourseName() + " " + getDeliveryMode() + " " + getDayOfLecture() + " " + getTimeRange();
    }
}
