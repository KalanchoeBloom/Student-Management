package rasetech.student.management.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put; // 修正
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.concurrent.locks.StampedLock;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rasetech.student.management.data.Students;
import rasetech.student.management.service.StudentService;

//@Nested消去
@WebMvcTest
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc; //リクエスト処理を疑似的に行なう

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の検索が実行できて空でかえってくること() throws Exception {
    String id = "999";
    mockMvc.perform(get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生詳細の登録が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(post("/registerStudent")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                  {
                     "student": {
                     "fullName": "田中　由美",
                     "furigana": "たなか　ゆみ",
                     "nickName": "ユミ",
                     "email": "1111@gmei.com",
                     "region": "東京都",
                     "age": 19,
                     "gender": "女",
                     "remark": ""
                      },
                     "studentCourses": [
                      {
                       "courses": "javaコース"
                                 }
                               ]
                             }
                """))
        .andExpect(status().isOk());  // ステータス200を期待

// サービスメソッドが1回呼ばれているか検証
    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(put("/updateStudent") // PUTメソッドを使用
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            """
                {
                     "student": {
                       "fullName": "田中　由美",
                       "furigana": "たなか　ゆみ",
                       "nickName": "ユミ",
                       "email": "1111@gmei.com",
                       "region": "東京都",
                       "age": 19,
                       "gender": "女",
                       "remark": ""
                     },
                     "studentCoursesList": [
                       {
                         "studentId": "1",
                         "studentsId": "1",
                         "courses": "javaコース",
                         "start_date": "2024-02-25T00:00:00",
                         "end_date": "2025-04-05T00:00:00"
                       }
                     ]
                   }
                   """
        )).andExpect(status().isOk());  // ステータス200を期待

// サービスメソッドが1回呼ばれているか検証
    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の例外APIが実行できてステータスが400返ってくること() throws Exception {
    mockMvc.perform(get("/exceptioin"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("このAPIは現在利用できません。古いURLとなっています。"));
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

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Students>> violations = validator.validate(student);
    assertThat(violations.size()).isEqualTo(0);
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

