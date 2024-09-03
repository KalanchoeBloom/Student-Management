package rasetech.student.management.controlle.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Students> students,
      List<StudentCourses> studentCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();

      studentDetail.setStudent(student);//studentsテーブルから一人ずつ取得

      List<StudentCourses> convertStudentCourses = studentCourses.stream()
          .filter(studentCourse -> student.getStudentId().equals(studentCourse.getStudentsId()))
          .collect(Collectors.toList());//courseから一致した人を取得

      studentDetail.setStudentCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
