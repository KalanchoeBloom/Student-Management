package rasetech.student.management.controlle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rasetech.student.management.controlle.converter.StudentConverter;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Students> students = service.searchStudentList();

    // 各学生のコース情報を取得する
    List<StudentCourses> studentCourses = new ArrayList<>();

    model.addAttribute("studentList", converter.convertStudentDetails(
        students, studentCourses));
    return "studentList";
  }

  //ID指定で情報をとる
  @GetMapping("/student/{StudentId}")
  public String getStudent(@PathVariable String studentId, Model model) {
    StudentDetail studentDetail = service.searchStudent(studentId);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentCourses(Arrays.asList(new StudentCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    //①新規受講生登録をとうろくする処理を実装する
    service.registerStudent(studentDetail);
    //②コース情報も一緒に登録できるように実装する。コースは単体で良い。
    System.out.println(
        studentDetail.getStudent().getName() + "さんが新規受講生として登録されました。");
    return "redirect:/studentList";
  }
  //更新

  @PostMapping("/updateStudent")
  public String Student(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "updateStudent";
    }
    //①新規受講生登録をとうろくする処理を実装する
    service.updateStudent(studentDetail);
    //②コース情報も一緒に登録できるように実装する。コースは単体で良い。
    System.out.println(
        studentDetail.getStudent().getName() + "さんが新規受講生として登録されました。");
    return "redirect:/studentList";
  }
}