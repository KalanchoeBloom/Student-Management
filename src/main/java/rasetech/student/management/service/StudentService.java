package rasetech.student.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rasetech.student.management.data.Student;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    //検索処理
    repository.search();

    //絞り込みをする。年齢が３０代の人のみを抽出する。
    //抽出したリストをコントローラーに渡す。(サービスだけ変更)

    return repository.search();
  }

  public List<StudentCourses> searchStudentCoursesList() {
    //絞り込み検索で「Javaコース」のコース検索のみを抽出する。
    //抽出したリストをコントローラーに返す。
    return repository.searchStudentCourses();
  }
}
