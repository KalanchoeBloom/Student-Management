package rasetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rasetech.student.management.controller.converter.StudentConverter;
import rasetech.student.management.data.StudentCourse;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービス。 受講生の検索や登録・更新処理を行ないます
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です 全件検索を行なうので、条件指定はおこなわない
   *
   * @return　受講生一覧（全件）
   */
  public List<StudentDetail> searchStudentList() {
    List<Students> studentList = repository.search();
    List<StudentCourse> studentsCoursesList = repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * 受講生詳細検索です IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param studentId 　受講生ID
   * @return　受講生
   */
  public StudentDetail searchStudent(String studentId) {
    Students student = repository.searchStudent(studentId);
    List<StudentCourse> studentsCourses = repository.searchStudentsCourses(student.getStudentId());
    return new StudentDetail(student, studentsCourses);
//    return new StudentDetail(student, studentsCourses);
  }//画面に入ってきたId情報を紐づける

  /**
   * 受講生詳細の登録を行なう 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日・コース終了日を設定します。
   *
   * @param studentDetail 　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Students student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourses().forEach(studentCourses -> {
      initStudentsCourse(studentCourses, student);
      repository.registerStudentCourses(studentCourses);
    });

    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期設定を設定する
   *
   * @param studentCourses 　受講生コース情報
   * @param student        　受講生
   */
  private void initStudentsCourse(StudentCourse studentCourses, Students student) {
    studentCourses.setStudentsId(student.getStudentId());
    LocalDateTime now = LocalDateTime.now();
    studentCourses.setStartDate(now);
    studentCourses.setEndDate(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。受講生の情報と受講生コース情報をそれぞれ更新します
   *
   * @param studentDetail
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentCourse studentCourses : studentDetail.getStudentCourses()) {
      studentCourses.setStudentsId(studentDetail.getStudent().getStudentId());
      repository.updateStudentCourses(studentCourses);

    }

  }
}
