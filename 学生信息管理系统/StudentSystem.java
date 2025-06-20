import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addStudent(); break;
                case "2": deleteStudent(); break;
                case "3": updateStudent(); break;
                case "4": queryStudent(); break;
                case "5": listAllStudents(); break;
                case "0": exitSystem(); return;
                default: System.out.println("��Ч���룬������ѡ��");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- ѧ��������Ϣ����ϵͳ ---");
        System.out.println("1. ���ѧ����Ϣ");
        System.out.println("2. ɾ��ѧ����Ϣ");
        System.out.println("3. �޸�ѧ����Ϣ");
        System.out.println("4. ��ѯѧ����Ϣ");
        System.out.println("5. ��ʾȫ��ѧ����Ϣ");
        System.out.println("0. �˳�ϵͳ");
        System.out.print("��ѡ�������");
    }

    private static void addStudent() {
        Student s = inputStudentInfo(null);
        if (manager.findStudentById(s.getStudentId()) != null) {
            System.out.println("��ѧ���Ѵ��ڡ�");
            return;
        }
        manager.addStudent(s);
        System.out.println("��ӳɹ���");
    }

    private static void deleteStudent() {
        System.out.print("������Ҫɾ����ѧ��ѧ�ţ�");
        String id = sc.nextLine().trim();
        boolean removed = manager.removeStudentById(id);
        if (removed) System.out.println("ɾ���ɹ���");
        else System.out.println("δ�ҵ���ѧ����");
    }

    private static void updateStudent() {
        System.out.print("������Ҫ�޸ĵ�ѧ��ѧ�ţ�");
        String id = sc.nextLine().trim();
        Student s = manager.findStudentById(id);
        if (s == null) {
            System.out.println("δ�ҵ���ѧ����");
            return;
        }
        System.out.println("��ǰѧ����Ϣ��");
        System.out.println(s);
        Student updated = inputStudentInfo(id);
        manager.updateStudent(updated);
        System.out.println("�޸ĳɹ���");
    }

    private static void queryStudent() {
        System.out.println("1. ��ѧ�Ų�ѯ");
        System.out.println("2. ��������ѯ");
        System.out.print("��ѡ���ѯ��ʽ��");
        String type = sc.nextLine().trim();
        switch (type) {
            case "1":
                System.out.print("������ѧ�ţ�");
                String id = sc.nextLine().trim();
                Student s = manager.findStudentById(id);
                if (s == null) System.out.println("δ�ҵ���ѧ����");
                else System.out.println(s);
                break;
            case "2":
                System.out.print("������������");
                String name = sc.nextLine().trim();
                List<Student> list = manager.findStudentsByName(name);
                if (list.isEmpty()) System.out.println("δ�ҵ����ѧ����");
                else for (Student stu : list) System.out.println(stu);
                break;
            default:
                System.out.println("��Чѡ��");
        }
    }

    private static void listAllStudents() {
        manager.printAllStudents();
    }

    private static void exitSystem() {
        System.out.println("��лʹ�ã����˳�ϵͳ��");
    }

    private static Student inputStudentInfo(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String studentId = id;
        if (id == null) {
            System.out.print("ѧ�ţ�");
            studentId = sc.nextLine().trim();
        }
        System.out.print("������");
        String name = sc.nextLine().trim();
        System.out.print("�Ա�");
        String gender = sc.nextLine().trim();
        Date birth = null;
        while (true) {
            System.out.print("��������(yyyy-MM-dd)��");
            String birthStr = sc.nextLine().trim();
            try {
                birth = sdf.parse(birthStr);
                break;
            } catch (ParseException e) {
                System.out.println("���ڸ�ʽ�������������롣");
            }
        }
        System.out.print("������ò��");
        String political = sc.nextLine().trim();
        System.out.print("��ͥסַ��");
        String address = sc.nextLine().trim();
        System.out.print("�绰��");
        String phone = sc.nextLine().trim();
        System.out.print("����ţ�");
        String dorm = sc.nextLine().trim();
        return new Student(studentId, name, gender, birth, political, address, phone, dorm);
    }
}