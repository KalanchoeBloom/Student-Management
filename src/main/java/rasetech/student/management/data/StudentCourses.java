package rasetech.student.management.data;

import java.time.LocalDateTime;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
public class StudentCourses {

  private String studentId;
  private String studentsId;
  private String courses;
  private LocalDateTime start_date;
  private LocalDateTime end_date;

  // Getter と　Setterの追加
  public String getStudentId() {
    return studentId;
  }
  public void setStudentId(String studentsId) {
    this.studentId = studentsId;
  }
  public String getStudentsId() {
    return studentsId;
  }
  public void setStudentsId(String studentsId) {
    this.studentsId = studentsId;
  }
  public String getCourses() {
    return courses;
  }
  public void setCourses(String courses) {
    this.courses = courses;
    }
  public LocalDateTime getStart_date() {
    return start_date;
  }
  public void setStartDate(LocalDateTime start_date) {
    this.start_date = start_date;
  }
  public LocalDateTime getEnd_date() {
    return end_date;
  }
  public void setEndDate(LocalDateTime end_date) {
    this.end_date = end_date;
  }
}