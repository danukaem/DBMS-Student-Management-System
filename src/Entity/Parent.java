package Entity;

public class Parent  extends SuperEntity{

    String studentID;
    String name;
    String telephone;
    String mobile;
    String mobile2;
    String email;
    String designation;
    String workPlace;
    String address;

    public Parent() {
    }

    public Parent(String studentID, String name, String telephone, String mobile, String mobile2, String email, String designation, String workPlace, String address) {
        this.studentID = studentID;
        this.name = name;
        this.telephone = telephone;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.email = email;
        this.designation = designation;
        this.workPlace = workPlace;
        this.address = address;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
