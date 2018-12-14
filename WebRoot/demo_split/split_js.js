DROP DATABASE csdpsystem;

CREATE DATABASE csdpsystem
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

USE csdpsystem;

CREATE TABLE csdpsystem.student
(
   studentID            VARCHAR(8) NOT NULL,
   studentName          VARCHAR(12),
   studentPassword      VARCHAR(32),
   sStatus              INT ,
   sIdentity            VARCHAR(6)  DEFAULT '学生',
   sLastTime            DATETIME,
   CONSTRAINT pk_studentID  PRIMARY KEY (studentID),
);

COMMIT ;