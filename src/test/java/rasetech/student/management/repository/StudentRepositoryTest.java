package rasetech.student.management.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rasetech.student.management.data.StudentCourse;
import rasetech.student.management.data.Students;

@MybatisTest
@Transactional
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行なえること() {
    List<Students> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5); // 学生が5人いることを確認
  }


  @Test
  void 受講生の登録が行なえること() {
    Students student = new Students();
    student.setFullName("吉田智美");
    student.setAge(20);
    student.setFurigana("ヨシダトモミ");
    student.setNickName("ヨッシー");
    student.setEmail("test@example.com");
    student.setRegion("東京");
    student.setGender("女性");
    student.setRemark("大学生");
    student.setDeleted(false);

    sut.registerStudent(student);//新しい学生を登録

    List<Students> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  //リポジトリー登録検索に更新
  @Test
  void 受講生の更新が行なえること() {
    Students student = new Students();
    student.setFullName("吉田智美");
    student.setAge(20);
    student.setFurigana("ヨシダトモミ");
    student.setNickName("ヨッシー");
    student.setEmail("test@example.com");
    student.setRegion("東京");
    student.setGender("女性");
    student.setRemark("大学生");
    student.setDeleted(false);

    sut.registerStudent(student);//新しい学生を登録
    //更新処理
    student.setFullName("吉田太郎"); // 名前を変更
    student.setAge(21); // 年齢を変更
    sut.updateStudent(student); // 更新処理
    // 更新された受講生を再度取得
    Students updatedStudent = sut.searchStudent(student.getStudentId());

    // 更新された内容が反映されているか確認
    assertThat(updatedStudent).isNotNull();
    assertThat(updatedStudent.getFullName()).isEqualTo("吉田太郎");
    assertThat(updatedStudent.getAge()).isEqualTo(21);
  }

  @Test
  void 受講生の検索ができること() {
    Students student = sut.searchStudent("1"); // 存在するstudentId
    assertThat(student).isNotNull();
    assertThat(student.getStudentId()).isEqualTo("1");

    Students nonExistentStudent = sut.searchStudent("999"); // 存在しないstudentId
    assertThat(nonExistentStudent).isNull();
  }

  @Test
  void 受講生コース情報の全件検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentsCoursesList();

    // 全件検索の結果が期待通りであることを確認
    assertThat(actual).asList().isNotEmpty(); // asList() を明示
    assertThat(actual.size()).isGreaterThan(0); // 1件以上のデータが存在することを確認
  }

  @Test
  void 特定の受講生IDに紐づくコース情報が検索できること() {
    String studentId = "1"; // 存在する受講生IDを設定
    List<StudentCourse> actual = sut.searchStudentsCourses(studentId);

    // 受講生IDに紐づくコース情報が正しく取得できることを確認
    assertThat(actual).isNotNull(); // データが存在することを確認
    assertThat(actual.get(0).getStudentId()).isEqualTo(studentId); // 取得したデータの受講生IDが一致することを確認
  }

  @Test
  void 受講生コース情報のコース名が更新できること() {
    StudentCourse course = new StudentCourse();
    course.setStudentId("1");
    course.setCourses("Java入門");
    course.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
    course.setEndDate(LocalDateTime.of(2024, 6, 30, 23, 59));

    sut.registerStudentCourses(course); // まず新規登録

    // コース名を更新
    course.setCourses("Java応用");
    sut.updateStudentCourses(course);

    List<StudentCourse> actual = sut.searchStudentsCourses("1");

    // 更新後のコース情報が正しく取得できることを確認
    assertThat(actual).isNotNull();
    assertThat(actual.stream().anyMatch(c -> c.getCourses().equals("Java応用"))).isTrue();
  }

  @Test
  void 受講生コース情報が新規登録できること() {
    StudentCourse course = new StudentCourse();
    course.setStudentId("1"); // 既存の受講生ID
    course.setCourses("javaコース");
    course.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
    course.setEndDate(LocalDateTime.of(2024, 6, 30, 23, 59));

    sut.registerStudentCourses(course); // 新規登録

    List<StudentCourse> actual = sut.searchStudentsCourses("1");

    // 新規登録されたコース情報が正しく取得できることを確認
    assertThat(actual).isNotNull();
    assertThat(actual.stream().anyMatch(c -> c.getCourses().equals("javaコース"))).isTrue();
  }

}

