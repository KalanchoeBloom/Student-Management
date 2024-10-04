package rasetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行ないます
   *
   * @return　受講生一覧（全件）
   */
  List<Students> search();
  /**
   * 受講生の検索を行ないます
   *
   * @param studentId　受講生ID
   * @return　受講生
   */
  Students searchStudent(String studentId);

  /**
   * 受講生のコース情報の全件検索を行ないます
   *
   * @return　受講生のコース情報（全件）
   */

  List<StudentCourse> searchStudentsCoursesList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します
   *
   * @param studentsId　受講生ID
   * @return　受講生IDに紐づく受講生コース情報
   */
  //特定の学生IDに紐づくコース情報を検索するメソッド

  List<StudentCourse> searchStudentsCourses(String studentsId);

  /**
   * 受講生を新規登録する。IDに関しては自動採番を行なう
   * @param student　受講生
   */
  // 受講生情報の登録

  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Students student);
  /**
   * 受講生コース情報を新規登録します。IDに関しては自動採番を行なう
   *
   * @param studentsCourses　受講生コース情報
   */
  // コース情報の登録

  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudentCourses(StudentCourse studentsCourses);

  /**
   * 受講生を更新します。
   *
   * @param student　受講生
   */


  void updateStudent(Students student);

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentsCourses　受講生コース情報
   */

  void updateStudentCourses(StudentCourse studentsCourses);
}

