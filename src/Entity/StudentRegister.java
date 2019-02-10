package Entity;

import java.time.LocalDate;

public class StudentRegister  extends SuperEntity{

    String studentID;
    String studentName;
    String courseID;
    String courseName;
    LocalDate registeredDate;

    public StudentRegister() {
    }

    public StudentRegister(String studentID, String studentName, String courseID, String courseName, LocalDate registeredDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.courseID = courseID;
        this.courseName = courseName;
        this.registeredDate = registeredDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
