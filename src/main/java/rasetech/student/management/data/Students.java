package rasetech.student.management.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Students {

  private String studentId;
  private String fullName;
  private String furigana;
  private String nickName;
  private String email;
  private String region;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;

  public String getName() {
    return fullName;
  }

  public String getStudentId() {
    return studentId;
  }

  public int getAge() {
    return age;
  }
}

