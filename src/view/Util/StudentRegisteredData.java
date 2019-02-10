package view.Util;

import java.time.LocalDate;

public class StudentRegisteredData {

    String studentID;
    String studentName;
    String batchID;
    LocalDate registeredDate;

    public StudentRegisteredData() {
    }

    public StudentRegisteredData(String studentID, String studentName, String batchID, LocalDate registeredDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.batchID = batchID;
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

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}


