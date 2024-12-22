import java.util.Date;
import java.util.Scanner;

public abstract class Command {
    private boolean error = false;
    private Hub hub = Hub.getInstance();
    public abstract void printInstruction();
    public abstract void receiveArguments();
    public abstract void execute();

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void printMessage(String eng, String rus, String kaz) {
        switch (hub.getLanguage()) {
            case RUS:
                System.out.println(rus);
                break;
            case KAZ:
                System.out.println(kaz);
                break;
            default:
                System.out.println(eng);
        }
    }
}

class LanguageCommand extends Command {
    private Language language;
    private String input;

    @Override
    public void printInstruction() {
        printMessage(
                "Choose language: type KAZ, ENG or RUS",
                "Выберите язык: введите KAZ, ENG или RUS",
                "Тілді таңдаңыз: KAZ, ENG немесе RUS енгізіңіз"
        );
    }

    @Override
    public void execute() {
        receiveArguments();
        if (isError()) {
            setError(false);
            return;
        }
        getHub().setLanguage(language);
    }

    @Override
    public void receiveArguments() {
        Scanner scanner = new Scanner(System.in);
        try {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("KAZ")) {
                language = Language.KAZ;
            } else if (input.equalsIgnoreCase("ENG")) {
                language = Language.ENG;
            } else if (input.equalsIgnoreCase("RUS")) {
                language = Language.RUS;
            } else {
                language = Language.ENG;
            }
        } catch (Exception e) {
            e.printStackTrace();
            setError(true);
        }
    }
}

class LogInCommand extends Command {
    private String email;
    private String password;

    @Override
    public void printInstruction() {
        printMessage(
                "Welcome to UniSystem! You need to log into the system. Enter email, then password",
                "Добро пожаловать в UniSystem! Необходимо войти в систему. Введите почту, затем пароль",
                "UniSystem-ге қош келдіңіз! Жүйеге кіру керек. Электрондық пошта мен құпия сөзді енгізіңіз"
        );
    }

    @Override
    public void execute() {
        receiveArguments();
        if (isError()) {
            setError(false);
            return;
        }
        getHub().loggedUser = getHub().logIn(email, password);
        if (getHub().loggedUser != null) {
            getHub().addLogEntry(getHub().getFactory().createLogEntry("Log in", new Date(), getHub().loggedUser));
        } else {
            printMessage(
                    "Invalid email or password.",
                    "Неверная почта или пароль.",
                    "Электрондық пошта немесе құпия сөз қате."
            );
        }
    }

    @Override
    public void receiveArguments() {
        Scanner scanner = new Scanner(System.in);
        try {
            printMessage("Enter email:", "Введите почту:", "Электрондық пошта енгізіңіз:");
            email = scanner.nextLine();

            printMessage("Enter password:", "Введите пароль:", "Құпия сөз енгізіңіз:");
            password = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            setError(true);
        }
    }
}

class ManageUsersCommand extends Command {
    private String action;
    private String user;

    @Override
    public void printInstruction() {
        printMessage(
                "Manage users: Enter ADD, REMOVE, or CHANGE followed by user details",
                "Управление пользователями: введите ADD, REMOVE или CHANGE и данные пользователя",
                "Пайдаланушыларды басқару: ADD, REMOVE немесе CHANGE және пайдаланушы мәліметтерін енгізіңіз"
        );
    }

    @Override
    public void execute() {
        receiveArguments();
        if (isError()) {
            setError(false);
            return;
        }
    }

    @Override
    public void receiveArguments() {
        Scanner scanner = new Scanner(System.in);
        try {
            action = scanner.nextLine();
            user = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            setError(true);
        }
    }
}

class SeeLogsCommand extends Command {
    private Admin admin = (Admin) getHub().loggedUser;

    @Override
    public void printInstruction() {
        printMessage("Viewing logs...", "Просматриваем логи...", "Журналдарды қарау...");
    }

    @Override
    public void execute() {
        if (admin != null) {
            admin.viewLogs();
        } else {
            printMessage(
                    "Only admins can view logs.",
                    "Только администраторы могут просматривать логи.",
                    "Журналдарды тек әкімшілер көре алады."
            );
        }
    }

    @Override
    public void receiveArguments() {
    }
}

class SeeCoursesCommand extends Command {
    private Teacher teacher = (Teacher) getHub().loggedUser;

    @Override
    public void printInstruction() {
        printMessage(
                "Viewing courses you teach...",
                "Просматриваем курсы, которые преподаются Вами...",
                "Сіз оқытатын курстарды қарау..."
        );
    }

    @Override
    public void execute() {
        if (teacher != null) {
            teacher.viewCourses();
        } else {
            printMessage(
                    "Only teachers can view their courses.",
                    "Только учителя могут просматривать свои курсы.",
                    "Тек мұғалімдер өз курстарын көре алады."
            );
        }
    }

    @Override
    public void receiveArguments() {
    }
}

class CourseStudentManagementCommand extends Command {
    private Teacher teacher = (Teacher) getHub().loggedUser;

    @Override
    public void printInstruction() {
        printMessage(
                "Manage students in your courses...",
                "Управление студентами в Ваших курсах...",
                "Сіздің курстарыңыздағы студенттерді басқару..."
        );
    }

    @Override
    public void execute() {
        if (teacher != null) {
            teacher.viewCourses();
        } else {
            printMessage(
                    "Only teachers can manage their courses.",
                    "Только учителя могут управлять своими курсами.",
                    "Тек мұғалімдер өз курстарын басқара алады."
            );
        }
    }

    @Override
    public void receiveArguments() {
    }
}

class StudentAddMarkCommand extends Command {
    private Teacher teacher = (Teacher) getHub().loggedUser;
    private String studentId;
    private int mark;

    @Override
    public void printInstruction() {
        printMessage(
                "Add marks for students: Enter student ID and mark",
                "Добавление оценок студентам: введите ID студента и оценку",
                "Студенттерге баға қою: Студенттің ID-ін және бағасын енгізіңіз"
        );
    }

    @Override
    public void execute() {
        receiveArguments();
        if (isError()) {
            setError(false);
            return;
        }
    }

    @Override
    public void receiveArguments() {
        Scanner scanner = new Scanner(System.in);
        try {
            printMessage("Enter student ID:", "Введите ID студента:", "Студенттің ID-ін енгізіңіз:");
            studentId = scanner.nextLine();

            printMessage("Enter mark:", "Введите оценку:", "Бағасын енгізіңіз:");
            mark = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
            setError(true);
        }
    }
}