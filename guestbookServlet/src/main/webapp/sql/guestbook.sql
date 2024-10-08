--[테이블] - Oracle
create table guestbook(
seq number primary key, -- 시퀀스 객체로부터 값을 얻어온다
name varchar2(30),
email varchar2(30),
homepage varchar2(35),
subject varchar2(500) not null, --제목
content varchar2(4000) not null, --내용
logtime date);

--시퀀스 생성
create sequence seq_guestbook nocycle nocache;

select * from guestbook;