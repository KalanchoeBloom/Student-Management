package rasetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
@Schema(description = "受講生コース情報")
public class StudentCourse {

  private String id;//受講コースID
  private String studentId;//学生に紐付けられたコースの外部キー
  private String courses;
  private LocalDateTime start_date;
  private LocalDateTime end_date;

  // Getter と　Setterの追加
  public String getId() {
    return id;
  }

  public void setId(String studentsId) {
    this.id = studentsId;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
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