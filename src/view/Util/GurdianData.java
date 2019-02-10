package view.Util;

public class GurdianData {
String name;
String telephone;
String mobile;
String email;
String designation;
String workPlace;
String address;

    public GurdianData() {
    }

    public GurdianData(String name, String telephone, String mobile, String email, String designation, String workPlace, String address) {
        this.name = name;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.designation = designation;
        this.workPlace = workPlace;
        this.address = address;
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
