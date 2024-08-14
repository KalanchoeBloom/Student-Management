package rasetech.student.management.controlle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rasetech.student.management.controlle.converter.StudentConverter;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Students> students = service.searchStudentList();
    List<StudentCourses> studentCourses = service.searchstudentcourseslist();

    return converter.convertStudentDetails(
        students, studentCourses);
  }

  @GetMapping("/student_coursesList")
  public List<StudentCourses> getStudentCoursesList() {
    return service.searchstudentcourseslist();
  }
//
//  @GetMapping("/filterStudents")
//  public List<Students> filterStudents() {
//    return service.searchStudentList();
//  }
//
//  @GetMapping("/filteredCourses")
//  public List<StudentCourses> getFilteredCourses() {
//    return service.searchstudentcourseslist();
//  }
}