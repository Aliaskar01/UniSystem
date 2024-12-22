import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Employee extends User {

    private String position;
    private int employeeId;
    private Vector<Message> messages;


    public Employee(int employeeId, String firstName, String lastName, String password, String email, String phoneNumber, Date registrationDate, boolean isResearcher, String position) {
        super(employeeId, firstName, lastName, password, email, phoneNumber, registrationDate, isResearcher);
        this.position = position;
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setMessages(Vector<Message> messages) {
        this.messages = messages;
    }




    public void seeMessages() {
        System.out.println("Printing messages: \n");
        for (Message m : messages) {
            System.out.println(m);
        }
    }

    public void sendMessage(Employee receiver, String message) {
        receiver.receiveMessage(Hub.getInstance().getFactory().createMessage(message, receiver, this));
    }

    public void receiveMessage(Message message) {
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(position, employee.position) && Objects.equals(messages, employee.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, employeeId, messages);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + getEmployeeId() +
                ", position='" + getPosition() + '\'' +
                ", researcher=" + isResearcher() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", registrationDate=" + getRegistrationDate() +
                '}';
    }
}