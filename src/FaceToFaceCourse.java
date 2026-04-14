/**
 * FaceToFaceCourse — concrete subclass for physically delivered courses.
 * Has a capacity limit; tracks current enrollment count.
 */
public class FaceToFaceCourse extends Course {
    private int capacity;
    private int currentEnrollment;

    public FaceToFaceCourse(String courseName, int capacity, String year,
                            String dayOfLecture, String timeOfLecture, double durationOfLecture) {
        super(courseName, year, dayOfLecture, timeOfLecture, durationOfLecture);
        this.capacity = capacity;
        this.currentEnrollment = 0;
    }

    @Override
    public String getDeliveryMode() { return "Face-to-face"; }

    @Override
    public boolean hasCapacityLimit() { return true; }

    @Override
    public int getCapacity() { return capacity; }

    public int getCurrentEnrollment() { return currentEnrollment; }

    public boolean hasAvailableSpace() { return currentEnrollment < capacity; }

    public boolean enrollStudent() {
        if (hasAvailableSpace()) {
            currentEnrollment++;
            return true;
        }
        return false;
    }

    public void withdrawStudent() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    @Override
    public String toString() {
        return getCourseName() + " " + getDeliveryMode() + " " + getDayOfLecture() + " " + getTimeRange();
    }
}
