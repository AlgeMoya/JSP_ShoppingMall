drop table Electronics;

create table Electronics(
	model_num varchar2(15) primary key, --상품번호
	model_name varchar2(20) not null,--상품이름
	price int,--가격
	description varchar2(100), --설명
	password varchar2(20) not null,--비밀번호
	writeday date  not null, --작성일
	readnum int, --조회수
	 fname varchar2(50), --파일이름
     fsize int --파일용량
);


insert into Electronics values('NT900X4D-A68','삼성샌스',1300000,'Windows 8','1111',sysdate,0,null,0); --sysdate는 날짜
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',sysdate,0,null,0);
insert into Electronics values('NT900X4D-A99S','삼성샌스',1700000,'Windows 8','1111',sysdate,0,null,0);

select * from Electronics

 commit
 -----------------------------------------------------------
 
 select * from Electronics order by writeday desc
 
 
 
 
 --하나의 게시물에 댓글 정보는 여러개 작성할수 있다.( 1 : 다)
 
 --댓글 테이블 작성
 create table replies(
   reply_num number primary key, --글번호
   reply_content varchar2(100) not null, --내용
   reply_reg_date date, -- 등록일
   parent_model_num varchar2(15) references electronics(model_num)
 );
 
 --댓글 글번호에 사용할 시퀀스 생성
 create sequence replies_no_seq;
 
 --댓글등록
 --ex)A09에 댓글 3개 등록해보자 
 insert into replies values(replies_no_seq.nextval, 'A09의 댓글 01 입니다.', sysdate,'A09');
 insert into replies values(replies_no_seq.nextval, 'A09의 댓글 02 입니다.', sysdate,'A09');
 insert into replies values(replies_no_seq.nextval, 'A09의 댓글 03 입니다.', sysdate,'A09');
 
 --ex) A05에 댓글 4개 등록해보자 
  insert into replies values(replies_no_seq.nextval, 'A05의 댓글 01 입니다.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05의 댓글 02 입니다.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05의 댓글 03 입니다.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05의 댓글 04 입니다.', sysdate,'A05');
 
 select * from replies;
 
 commit
 -- 부모글의 정보를 검색하면서 댓글정보도 함께 검색하고싶다!!  - join
 select * from electronics e join replies r 
 on e.model_num = r.parent_model_num and e.model_num='A05'; 
 
 
 --java의 입장에서 나온결과를 객체에 저장해야한다. 
 /*
   --java에 입장에서는 쉽지 않기 때문에 나중에 ORM Framework을 이용해서 해결한다
    while(rs.next()){
        나온결과를 Electroncis와 replies로 다시 분리해서...
        Electroncis는 한개만, replies로 여러개로 저장해서 하나의 객체로 만든다(has a 관계 ) .
        
    }
    
    
    class Electroncis{
    
      //1 : 다의 관계(has a 관계)
       List<Replies> replies;
    }
    
    
    class Replies{
    
        // 1 : 1 의 관계 (has a 관계)
        Electronics electronics;
    }
    
    
   ex2) 1번 댓글정보에 해당하는 부모글의 정보를 함께 검색하고 싶다!  ( 1 : 1관계)
  
*/
   select * from electronics e join replies r 
   on e.model_num = r.parent_model_num and e.model_num='A09' ;
 
  --위 join을 각각 2번의 select로 변경한다. 
 select * from electronics where model_num='A09'; --Electronics 객체 저장
 select * from replies where parent_model_num='A09'; -- Reply객체 저장
 --다시 Electronics안에 Reply를 List 형태로 저장한다.
 
 
 
 
 
 
 
 
 
 
 
 
 
 

