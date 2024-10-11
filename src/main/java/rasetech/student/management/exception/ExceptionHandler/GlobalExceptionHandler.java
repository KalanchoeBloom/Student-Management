package rasetech.student.management.exception.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rasetech.student.management.exception.TestException;

@ControllerAdvice//コントローラー全体に対して例外処理が可能になります
public class GlobalExceptionHandler {

  // TestException 用のハンドラー
  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TestException: " + ex.getMessage());
  }

  // 他の一般的な例外もキャッチ可能
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("General Exception: " + ex.getMessage());
  }
}
