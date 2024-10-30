CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    furigana VARCHAR(100),
    nick_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    region VARCHAR(50),
    age INT,
    gender VARCHAR(10),
    remark TEXT,
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS students_courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    start_date DATETIME,
    end_date DATETIME
);
