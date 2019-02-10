package Entity;

import java.time.LocalDate;

public class Qualification  extends SuperEntity{
    String studentId;
    String qualification;
    String institute;
    LocalDate awardDate;
    String specialization;

    public Qualification() {
    }

    public Qualification(String studentId, String qualification, String institute, LocalDate awardDate, String specialization) {
        this.studentId = studentId;
        this.qualification = qualification;
        this.institute = institute;
        this.awardDate = awardDate;
        this.specialization = specialization;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public LocalDate getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(LocalDate awardDate) {
        this.awardDate = awardDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
