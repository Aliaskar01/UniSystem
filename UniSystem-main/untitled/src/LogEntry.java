import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LogEntry implements Serializable, Comparable<LogEntry> {

    private String type;
    private Date date;
    private User user;

    public LogEntry(String type, Date date, User user) {
        this.type = type;
        this.date = date;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(LogEntry o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogEntry)) return false;
        LogEntry logEntry = (LogEntry) o;
        return Objects.equals(type, logEntry.type) && Objects.equals(date, logEntry.date) && Objects.equals(user, logEntry.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, user);
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "type='" + getType() + '\'' +
                ", date=" + getDate() +
                ", user=" + getUser() +
                '}';
    }
}
