package rasetech.student.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rasetech.student.management.controller.converter.StudentConverter;
import rasetech.student.management.data.StudentCourse;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;
  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  public void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    //事前準備
    List<Students> studentsList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    Mockito.when(repository.search()).thenReturn(studentsList);
    Mockito.when(repository.searchStudentsCoursesList()).thenReturn(studentCourseList);
    //実行
    sut.searchStudentList();
    //検証
    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentsCoursesList();
    verify(converter, times(1)).convertStudentDetails(studentsList, studentCourseList);
  }

  @Test
  public void 受講生詳細検索＿リポジトリとコース情報が適切に呼ばれていること() {
    //事前準備（テストに使うダミーデータを用意）
    String studentId = "80";//IDダミー
    Students student = new Students();//生徒ダミー
    student.setStudentId(studentId);
    student.setFullName("五郎");

    List<StudentCourse> studentsCourses = new ArrayList<>();//コース情報のダミー
    Mockito.when(repository.searchStudent(studentId)).thenReturn(student);
    Mockito.when(repository.searchStudentsCourses(studentId)).thenReturn(studentsCourses);
    //実行
    sut.searchStudent(student.getStudentId());
    //検証
    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentsCourses(studentId);
//    Assertions.assertEquals(expected.getStudent().getStudentId(),
//        acctual.getStudents().getStudentId());

  }

  @Test
  public void 受講生詳細の登録を行なう_受講生情報とコース情報がリポジトリに適切に呼ばれていること() {
    //事前準備
    Students student = new Students();  // 生徒のダミーデータ
    student.setStudentId("80");
    student.setFullName("Goro");
    StudentCourse course1 = new StudentCourse();  // コース1のダミーデータ
    StudentCourse course2 = new StudentCourse();  // コース2のダミーデータ
    List<StudentCourse> studentCourses = List.of(course1, course2);  // 2つのコースを用意
    StudentDetail studentDetail = new StudentDetail(student, studentCourses);  // 生徒+コース情報
    //実行
    sut.registerStudent(studentDetail);
    //検証
    verify(repository, times(1)).registerStudent(eq(student));
    verify(repository, times(1)).registerStudentCourses(eq(course1));
    verify(repository, times(1)).registerStudentCourses(eq(course2));
  }

  @Test
  public void 受講生詳細の更新_受講生情報とコース情報がリポジトリに適切に呼び出されているかを確認する() {
    //事前準備
    Students student = new Students();  // 生徒のダミーデータ
    student.setStudentId("80");
    student.setFullName("Goro");
    StudentCourse course1 = new StudentCourse();  // コース1のダミーデータ
    StudentCourse course2 = new StudentCourse();  // コース2のダミーデータ
    List<StudentCourse> studentCourses = List.of(course1, course2);  // 2つのコースを用意
    // StudentDetail に student と courses をセット
    StudentDetail studentDetail = new StudentDetail(student, studentCourses);
    //実行
    sut.updateStudent(studentDetail);
    //検証
    // 受講生の更新が1回呼ばれたか
    verify(repository, times(1)).updateStudent(student);
    // 各コースの更新が1回呼ばれたか
    verify(repository, times(1)).updateStudentCourses(course1);
    verify(repository, times(1)).updateStudentCourses(course2);
    // 各コースの studentsId が正しく設定されているかも確認
    assertEquals("80", course1.getStudentId());
    assertEquals("80", course2.getStudentId());
  }

  @Test
  void 受講性詳細の登録＿初期化処理が行なわれること() {
    Students student = new Students();  // 生徒のダミーデータ
    student.setStudentId("80");
    student.setFullName("Goro");
    StudentCourse studentCourse = new StudentCourse();

    sut.initStudentsCourse(studentCourse, student);

    assertEquals("80", studentCourse.getId());
    assertEquals(LocalDateTime.now().getHour(), studentCourse.getStart_date().getHour());
    assertEquals(LocalDateTime.now().plusYears(1).getYear(), studentCourse.getEnd_date().getYear());
  }
}
