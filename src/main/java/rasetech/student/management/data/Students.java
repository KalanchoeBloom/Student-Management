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

  // Getter and Setter methods
  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFurigana() {
    return furigana;
  }

  public void setFurigana(String furigana) {
    this.furigana = furigana;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  public String getName() {
    return fullName;
  }


}

