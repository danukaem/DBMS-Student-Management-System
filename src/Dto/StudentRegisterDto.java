package Dto;

import java.time.LocalDate;

public class StudentRegisterDto  extends SuperDTO {
    String studentID;
    String studentName;
    String BatchID;
    LocalDate registeredDate;

    public StudentRegisterDto() {
    }

    public StudentRegisterDto(String studentID, String studentName, String batchID, LocalDate registeredDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        BatchID = batchID;
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
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}