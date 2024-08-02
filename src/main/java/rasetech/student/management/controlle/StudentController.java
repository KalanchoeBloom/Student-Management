package rasetech.student.management.controlle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rasetech.student.management.data.Student;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/student")
  public List<Student> getStudentList() {
    //リクエストの加工処理；入力チェックなどが入る。
    return service.searchStudentList();
  }

  @GetMapping("/student_coursesList")
  public List<StudentCourses> getStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

  @GetMapping("/filterStudents")  // 新しいエンドポイントを追加
  public List<Student> filterStudents() {
    return service.searchStudentList();
  }

  @GetMapping("/filteredCourses")  // 新しいエンドポイントを追加
  public List<StudentCourses> getFilteredCourses() {
    return service.searchStudentCoursesList();
  }
}