import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class StudentOrganization implements Serializable, Comparable<StudentOrganization> {

    private String name;
    private String description;
    private Student head;
    private Vector<Student> members;

    public StudentOrganization(String name, String description) {
        this.name = name;
        this.members = new Vector<Student>();
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<Student> getMembers() {
        return members;
    }

    public void setMembers(Vector<Student> members) {
        this.members = members;
    }

    public void addMember(Student student) {
        members.add(student);
        student.getOrganizations().add(this);
    }
    public void removeMember(Student student) {
        members.remove(student);
        student.getOrganizations().remove(this);
    }

    public void removeMemberDeletion(Student student) {
        members.remove(student);
    }

    public Student getHead() {
        return head;
    }

    public void setHead(Student head) {
        this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentOrganization)) return false;
        StudentOrganization that = (StudentOrganization) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, members);
    }

    @Override
    public String toString() {
        return "StudentOrganization{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", members=" + getMembers() +
                '}';
    }

    @Override
    public int compareTo(StudentOrganization o) {
        return getMembers().size()-o.getMembers().size();
    }
}
