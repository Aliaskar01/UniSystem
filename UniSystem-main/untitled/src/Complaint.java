import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Complaint extends Message{

    private ComplaintUrgency urgency;
    private Vector<Student> objects;

    public Complaint(Manager recipient, Teacher sender, Date timestamp, String content, ComplaintUrgency urgency, Vector<Student> objects) {
        super(recipient, sender, timestamp, content);
        this.urgency = urgency;
        this.objects = objects;
    }

    public ComplaintUrgency getUrgency() {
        return urgency;
    }

    public void setUrgency(ComplaintUrgency urgency) {
        this.urgency = urgency;
    }

    public Vector<Student> getObjects() {
        return objects;
    }

    public void setObjects(Vector<Student> objects) {
        this.objects = objects;
    }


    @Override
    public String toString() {
        return "Complaint{" +
                "urgency=" + getUrgency() +
                ", objects=" + getObjects() +
                ", timestamp=" + getTimestamp() +
                ", content='" + getContent() + '\'' +
                ", sender=" + getSender() +
                '}';
    }
}
