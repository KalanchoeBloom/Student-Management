package rasetech.student.management.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rasetech.student.management.data.StudentCourse;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import java.util.List;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void setUp() {
    sut = new StudentConverter();
  }

  @Test
  public void 受講生情報と受講生コース情報のリストを渡して受講生詳細情報のリストを作成できること() {
    Students student = createStudent();
    //受講生コース情報
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setStudentsId("1");
    studentCourse.setCourses("Java");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now());

    List<Students> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourses()).isEqualTo(studentCourseList);
  }
  @Test
  void 受講生のリストと受講生のリストを渡したときに紐づかない受講生コース情報は除外されること() {
    Students student = createStudent();
    //受講生コース情報
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setStudentsId("2");
    studentCourse.setCourses("Java");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now());

    List<Students> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourses()).isEmpty(); // 紐づかない情報は除外されることを確認
  }

  private static Students createStudent () {
      Students student = new Students();
      student.setStudentId("1");
      student.setFullName("吉田智美");
      student.setAge(20);
      student.setFurigana("ヨシダトモミ");
      student.setNickName("ヨッシー");
      student.setEmail("test@example.com");
      student.setRegion("東京");
      student.setGender("女性");
      student.setRemark("大学生");
      student.setDeleted(false);
      return student;
    }
  }