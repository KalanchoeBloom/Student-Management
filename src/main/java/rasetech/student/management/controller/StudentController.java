package rasetech.student.management.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.exception.TestException;
import rasetech.student.management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行なうREST　APIとして実行されるcontroller
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;

  }

  /**
   * 受講生詳細の一覧検索。 全件検索を行なうので、条件指定は行なわない。
   *
   * @return　受講生一覧（全件）
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() throws TestException {
    throw new TestException("現在のこのAPIは利用できません。URLは「StudentList」を利用してください。");
  }

  /**
   * 受講生詳細検索。 IDに紐づく任意の受講生の情報を取得します。
   *
   * @param StudentId 　受講生ID
   * @return　受講生
   */
  @GetMapping("/student/{StudentId}")
  public StudentDetail getStudent(
      @PathVariable @NotBlank @Pattern(regexp = "\\d+S") String StudentId) {
    return service.searchStudent(StudentId);
  }

  /**
   * 受講生の登録を行います。
   *
   * @param studentDetail　受講生詳細
   * @return 実行結果　
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います
   * キャンセルフラグの更新もここで行います。（論理削除）
   *
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */

  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました！");
  }
}