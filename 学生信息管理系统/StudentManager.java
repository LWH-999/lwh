import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentManager() {
        loadFromFile();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    public boolean removeStudentById(String id) {
        Iterator<Student> it = students.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getStudentId().equals(id)) {
                it.remove();
                removed = true;
            }
        }
        if (removed) saveToFile();
        return removed;
    }

    public Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) return s;
        }
        return null;
    }

    public List<Student> findStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().contains(name)) result.add(s);
        }
        return result;
    }

    public void updateStudent(Student updated) {
        for (int i = 0; i < students.size(); ++i) {
            if (students.get(i).getStudentId().equals(updated.getStudentId())) {
                students.set(i, updated);
                saveToFile();
                return;
            }
        }
    }

    public void loadFromFile() {
        students.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Student s = Student.fromFileString(line);
                    students.add(s);
                } catch (Exception e) {
                    // skip error lines
                }
            }
        } catch (IOException e) {
            System.out.println("读取学生文件出错：" + e.getMessage());
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("保存学生文件出错：" + e.getMessage());
        }
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("无学生信息。");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }
}