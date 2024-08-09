package rasetech.student.management.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.data.Students;

@Getter
@Setter
public class StudentDetail {

  private Students students;
  private List<StudentCourses> studentCourses;

  public void setStudent(Students student) {
  }

  public void setStudentsCourses(List<StudentCourses> convertStudentCourses) {
  }
}
