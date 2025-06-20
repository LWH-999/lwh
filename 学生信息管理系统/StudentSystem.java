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
                default: System.out.println("无效输入，请重新选择。");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- 学生基本信息管理系统 ---");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 删除学生信息");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 查询学生信息");
        System.out.println("5. 显示全部学生信息");
        System.out.println("0. 退出系统");
        System.out.print("请选择操作：");
    }

    private static void addStudent() {
        Student s = inputStudentInfo(null);
        if (manager.findStudentById(s.getStudentId()) != null) {
            System.out.println("该学号已存在。");
            return;
        }
        manager.addStudent(s);
        System.out.println("添加成功！");
    }

    private static void deleteStudent() {
        System.out.print("请输入要删除的学生学号：");
        String id = sc.nextLine().trim();
        boolean removed = manager.removeStudentById(id);
        if (removed) System.out.println("删除成功！");
        else System.out.println("未找到该学生。");
    }

    private static void updateStudent() {
        System.out.print("请输入要修改的学生学号：");
        String id = sc.nextLine().trim();
        Student s = manager.findStudentById(id);
        if (s == null) {
            System.out.println("未找到该学生。");
            return;
        }
        System.out.println("当前学生信息：");
        System.out.println(s);
        Student updated = inputStudentInfo(id);
        manager.updateStudent(updated);
        System.out.println("修改成功！");
    }

    private static void queryStudent() {
        System.out.println("1. 按学号查询");
        System.out.println("2. 按姓名查询");
        System.out.print("请选择查询方式：");
        String type = sc.nextLine().trim();
        switch (type) {
            case "1":
                System.out.print("请输入学号：");
                String id = sc.nextLine().trim();
                Student s = manager.findStudentById(id);
                if (s == null) System.out.println("未找到该学生。");
                else System.out.println(s);
                break;
            case "2":
                System.out.print("请输入姓名：");
                String name = sc.nextLine().trim();
                List<Student> list = manager.findStudentsByName(name);
                if (list.isEmpty()) System.out.println("未找到相关学生。");
                else for (Student stu : list) System.out.println(stu);
                break;
            default:
                System.out.println("无效选择。");
        }
    }

    private static void listAllStudents() {
        manager.printAllStudents();
    }

    private static void exitSystem() {
        System.out.println("感谢使用，已退出系统。");
    }

    private static Student inputStudentInfo(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String studentId = id;
        if (id == null) {
            System.out.print("学号：");
            studentId = sc.nextLine().trim();
        }
        System.out.print("姓名：");
        String name = sc.nextLine().trim();
        System.out.print("性别：");
        String gender = sc.nextLine().trim();
        Date birth = null;
        while (true) {
            System.out.print("出生日期(yyyy-MM-dd)：");
            String birthStr = sc.nextLine().trim();
            try {
                birth = sdf.parse(birthStr);
                break;
            } catch (ParseException e) {
                System.out.println("日期格式错误，请重新输入。");
            }
        }
        System.out.print("政治面貌：");
        String political = sc.nextLine().trim();
        System.out.print("家庭住址：");
        String address = sc.nextLine().trim();
        System.out.print("电话：");
        String phone = sc.nextLine().trim();
        System.out.print("宿舍号：");
        String dorm = sc.nextLine().trim();
        return new Student(studentId, name, gender, birth, political, address, phone, dorm);
    }
}