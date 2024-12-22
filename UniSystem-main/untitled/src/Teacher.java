import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Teacher extends Employee{
    

    private Vector<Course> courses;
    private int teacherId;
    private TeacherType teacherType;
    private Vector<Lesson> lessons;
    private Vector<Integer> rating;

    public Teacher(int employeeId, String firstName, String lastName, String password, String email, String phoneNumber, Date registrationDate, boolean isResearcher, String position, int teacherId, TeacherType teacherType) {
        super(employeeId, firstName, lastName, password, email, phoneNumber, registrationDate, isResearcher, position);
        this.teacherId = teacherId;
        this.teacherType = teacherType;
    }

    public Vector<Integer> getRating() {
        return rating;
    }

    public void setRating(Vector<Integer> rating) {
        this.rating = rating;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Vector<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Vector<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public void addRating(int rating) {
        if(rating <= 0){
            rating = 1;
        } else if (rating >= 11) {
            rating = 10;
        }
        this.rating.add(rating);
    }

    public void sendComplaint(Manager receiver, String message, Vector<Student> objects, ComplaintUrgency urgency) {
        if (receiver.getManagerType() != ManagerType.DEAN){
            if(Hub.getInstance().getLanguage() == Language.RUS) {
                System.out.println("Жалобы можно отправлять лишь декану");
            }
            else if(Hub.getInstance().getLanguage() == Language.KAZ) {
                System.out.println("-----");
            }
            else {
                System.out.println("Complaints can be sent only to dean");
            }
            return;
        }
        receiver.receiveComplaint(Hub.getInstance().getFactory().createComplaint(message, receiver, this, urgency, objects));
    }

    public void manageCourseAddStudentLesson(Course course, Lesson lesson, Student student) {
        if(courses.contains(course)){
            if(lessons.contains(lesson)){
                if(!lesson.getStudents().contains(student)){
                    lesson.assignStudent(student);
                    return;
                }

                if(Hub.getInstance().getLanguage() == Language.RUS) {
                    System.out.println("Студент уже назначен на урок");
                }
                else if(Hub.getInstance().getLanguage() == Language.KAZ) {
                    System.out.println("-----");
                }
                else {
                    System.out.println("Student already assigned to lesson");
                }
                return;
            }
        }
        if(Hub.getInstance().getLanguage() == Language.RUS) {
            System.out.println("Ошибка");
        }
        else if(Hub.getInstance().getLanguage() == Language.KAZ) {
            System.out.println("-----");
        }
        else {
            System.out.println("Error");
        }
    }

    public void manageCourseRemoveStudentLesson(Course course, Lesson lesson, Student student) {
        if(courses.contains(course)){
            if(lessons.contains(lesson)){
                if(!lesson.getStudents().contains(student)){
                    lesson.deassignStudent(student);
                    return;
                }

                if(Hub.getInstance().getLanguage() == Language.RUS) {
                    System.out.println("Студент не назначен на урок");
                }
                else if(Hub.getInstance().getLanguage() == Language.KAZ) {
                    System.out.println("-----");
                }
                else {
                    System.out.println("Student is not assigned to lesson");
                }
                return;
            }
        }
        if(Hub.getInstance().getLanguage() == Language.RUS) {
            System.out.println("Ошибка");
        }
        else if(Hub.getInstance().getLanguage() == Language.KAZ) {
            System.out.println("-----");
        }
        else {
            System.out.println("Error");
        }
    }

    public void viewCourses(){
        for(Course course : courses){
            System.out.println(course);
        }
    }

    public void viewLessons(){
        for(Lesson lesson : lessons){
            System.out.println(lesson);
        }
    }

    public void viewStudents(Course course) {
        for(Student student : course.getStudents()){
            System.out.println(student.getStudentId() + " " + student.getFirstName() + " " + student.getLastName());
        }
    }

    public void viewStudent(Student student){
        System.out.println(student);
    }

    public void assignMark(Course course, Student student, Mark mark) {
        for(Lesson lesson : lessons){
            if(lesson.getCourse().equals(course)){
                if(lesson.getStudents().contains(student)){
                    student.receiveMark(course, mark);
                    return;
                }
            }
        }
        if(Hub.getInstance().getLanguage() == Language.RUS) {
            System.out.println("Ошибка");
        }
        else if(Hub.getInstance().getLanguage() == Language.KAZ) {
            System.out.println("-----");
        }
        else {
            System.out.println("Error");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId && Objects.equals(courses, teacher.courses) && teacherType == teacher.teacherType && Objects.equals(lessons, teacher.lessons) && Objects.equals(rating, teacher.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courses, teacherId, teacherType, lessons, rating);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + getCourses() +
                ", teacherId=" + getTeacherId() +
                ", teacherType=" + getTeacherType() +
                ", lessons=" + getLessons() +
                ", rating=" + getRating() +
                ", researcher=" + isResearcher() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", registrationDate=" + getRegistrationDate() +
                '}';
    }
}
