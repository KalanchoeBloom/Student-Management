-- data.sql
INSERT INTO students (fullName, furigana, nick_name, email, region, age, gender)
VALUES
('田中 由美', 'たなか ゆみ', 'ユミ', '1111@gmei.com', '東京都', 19, '女性'),
('加藤 拓海', 'かとう たくみ', 'タク', '2222@gmei.com', '東京都', 20, '男性'),
('伊藤 真由', 'いとう まゆ', 'マユ', '3333@gmei.com', '東京都', 21, '女性'),
('山口 桜', 'やまぐち さくら', 'サクラ', '4444@gmei.com', '東京都', 22, '女性'),
('高橋 陽介', 'たかはし ようすけ', 'ヨウ', '5555@gmei.com', '東京都', 23, '男性');

INSERT INTO students_courses (studentsId, courses, start_date, end_date)
VALUES
(1,'javaコース','2024-02-25 00:00:00', '2025-04-05 00:00:00'),
(2,'webデザインコース','2024-06-25 00:00:00', '2025-06-05 00:00:00'),
(3,'phpコース','2024-09-20 00:00:00', '2025-01-11 00:00:00'),
(4,'javaコース','2024-08-25 00:00:00', '2025-09-05 00:00:00'),
(5,'rubyコース','2024-02-14 00:00:00', '2025-03-07 00:00:00');
