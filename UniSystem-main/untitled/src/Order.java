import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order extends Message implements Serializable {

    private Employee assignedTo;
    private Date createdAt;
    private Date completedAt;
    private Status status;
    private String description;
    private int orderId;
    private TechSupporter techSupporter;

    public Order(Employee recipient, Employee sender, Date timestamp, String content, String description, int orderId) {
        super(recipient, sender, timestamp, content);
        this.createdAt = new Date();
        this.description = description;
        this.orderId = orderId;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public TechSupporter getTechSupporter() {
        return techSupporter;
    }

    public void setTechSupporter(TechSupporter techSupporter) {
        this.techSupporter = techSupporter;
    }

    public void acceptOrder() {
        if (status == Status.UNKNOWN) {
            status = Status.ACCEPTED;
            createdAt = new Date();
        } else {
            System.out.println("Order cannot be accepted. Current status: " + status);
        }
    }

    public void rejectOrder() {
        if (status == Status.UNKNOWN) {
            status = Status.REJECTED;
            completedAt = new Date();
        } else {
            System.out.println("Order cannot be rejected. Current status: " + status);
        }
    }

    public void markAsDone() {
        if (status == Status.ACCEPTED) {
            status = Status.DONE;
            completedAt = new Date();
        } else {
            System.out.println("Order cannot be marked as done. Current status: " + status);
        }
    }

    public String getOrderDetails() {
        return "Order ID: " + orderId + "\nDescription: " + description + "\nStatus: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(assignedTo, order.assignedTo) && Objects.equals(createdAt, order.createdAt) && Objects.equals(completedAt, order.completedAt) && status == order.status && Objects.equals(description, order.description) && Objects.equals(techSupporter, order.techSupporter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignedTo, createdAt, completedAt, status, description, orderId, techSupporter);
    }

    @Override
    public String toString() {
        return "Order{" +
                "assignedTo=" + getAssignedTo() +
                ", createdAt=" + getCreatedAt() +
                ", completedAt=" + getCompletedAt() +
                ", status=" + getStatus() +
                ", description='" + getDescription() + '\'' +
                ", orderId=" + getOrderId() +
                ", techSupporter=" + getTechSupporter() +
                '}';
    }
}
