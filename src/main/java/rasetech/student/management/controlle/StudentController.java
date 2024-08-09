package rasetech.student.management.controlle;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Students> students = service.searchStudentList();
    List<StudentCourses> studentCourses = service.searchstudentcourseslist();

    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Students student : students) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourses> convertStudentCourses = new ArrayList<>();
      for (StudentCourses studentCourse : studentCourses) {
        // Nullチェックを追加してから equals を呼び出す
        if (student.getStudentId() != null && student.getStudentId()
            .equals(studentCourse.getStudentsId())) {
          convertStudentCourses.add(studentCourse);
        }
      }
      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }

  @GetMapping("/student_coursesList")
  public List<StudentCourses> getStudentCoursesList() {
    return service.searchstudentcourseslist();
  }

  @GetMapping("/filterStudents")
  public List<Students> filterStudents() {
    return service.searchStudentList();
  }

  @GetMapping("/filteredCourses")
  public List<StudentCourses> getFilteredCourses() {
    return service.searchstudentcourseslist();
  }
}