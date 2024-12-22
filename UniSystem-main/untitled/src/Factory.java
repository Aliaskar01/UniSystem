import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class Factory extends ObjectFactory {
    @Override
    public void create() {
        Hub.getInstance().setObjectIdCounter(Hub.getInstance().getObjectIdCounter() + 1);
    }

    public ResearchPaper createResearchPaper(String title, Vector<Researcher> authors, int pages, String doi, int citations, Date publicationDate) {
        create();
        ResearchPaper result = new ResearchPaper(title, authors, pages, doi, citations, publicationDate);
        return result;
    }
    public ResearchProject createResearchProject(String topic, Vector<ResearchPaper> publishedPapers, Vector<Researcher> participants){
        create();
        ResearchProject result = new ResearchProject(topic, publishedPapers, participants);
        return result;
    }
    public Message createMessage(String content, User recipient, User sender) {
        create();
        Message result = new Message(recipient, sender, new Date(), content);
        return result;
    }

    public Complaint createComplaint(String content, Manager recipient, Teacher sender,ComplaintUrgency urgency, Vector<Student> objects) {
        create();
        Complaint result = new Complaint(recipient, sender, new Date(), content, urgency, objects);
        return result;
    }

    public News createNews(String title, String content, String topic){
        create();
        News result = new News(Hub.getInstance().getObjectIdCounter(), title, content, topic, new Date());
        return result;
    }

    public Mark createMark(int value, int studentId, HashMap<Language, String> courseId, MarkType markType, MarkTypeAttestation markTypeAttestation){
        create();
        Mark result = new Mark(value, studentId, courseId, markType, markTypeAttestation);
        return result;
    }
    public LogEntry createLogEntry(String type, Date date, User user){
        create();
        LogEntry result = new LogEntry(type, date, user);
        return result;
    }

    public Student createStudent(String firstName, String lastName, String password, String email, String phoneNumber, boolean isResearcher, String major, int year, String school){
        create();
        Student result = new Student(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(), isResearcher, major, StudentType.BACHELOR, year, school);
        return result;
    }

    public Teacher createTeacher(String firstName, String lastName, String password, String email, String phoneNumber, boolean isResearcher, TeacherType teacherType){
        create();
        Teacher result = new Teacher(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(), isResearcher, "Teacher", Hub.getInstance().getObjectIdCounter(), teacherType);
        return result;
    }

    public Manager createManager(String firstName, String lastName, String password, String email, String phoneNumber, boolean isResearcher, ManagerType managerType){
        create();
        Manager result = new Manager(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(), isResearcher, "Manager", Hub.getInstance().getObjectIdCounter(), managerType);
        return result;
    }

    public Employee createEmployee(String firstName, String lastName, String password, String email, String phoneNumber, boolean isResearcher, String position){
        create();
        Employee result = new Employee(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(), isResearcher, position);
        return result;
    }

    public Admin createAdmin(String firstName, String lastName, String password, String email, String phoneNumber, boolean isResearcher){
        create();
        Admin result = new Admin(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(), isResearcher, "Admin", Hub.getInstance().getObjectIdCounter());
        return result;
    }

    public TechSupporter createTechSupporter(String firstName, String lastName, String password, String email, String phoneNumber){
        create();
        TechSupporter result = new TechSupporter(Hub.getInstance().getObjectIdCounter(), firstName, lastName, password, email, phoneNumber, new Date(),"Tech Supporter", Hub.getInstance().getObjectIdCounter());
        return result;
    }

    public StudentOrganization createStudentOrganization(String name, String description){
        create();
        StudentOrganization result = new StudentOrganization(name, description);
        return result;
    }

    public Request createRequest(Employee recipient, Employee sender, String content, Manager requestRecipient){
        create();
        Request result = new Request(recipient, sender, new Date(), content, requestRecipient);
        return result;
    }

    public Order createOrder(Employee recipient, Employee sender, String content, String description){
        create();
        Order result = new Order(recipient, sender, new Date(), content, description, Hub.getInstance().getObjectIdCounter());
        return result;
    }

    public Lesson createLesson(Course course, Teacher teacher, Time scheduledAt, Time endAt, String description, String title, LessonType type){
        create();
        Lesson result = new Lesson(Hub.getInstance().getObjectIdCounter(), course, teacher, scheduledAt, endAt, description, title, type);
        return result;
    }

    public Journal createJournal(String name, String topic){
        create();
        Journal result = new Journal(Hub.getInstance().getObjectIdCounter(), name, topic);
        return result;
    }

    public Course createCourse(HashMap<Language, String> courseName, int credits, int year, CourseType courseType, String parentDepartment){
        create();
        Course result = new Course(Hub.getInstance().getObjectIdCounter(), courseName, credits, year, courseType, parentDepartment);
        return result;
    }

    public CommentNews createCommentNews(News parentNews, String content, User author){
        create();
        CommentNews result = new CommentNews(parentNews, content, author);
        return result;
    }








}
