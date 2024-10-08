package rasetech.student.management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourses {

  private String studentsId;
  private String courses;
  private LocalDateTime start_date;
  private LocalDateTime end_date;

  public String getCourseName() {
    return courses;
  }

  public String getStudentsId() {
    return studentsId;
  }

}