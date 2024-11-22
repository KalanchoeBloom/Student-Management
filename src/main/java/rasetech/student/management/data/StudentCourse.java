package rasetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "受講生コース情報")
public class StudentCourse {

  private String id;//受講コースID
  private String studentId;//学生に紐付けられたコースの外部キー
  private String courses;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  // Getter と　Setterの追加
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id; ;
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

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}