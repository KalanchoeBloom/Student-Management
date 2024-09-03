package rasetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourses;

/**
 * 受講生情報を扱うリポジトリ。 全体検索や単位条件での検索、コース情報の検索が行なえるクラスです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return 全件検索した受講生情報の一覧
   */
  @Select("SELECT * FROM students")
  List<Students> search();

  @Select("SELECT * FROM student_courses")
  List<StudentCourses> searchStudentCourses();

  @Insert("INSERT INTO students(fullName,furigana,nickName,email,region,age,gender,remark,isDeleted)"
      +"VALUES(#{fullName},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true,keyProperty = "studentId")
  void registerStudent(Students student);
}
