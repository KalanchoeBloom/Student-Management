package rasetech.student.management.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rasetech.student.management.data.Students;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;


@Test
  void 受講生の全件検索が行なえること(){
  List<Students>actual =sut.search();
  assertThat(actual.size()).isEqualTo(5);
}
@Test
  void 受講生の登録が行なえること(){
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

  sut.registerStudent(student);

    List<Students> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }
  //リポジトリー登録検索に更新削除
}