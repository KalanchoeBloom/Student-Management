package rasetech.student.management.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql; // @Sql のインポート
import org.springframework.transaction.annotation.Transactional;
import rasetech.student.management.data.StudentCourse;
import rasetech.student.management.data.Students;

@MybatisTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // テストの順序を指定
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
}

