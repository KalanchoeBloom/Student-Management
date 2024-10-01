package rasetech.student.management.domain;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourse;

@AllArgsConstructor
@Valid
public class StudentDetail {

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

