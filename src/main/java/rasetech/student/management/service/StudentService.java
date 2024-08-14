package rasetech.student.management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.data.Students;
import rasetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Students> searchStudentList() {
//    //検索処理
//    List<Students> allStudents = repository.search();
//    //絞り込みをする。年齢が３０代の人のみを抽出する。
//    List<Students> filteredStudents = allStudents.stream()
//        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
//        .collect(Collectors.toList());
//
//    //抽出したリストをコントローラーに渡す。(サービスだけ変更)
//    return filteredStudents;
    return repository.search();
  }

  public List<StudentCourses> searchstudentcourseslist() {
    //絞り込み検索で「Javaコース」のコース検索のみを抽出する。
    List<StudentCourses> allStudentCourses = repository.searchStudentCourses();

    List<StudentCourses> filteredCourses = allStudentCourses.stream()
        .filter(course -> "javaコース".equalsIgnoreCase(course.getCourseName()))
        .collect(Collectors.toList());

    //抽出したリストをコントローラーに返す。
    return filteredCourses;
  }
}
