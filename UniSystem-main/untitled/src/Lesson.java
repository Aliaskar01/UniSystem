import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Lesson implements Serializable, Comparable<Lesson> {

    private int lessonId;
    private Course course;
    private Teacher teacher;
    private Time scheduledAt;
    private Time endAt;
    private String description;
    private String title;
    private LessonType type;
    private Vector<Student> students;

    public Lesson(int lessonId, Course course, Teacher teacher, Time scheduledAt, Time endAt, String description, String title, LessonType type) {
        this.lessonId = lessonId;
        this.course = course;
        this.teacher = teacher;
        this.scheduledAt = scheduledAt;
        this.endAt = endAt;
        this.description = description;
        this.title = title;
        this.type = type;
        students = new Vector<>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Time scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getLessons().add(this);
    }

    public void deassignTeacher(Teacher teacher) {
        this.teacher = null;
        teacher.getLessons().remove(this);
    }

    public void assignStudent(Student student) {
        this.students.add(student);
        student.getLessons().add(this);
    }
    public void deassignStudent(Student student) {
        this.students.remove(student);
        student.getLessons().remove(this);
    }

    public void scheduleLesson(Time start, Time end ) {
        scheduledAt = start;
        endAt = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return lessonId == lesson.lessonId && Objects.equals(course, lesson.course) && Objects.equals(teacher, lesson.teacher) && Objects.equals(scheduledAt, lesson.scheduledAt) && Objects.equals(endAt, lesson.endAt) && Objects.equals(description, lesson.description) && Objects.equals(title, lesson.title) && type == lesson.type && Objects.equals(students, lesson.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, course, teacher, scheduledAt, endAt, description, title, type, students);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + getType() +
                ", title='" + getTitle() + '\'' +
                ", lessonId=" + getLessonId() +
                ", course=" + getCourse() +
                ", scheduledAt=" + getScheduledAt() +
                ", teacher=" + getTeacher() +
                ", description='" + getDescription() + '\'' +
                ", endAt=" + getEndAt() +
                '}';
    }

    @Override
    public int compareTo(Lesson o) {
        return lessonId - o.lessonId;
    }
}
