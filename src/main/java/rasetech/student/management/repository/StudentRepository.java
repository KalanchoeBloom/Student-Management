package rasetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import rasetech.student.management.data.Students;
import rasetech.student.management.data.StudentCourses;

/**
 * 受講生情報を扱うリポジトリ。 全体検索や単位条件での検索、コース情報の検索が行なえるクラスです。
 */
@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Students> search();

  @Select("SELECT * FROM students WHERE studentId = #{studentId}")
  Students searchStudent(String studentId);

  @Select("SELECT * FROM student_courses")
  List<StudentCourses> searchStudentsCoursesList();
//特定の学生IDに紐づくコース情報を検索するメソッド
  @Select("SELECT * FROM student_courses  WHERE studentsId = #{studentsId}")
  List<StudentCourses> searchStudentsCourses(String studentsId);
  // 受講生情報の登録
  @Insert(
      "INSERT INTO students(fullName,furigana,nickName,email,region,age,gender,remark,isDeleted)"
          + "VALUES(#{fullName},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")//Insertのみ必要
  void registerStudent(Students student);
  // コース情報の登録
  @Insert("Insert INTO student_courses(studentsId,courses,start_date,end_date)"
      + "VALUES(#{studentsId},#{courses},#{start_date},#{end_date})")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudentCourses(StudentCourses studentsCourses);

  // 受講生情報の更新
  @Update("Update students"
          + "SET fullName= #{fullName},furigana=#{furigana},nickName=#{nickName},email=#{email},region=#{region},age=#{age},gender=#{gender},remark=#{remark},isDeleted =#{isDeleted}"
          + "WHERE studentId=#{studentId}")
  void updateStudent(Students student);
  // コース情報の更新
  @Update("UPDATE student_courses "
      + "SET courses = #{courses} "
      + "WHERE studentsId =#{studentsId}")
  void updateStudentCourses(StudentCourses studentsCourses);
}

