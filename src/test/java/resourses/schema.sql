-- schema.sql
CREATE TABLE IF NOT EXISTS students
(
    studentId INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    furigana VARCHAR(20)  DEFAULT NULL,
    nickname VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    region VARCHAR(50) NOT NULL,
    age INT DEFAULT NULL,
    gender VARCHAR(10) NOT NULL,
    remark VARCHAR(255)  DEFAULT NULL,
    isDeleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS student_courses
(
   studentId INT AUTO_INCREMENT PRIMARY KEY,
   studentsId  varchar(255) DEFAULT NULL,
   courses VARCHAR(20) NOT NULL,
   start_date TIMESTAMP,
   end_date TIMESTAMP
);