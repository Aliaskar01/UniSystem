import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Mark implements Serializable, Comparable<Mark> {

    private int value; 
    private int studentId; 
    private HashMap<Language, String> courseId;
    private MarkType markType;
    private MarkTypeAttestation markTypeAttestation;


    public Mark(int value, int studentId, HashMap<Language, String> courseId, MarkType markType, MarkTypeAttestation markTypeAttestation) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Mark value must be between 0 and 100.");
        }
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be greater than 0.");
        }
        if (courseId == null || courseId.isEmpty()) {
            throw new IllegalArgumentException("Course ID must not be empty.");
        }
        if (markType == null) {
            throw new IllegalArgumentException("Mark type must not be null.");
        }

        this.value = value;
        this.studentId = studentId;
        this.courseId = courseId;
        this.markType = markType;
        this.markTypeAttestation = markTypeAttestation;
    }

    public MarkTypeAttestation getMarkTypeAttestation() {
        return markTypeAttestation;
    }

    public void setMarkTypeAttestation(MarkTypeAttestation markTypeAttestation) {
        this.markTypeAttestation = markTypeAttestation;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Mark value must be between 0 and 100.");
        }
        this.value = value;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be greater than 0.");
        }
        this.studentId = studentId;
    }

    public HashMap<Language, String> getCourseId() {
        return courseId;
    }

    public void setCourseId(HashMap<Language, String> courseId) {
        if (courseId == null || courseId.isEmpty()) {
            throw new IllegalArgumentException("Course ID must not be empty.");
        }
        this.courseId = courseId;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        if (markType == null) {
            throw new IllegalArgumentException("Mark type must not be null.");
        }
        this.markType = markType;
    }


    public String getMarkDetails() {
        return String.format("Mark: %d, Student ID: %d, Course: %s, Type: %s",
                value, studentId, courseId, markType);
    }

    @Override
    public String toString() {
        return String.format("Mark{value=%d, studentId=%d, courseId='%s', markType='%s'}", 
                value, studentId, courseId, markType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return value == mark.value && studentId == mark.studentId && Objects.equals(courseId, mark.courseId) && markType == mark.markType && markTypeAttestation == mark.markTypeAttestation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, studentId, courseId, markType, markTypeAttestation);
    }

    @Override
    public int compareTo(Mark o) {
        return value - o.value;
    }
}



