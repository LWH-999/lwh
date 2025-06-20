import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    private String studentId;
    private String name;
    private String gender;
    private Date birthDate;
    private String politicalStatus;
    private String address;
    private String phone;
    private String dormitory;

    public Student(String studentId, String name, String gender, Date birthDate,
                   String politicalStatus, String address, String phone, String dormitory) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.politicalStatus = politicalStatus;
        this.address = address;
        this.phone = phone;
        this.dormitory = dormitory;
    }

    // Getter and Setter methods
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public String getPoliticalStatus() { return politicalStatus; }
    public void setPoliticalStatus(String politicalStatus) { this.politicalStatus = politicalStatus; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDormitory() { return dormitory; }
    public void setDormitory(String dormitory) { this.dormitory = dormitory; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format(
                "学号：%s  姓名：%s  性别：%s  出生日期：%s  政治面貌：%s  家庭住址：%s  电话：%s  宿舍号：%s",
                studentId, name, gender, sdf.format(birthDate), politicalStatus, address, phone, dormitory
        );
    }

    public String toFileString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.join(",",
                studentId, name, gender, sdf.format(birthDate), politicalStatus, address, phone, dormitory
        );
    }

    public static Student fromFileString(String line) throws Exception {
        String[] parts = line.split(",", -1);
        if (parts.length != 8) throw new Exception("格式错误");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new Student(
                parts[0], parts[1], parts[2], sdf.parse(parts[3]),
                parts[4], parts[5], parts[6], parts[7]
        );
    }
}