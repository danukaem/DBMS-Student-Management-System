package Entity;

import java.time.LocalDate;

public class Student  extends SuperEntity{
    String StdInitialName;
    String FullName;
    String Address;
    String Telephone;
    String Email;
    String Mobile;
    String NIC;
    String StdtId;
    String City;
    LocalDate Date;
    String School;
    String University;
    String Faculty;
    String cmbCourse;
    String cmbBatch;
    String cmbGender;
    String cmbQualification;

    public Student() {
    }

    public Student(String stdInitialName, String fullName, String address, String telephone, String email, String mobile, String NIC, String stdtId, String city, LocalDate date, String school, String university, String faculty, String cmbCourse, String cmbBatch, String cmbGender, String cmbQualification) {
        StdInitialName = stdInitialName;
        FullName = fullName;
        Address = address;
        Telephone = telephone;
        Email = email;
        Mobile = mobile;
        this.NIC = NIC;
        StdtId = stdtId;
        City = city;
        Date = date;
        School = school;
        University = university;
        Faculty = faculty;
        this.cmbCourse = cmbCourse;
        this.cmbBatch = cmbBatch;
        this.cmbGender = cmbGender;
        this.cmbQualification = cmbQualification;
    }

    public String getStdInitialName() {
        return StdInitialName;
    }

    public void setStdInitialName(String stdInitialName) {
        StdInitialName = stdInitialName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getStdtId() {
        return StdtId;
    }

    public void setStdtId(String stdtId) {
        StdtId = stdtId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getCmbCourse() {
        return cmbCourse;
    }

    public void setCmbCourse(String cmbCourse) {
        this.cmbCourse = cmbCourse;
    }

    public String getCmbBatch() {
        return cmbBatch;
    }

    public void setCmbBatch(String cmbBatch) {
        this.cmbBatch = cmbBatch;
    }

    public String getCmbGender() {
        return cmbGender;
    }

    public void setCmbGender(String cmbGender) {
        this.cmbGender = cmbGender;
    }

    public String getCmbQualification() {
        return cmbQualification;
    }

    public void setCmbQualification(String cmbQualification) {
        this.cmbQualification = cmbQualification;
    }
}
