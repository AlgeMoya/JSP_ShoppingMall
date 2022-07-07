drop table Electronics;

create table Electronics(
	model_num varchar2(15) primary key, --��ǰ��ȣ
	model_name varchar2(20) not null,--��ǰ�̸�
	price int,--����
	description varchar2(100), --����
	password varchar2(20) not null,--��й�ȣ
	writeday date  not null, --�ۼ���
	readnum int, --��ȸ��
	 fname varchar2(50), --�����̸�
     fsize int --���Ͽ뷮
);


insert into Electronics values('NT900X4D-A68','�Ｚ����',1300000,'Windows 8','1111',sysdate,0,null,0); --sysdate�� ��¥
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',sysdate,0,null,0);
insert into Electronics values('NT900X4D-A99S','�Ｚ����',1700000,'Windows 8','1111',sysdate,0,null,0);

select * from Electronics

 commit
 -----------------------------------------------------------
 
 select * from Electronics order by writeday desc
 
 
 
 
 --�ϳ��� �Խù��� ��� ������ ������ �ۼ��Ҽ� �ִ�.( 1 : ��)
 
 --��� ���̺� �ۼ�
 create table replies(
   reply_num number primary key, --�۹�ȣ
   reply_content varchar2(100) not null, --����
   reply_reg_date date, -- �����
   parent_model_num varchar2(15) references electronics(model_num)
 );
 
 --��� �۹�ȣ�� ����� ������ ����
 create sequence replies_no_seq;
 
 --��۵��
 --ex)A09�� ��� 3�� ����غ��� 
 insert into replies values(replies_no_seq.nextval, 'A09�� ��� 01 �Դϴ�.', sysdate,'A09');
 insert into replies values(replies_no_seq.nextval, 'A09�� ��� 02 �Դϴ�.', sysdate,'A09');
 insert into replies values(replies_no_seq.nextval, 'A09�� ��� 03 �Դϴ�.', sysdate,'A09');
 
 --ex) A05�� ��� 4�� ����غ��� 
  insert into replies values(replies_no_seq.nextval, 'A05�� ��� 01 �Դϴ�.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05�� ��� 02 �Դϴ�.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05�� ��� 03 �Դϴ�.', sysdate,'A05');
  insert into replies values(replies_no_seq.nextval, 'A05�� ��� 04 �Դϴ�.', sysdate,'A05');
 
 select * from replies;
 
 commit
 -- �θ���� ������ �˻��ϸ鼭 ��������� �Բ� �˻��ϰ�ʹ�!!  - join
 select * from electronics e join replies r 
 on e.model_num = r.parent_model_num and e.model_num='A05'; 
 
 
 --java�� ���忡�� ���°���� ��ü�� �����ؾ��Ѵ�. 
 /*
   --java�� ���忡���� ���� �ʱ� ������ ���߿� ORM Framework�� �̿��ؼ� �ذ��Ѵ�
    while(rs.next()){
        ���°���� Electroncis�� replies�� �ٽ� �и��ؼ�...
        Electroncis�� �Ѱ���, replies�� �������� �����ؼ� �ϳ��� ��ü�� �����(has a ���� ) .
        
    }
    
    
    class Electroncis{
    
      //1 : ���� ����(has a ����)
       List<Replies> replies;
    }
    
    
    class Replies{
    
        // 1 : 1 �� ���� (has a ����)
        Electronics electronics;
    }
    
    
   ex2) 1�� ��������� �ش��ϴ� �θ���� ������ �Բ� �˻��ϰ� �ʹ�!  ( 1 : 1����)
  
*/
   select * from electronics e join replies r 
   on e.model_num = r.parent_model_num and e.model_num='A09' ;
 
  --�� join�� ���� 2���� select�� �����Ѵ�. 
 select * from electronics where model_num='A09'; --Electronics ��ü ����
 select * from replies where parent_model_num='A09'; -- Reply��ü ����
 --�ٽ� Electronics�ȿ� Reply�� List ���·� �����Ѵ�.
 
 
 
 
 
 
 
 
 
 
 
 
 
 

