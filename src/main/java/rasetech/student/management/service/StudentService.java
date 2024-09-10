package rasetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rasetech.student.management.data.StudentCourses;
import rasetech.student.management.data.Students;
import rasetech.student.management.domain.StudentDetail;
import rasetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Students> searchStudentList() {
    return repository.search();
  }

  public StudentDetail searchStudent(String studentId) {
    Students student = repository.searchStudent(studentId);
    List<StudentCourses> studentsCourses = repository.searchStudentsCourses(student.getStudentId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentsCourses);
    return studentDetail;
  }//画面に入ってきたId情報を紐づける

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    //TODO:コース情報登録も行う。
    for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
      studentCourses.setStudentsId(studentDetail.getStudent().getStudentId());

      studentCourses.setStartDate(LocalDateTime.now());
      studentCourses.setEndDate(LocalDateTime.now().plusYears(1));
      repository.registerStudentCourses(studentCourses);
      //StudentCoursesの数だけループする
      //IDと紐付ける
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
      studentCourses.setStudentsId(studentDetail.getStudent().getStudentId());
      repository.updateStudentCourses(studentCourses);
    }
  }
}
