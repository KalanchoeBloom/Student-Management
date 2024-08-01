package rasetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import rasetech.student.management.data.Student;
import rasetech.student.management.data.StudentCourses;

/**
 * 受講生情報を扱うリポジトリ。
 * <p>
 * 全体検索や単位条件での検索、コース情報の検索が行なえるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return 全件検索した受講生情報の一覧
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM student_courses")
  List<StudentCourses> searchStudentCourses();
}
