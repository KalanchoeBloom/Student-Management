package rasetech.student.management.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
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
}