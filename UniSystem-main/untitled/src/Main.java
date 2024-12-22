public class Main {
    public static void main(String[] args) {
        //Hub.getInstance();
        //Hub.getInstance().addUser(Hub.getInstance().getFactory().createStudent("a", "b", "asd", "test@abc.com", "+8132443123", false, "TESTMAJOR", 1, "Testschool"));
        //Hub.getInstance().serialize();
        Hub.getInstance().deserialize();
        System.out.println(Hub.getInstance().getUsers());
        System.out.println(Hub.getInstance().getStudents());

    }
}