import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;


public class Student extends User{

    private String major;
    private String school;
    private int studentId;
    private int maxCredits = 21;
    private StudentType studentType;
    private int year;
    private Researcher supervisor;
    private Vector<Course> courses;
    private Vector<Lesson> lessons;
    private Vector<Course> completedCourses;
    private Vector<Course> pendingCourses;
    private HashMap<Course, Vector<Mark>> marks;
    private Vector<StudentOrganization> organizations;



    public Student(int studentId, String firstName, String lastName, String password, String email, String phoneNumber, Date registrationDate, boolean isResearcher, String major, StudentType studentType, int year, String school) {
        super(studentId, firstName, lastName, password, email, phoneNumber, registrationDate, isResearcher);
        this.major = major;
        this.studentId = studentId;
        this.studentType = studentType;
        this.year = year;
        this.completedCourses = new Vector<>();
        this.courses = new Vector<>();
        this.pendingCourses = new Vector<>();
        this.marks = new HashMap<Course, Vector<Mark>>();
        this.organizations = new Vector<>();
        this.school = school;
    }

    public Vector<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Vector<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSupervisor(Researcher supervisor)throws ResearcherIndexException {
        try {
            if (supervisor.calculateHIndex() < 3) {
                throw new ResearcherIndexException("H Index is too low");
            }
            else {
                this.supervisor = supervisor;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Vector<Course> getPendingCourses() {
        return pendingCourses;
    }

    public void setPendingCourses(Vector<Course> pendingCourses) {
        this.pendingCourses = pendingCourses;
    }

    public Vector<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(Vector<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public HashMap<Course, Vector<Mark>> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<Course, Vector<Mark>> marks) {
        this.marks = marks;
    }


    public Vector<StudentOrganization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Vector<StudentOrganization> organizations) {
        this.organizations = organizations;
    }

    public HashMap<Course, HashMap<String, Double>> getTranscript() {
        HashMap<Course, HashMap<String, Double>> transcript = new HashMap<>();
        for(Course course:marks.keySet()) {
            HashMap<String, Double> courseTranscript = new HashMap<>();
            double first = 0;
            double second = 0;
            double fExam = 0;
            for(Mark mark:marks.get(course)) {
                if(mark.getMarkTypeAttestation() == MarkTypeAttestation.FIRST){
                    first += mark.getValue();
                }
                else if(mark.getMarkTypeAttestation() == MarkTypeAttestation.SECOND){
                    second += mark.getValue();
                }
                else if(mark.getMarkTypeAttestation() == MarkTypeAttestation.FINAL){
                    fExam += mark.getValue();
                }
            }
            courseTranscript.put("first", first);
            courseTranscript.put("second", second);
            courseTranscript.put("final", fExam);
            courseTranscript.put("total", first+second+fExam);
            transcript.put(course, courseTranscript);

        }
        return transcript;
    }

    public void viewTranscript() {
        HashMap<Course, HashMap<String, Double>> transcript = getTranscript();

        String firstAttestation, secondAttestation, finalExam, total;

        for (Course course : transcript.keySet()) {
            System.out.println("=========");
            System.out.println(course.getCourseNameEntry(Hub.getInstance().getLanguage()));

            if (Hub.getInstance().getLanguage() == Language.RUS) {
                firstAttestation = "Первая Аттестация: ";
                secondAttestation = "Вторая Аттестация: ";
                finalExam = "Сессия: ";
                total = "Сумма: ";
            } else if (Hub.getInstance().getLanguage() == Language.KAZ) {
                firstAttestation = "-----";
                secondAttestation = "-----";
                finalExam = "-----";
                total = "-----";
            } else {
                firstAttestation = "First Attestation: ";
                secondAttestation = "Second Attestation: ";
                finalExam = "Final Exam: ";
                total = "Total: ";
            }

            System.out.println(firstAttestation + transcript.get(course).get("first"));
            System.out.println(secondAttestation + transcript.get(course).get("second"));
            System.out.println(finalExam + transcript.get(course).get("final"));
            System.out.println(total + transcript.get(course).get("total") + " " + gpaScale(transcript.get(course).get("total")));
            System.out.println("=========");
        }
    }


    private String gpaScale(double score){
        if (score >= 95 && score <= 100) {
            return "A";
        } else if (score >= 90) {
            return "A-";
        } else if (score >= 85) {
            return "B+";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 75) {
            return "B-";
        } else if (score >= 70) {
            return "C+";
        } else if (score >= 65) {
            return "C";
        } else if (score >= 60) {
            return "C-";
        } else if (score >= 50) {
            return "D";
        } else if (score >= 0) {
            return "F";
        } else {
            return "";
        }

    }

    public void viewMarks() {
        for (Course course : marks.keySet()) {
            System.out.println(course.getCourseNameEntry(Hub.getInstance().getLanguage()));

            String markTypeLabel, markValueLabel;

            if (Hub.getInstance().getLanguage() == Language.RUS) {
                markTypeLabel = "Тип аттестации: ";
                markValueLabel = "Оценка: ";
            } else if (Hub.getInstance().getLanguage() == Language.KAZ) {
                markTypeLabel = "Аттестация түрі: ";
                markValueLabel = "Бағасы: ";
            } else {
                markTypeLabel = "Mark Type: ";
                markValueLabel = "Mark: ";
            }

            for (Mark mark : marks.get(course)) {
                System.out.println(markTypeLabel + mark.getMarkTypeAttestation() + ", " + markValueLabel + mark.getValue() + " (" + mark.getMarkType() + ")");
            }
        }
    }


    public void receiveMark(Course course, Mark mark) {
        marks.get(course).add(mark);
    }

    public void rateTeacher(Teacher teacher, Integer rating) {
        if (rating < 1) {
            rating = 1;
        } else if (rating > 10) {
            rating = 10;
        }

        if (Hub.getInstance().getLanguage() == Language.RUS) {
            System.out.println("Вы оценили преподавателя: " + rating + " баллов.");
        } else if (Hub.getInstance().getLanguage() == Language.KAZ) {
            System.out.println("Сіз оқытушыны " + rating + " баллмен бағаладыңыз.");
        } else {
            System.out.println("You rated the teacher: " + rating + " points.");
        }

        teacher.addRating(rating);
    }

    public void viewTeacherInfo(Course course) {
        for(Teacher teacher : course.getTeachers()) {
            System.out.println(teacher);
        }
    }
    public void leaveOrganziation(StudentOrganization organization) {
        organization.removeMember(this);
    }
    public void joinOrganization(StudentOrganization organization) {
        organization.addMember(this);
    }
    public void becomeHeadOfOrganization(StudentOrganization organization) {
        organization.setHead(this);
    }

    public void registerCourse(Course course) {
        if(!pendingCourses.contains(course)) {
            if(Hub.getInstance().getMajorCoursesMap().get(course).get(major) != CourseType.UNAVAILABLE) {
                if(year >= course.getYear()){
                    if (getPendingCredits() + course.getCredits() <= maxCredits) {
                        pendingCourses.add(course);
                        return;
                    }
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

    private int getPendingCredits(){
        int result = 0;
        for(Course course : pendingCourses) {
            result += course.getCredits();
        }
        return result;
    }

    public Vector<ResearchPaper> getDiplomaProject() {
        return getResearchPapers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentId == student.studentId && maxCredits == student.maxCredits && year == student.year && Objects.equals(major, student.major) && studentType == student.studentType && Objects.equals(supervisor, student.supervisor) && Objects.equals(courses, student.courses) && Objects.equals(completedCourses, student.completedCourses) && Objects.equals(pendingCourses, student.pendingCourses) && Objects.equals(marks, student.marks) && Objects.equals(organizations, student.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), major, studentId, maxCredits, studentType, year, supervisor, courses, completedCourses, pendingCourses, marks, organizations);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentType=" + getStudentType() +
                ", year=" + getYear() +
                ", supervisor=" + getSupervisor() +
                ", studentId=" + getStudentId() +
                ", major='" + getMajor() + '\'' +
                ", researcher=" + isResearcher() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", registrationDate=" + getRegistrationDate() +
                '}';
    }
}
