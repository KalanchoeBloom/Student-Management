package rasetech.student.management.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourse;

@Schema(description = "受講生詳細")
@AllArgsConstructor

public class StudentDetail {
  @Valid
  private Students student;
  private List<StudentCourse> studentCourses;

  public StudentDetail() {
    this.student = new Students();
    this.studentCourses = new ArrayList<>();
  }

  public Students getStudent() {
    return student;
  }

  public void setStudent(Students student) {
    this.student = student;
  }

  public List<StudentCourse> getStudentCourses() {
    return studentCourses;
  }

  public void setStudentCourses(List<StudentCourse> studentCourses) {
    this.studentCourses = studentCourses;
  }
}

