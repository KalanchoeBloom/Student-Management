package rasetech.student.management.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourses;

@Getter
@Setter
public class StudentDetail {

  private Students student;
  private List<StudentCourses> studentCourses;

  public Students getStudent() {
    return student;
  }

  public void setStudent(Students student) {
    this.student = student;
  }

  public void setStudentCourses(List<StudentCourses> convertStudentCourses) {
    this.studentCourses = convertStudentCourses;
  }
}
