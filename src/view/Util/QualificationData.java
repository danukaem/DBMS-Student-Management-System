package view.Util;

import java.time.LocalDate;

public class QualificationData {

    String studentId;
    String qualification;
    String institute;
    LocalDate awardDate;
    String specialization;

    public QualificationData() {
    }

    public QualificationData(String studentId, String qualification, String institute, LocalDate awardDate, String specialization) {
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
