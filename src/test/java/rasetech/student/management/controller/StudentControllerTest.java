package rasetech.student.management.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import rasetech.student.management.data.Students;
import rasetech.student.management.service.StudentService;

@WebMvcTest
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが帰ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
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

    Set<ConstraintViolation<Students>> violations = validator.validate(student);
    assertThat(violations.size()).isEqualTo(0);
    // エラーメッセージを出力
    for (ConstraintViolation<Students> violation : violations) {
      System.out.println(violation.getMessage());
    }
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックに掛かること() {
    Students student = new Students();
    student.setStudentId("テストです。");
    student.setFullName("吉田智美");
    student.setAge(20);
    student.setFurigana("ヨシダトモミ");
    student.setNickName("ヨッシー");
    student.setEmail("test@example.com");
    student.setRegion("東京");
    student.setGender("女性");
    student.setRemark("大学生");

    Set<ConstraintViolation<Students>> violations = validator.validate(student);

    // エラーメッセージを出力
    for (ConstraintViolation<Students> violation : violations) {
      System.out.println(violation.getMessage());
    }
    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }
}

