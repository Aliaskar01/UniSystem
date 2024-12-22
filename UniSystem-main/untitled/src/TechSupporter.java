import java.util.Objects;
import java.util.Vector;

public class TechSupporter extends Employee {

    private int supportId;
    private Order orderInWork;
    private Vector<Order> orders;
    private Vector<String> logs;

    public TechSupporter(int employeeId, String firstName, String lastName, String password, String email, 
            String phoneNumber, java.util.Date registrationDate, String position, int supportId) {
    	super(employeeId, firstName, lastName, password, email, phoneNumber, registrationDate, false, position);
    	this.supportId = supportId;
    	this.orders = Hub.getInstance().getOrders();
    	this.logs = new Vector<>();
}

    public int getSupportId() {
        return supportId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public Order getOrderInWork() {
        return orderInWork;
    }

    public void setOrderInWork(Order orderInWork) {
        this.orderInWork = orderInWork;
    }

    public Vector<Order> getOrders() {
        return orders;
    }

    public void setOrders(Vector<Order> orders) {
        this.orders = orders;
    }

    public Vector<String> getLogs() {
        return logs;
    }

    public void viewNewOrders(Vector<Order> allOrders) {
        System.out.println("New Orders:");
        for (Order order : allOrders) {
            if (order.getStatus() == Status.UNKNOWN) {
                System.out.println("- " + order.getDescription());
            }
        }
    }

    
    public void acceptOrder(Order order) {
        if (order.getStatus() == Status.UNKNOWN) {
            order.setStatus(Status.ACCEPTED);
            orders.add(order);
            logs.add("TechSupporter " + getFirstName() + " accepted order: " + order.getDescription());
        } else {
            System.out.println("Order is not in a state to be accepted.");
        }
    }

   
    public void markOrderAsDone(Order order) {
        if (orders.contains(order) && order.getStatus() == Status.ACCEPTED) {
            order.setStatus(Status.DONE);
            logs.add("TechSupporter " + getFirstName() + " marked order as done: " + order.getDescription());
        } else {
            System.out.println("Order is not in a state to be marked as done, or it is not assigned to this supporter.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechSupporter)) return false;
        if (!super.equals(o)) return false;
        TechSupporter that = (TechSupporter) o;
        return supportId == that.supportId && Objects.equals(orderInWork, that.orderInWork) && Objects.equals(orders, that.orders) && Objects.equals(logs, that.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supportId, orderInWork, orders, logs);
    }

    @Override
    public String toString() {
        return "TechSupporter{" +
                "position='" + getPosition() + '\'' +
                ", supportId=" + getSupportId() +
                ", orderInWork=" + getOrderInWork() +
                ", orders=" + getOrders() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", registrationDate=" + getRegistrationDate() +
                '}';
    }
}
