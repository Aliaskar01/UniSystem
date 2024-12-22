import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Request extends Message implements Serializable {

    private Manager signedBy;
    private Manager requestRecipient;

    public Request(Employee recipient, Employee sender, Date timestamp, String content, Manager requestRecipient) {
        super(recipient, sender, timestamp, content);
        this.requestRecipient = requestRecipient;
    }

    public Manager getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(Manager signedBy) {
        this.signedBy = signedBy;
    }

    public Manager getRequestRecipient() {
        return requestRecipient;
    }

    public void setRequestRecipient(Manager requestRecipient) {
        this.requestRecipient = requestRecipient;
    }

    public void sign(Manager manager) {
        this.signedBy = manager;
        requestRecipient.addRequest(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        if (!super.equals(o)) return false;
        Request request = (Request) o;
        return Objects.equals(signedBy, request.signedBy) && Objects.equals(requestRecipient, request.requestRecipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), signedBy, requestRecipient);
    }

    @Override
    public String toString() {
        return "Request{" +
                "signedBy=" + getSignedBy() +
                ", requestRecipient=" + getRequestRecipient() +
                ", recipient=" + getRecipient() +
                ", sender=" + getSender() +
                ", timestamp=" + getTimestamp() +
                ", content='" + getContent() + '\'' +
                '}';
    }
}
