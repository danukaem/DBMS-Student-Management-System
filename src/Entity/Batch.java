package Entity;

import java.time.LocalDate;

public class Batch extends SuperEntity{

    private String course;
    private String batchId;
    private LocalDate date;
    private String description;
    private int batchCapacity;
    private String courseID;

    public Batch() {

    }

    public Batch(String course, String batchId, LocalDate date, String description, int batchCapacity, String courseID) {
        this.course = course;
        this.batchId = batchId;
        this.date = date;
        this.description = description;
        this.batchCapacity = batchCapacity;
        this.courseID = courseID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBatchCapacity() {
        return batchCapacity;
    }

    public void setBatchCapacity(int batchCapacity) {
        this.batchCapacity = batchCapacity;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
