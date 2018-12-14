DROP TABLE admin PURGE ;
CREATE TABLE admin(
 aid VARCHAR2(50),
 password VARCHAR2(32),
 CONSTRAINTS pk_aid PRIMARY KEY(aid)
) ;
--'password' 通过MD5加密后的内容= '5d41402abc4b2a76b9719d911017c592' ;
INSERT INTO admin(aid,password) VALUES('admin','5d41402abc4b2a76b9719d911017c592') ;
--'java' 通过MD5加密后的内容= '93f725a07423fe1c889f448b33d21f46' ;
INSERT INTO admin(aid,password) VALUES('pp','93f725a07423fe1c889f448b33d21f46') ;
COMMIT ;

--修改emp表增加照片及简介内容;
ALTER TABLE emp ADD(photo VARCHAR2(50) DEFAULT 'nophoto.jpg');
ALTER TABLE emp ADD(note CLOB);
