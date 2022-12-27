---------------------------- ������ ����----------------------------
drop sequence product_seq;
create sequence product_seq start with 1;

drop sequence cart_seq;
create sequence cart_seq start with 1;

drop sequence orders_seq;
create sequence orders_seq start with 1;

drop sequence order_detail_seq;
create sequence order_detail_seq start with 1;

drop sequence qna_seq;
create sequence qna_seq start with 1;

select * from address;
select count(*) from address;


------------------------ ���õ����� �Է� --------------------------
-- ������ �Է�
insert into worker values('admin','admin','������','010-7777-7777');
insert into worker values('scott', 'tiger', 'ȫ�浿','010-6666-6068');

-- ȸ�� �Է�
insert into member(id, pwd, name, zip_num, address1, address2, phone, email) values
('one','1111','ä����','133-110', '����� ������ ������1��', '1���� 21ȣ', '010-7777-7777','acc@abc.com');
insert into member(id, pwd, name, zip_num, address1, address2, phone, email) values
('two','2222','ä����','130-120', '����� ���ı� ���2��', '������ ����Ʈ 201�� 505ȣ', '010-1234-5678','abc@abc.com');

select*from member;

-- ��ǰ �Է�
insert into product(pseq, name, kind, price1, price2, price3, content, image)
values(product_seq.nextval, 'ũ��Ŀ���Ϻ���', '2', 40000,50000,10000,'�������� ũ��Ŀ���Ϻ����Դϴ�.', 'w2.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '�պ���', '2', 40000,50000,10000,'������ �պ����Դϴ�.', 'w-28.jpg', 'n');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '��', '1', 10000,12000,2000,'�������� ��', 'w-14.jpg', 'n');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '������', '4', 5000,5500,500,'���� �������Դϴ�.', 'w-25.jpg', 'y');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, 'ȸ����', '1', 10000,12000,2000,'�������� ��', 'w-23.jpg', 'n');
insert into product(pseq, name, kind, price1, price2, price3, content, image)
values(product_seq.nextval, '��������', '2', 12000,18000,6000,'������ ����', 'w4.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '��ũ����', '3', 5000,5500,500,'������� �����Դϴ�.', 'w-24.jpg', 'y');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '������', '3', 5000,5500,500,'���� �������Դϴ�.', 'w11.jpg', 'y');
insert into product(pseq, name, kind, price1, price2, price3, content, image)
values(product_seq.nextval, '����Ŀ��', '4', 15000,20000,5000,'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w-13.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '����', '3', 5000,5500,500,'������� �����Դϴ�.', 'w-09.jpg', 'n');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn)
values(product_seq.nextval, '����Ŀ��', '5', 15000,20000,5000,'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w-05.jpg', 'n');


-- īƮ �߰�
select * from cart;
insert into cart(cseq,id,pseq,quantity) values(cart_seq.nextval,'one',1,1); 
-- id: one ����ڰ� 1�� ��ǰ 1�� īƮ�� �߰�
insert into cart(cseq,id,pseq,quantity) values(cart_seq.nextval,'two',2,1);

-- orders�� order_detail �߰�
-- order_detail ���̺� ���ڵ� �߰��ÿ� orders ���̺��� oseq�� �� ������ Ʋ���� �ʰ� �Է��մϴ�.
insert into orders(oseq,id) values(orders_seq.nextval,'one');
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 1, 1,1);
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 1, 2,2);
-- ��� orders ���̺��� oseq���� order_detail���̺��� oseq ������ �Էµ˴ϴ�. 

insert into orders(oseq,id) values(orders_seq.nextval,'two');
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 2, 4,3);
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 2, 5,2);

insert into orders(oseq,id) values(orders_seq.nextval,'one');
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 3, 3,1);
insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, 3, 2,1);

select * from orders;
select * from order_detail;

-- qna �߰�
select * from qna;
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '��۰��� �����Դϴ�.', '���� ��ۻ��¿� ���� ����� �亯 ��Ź�մϴ�.', 'one');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, 'ȯ�Ұ���', 'ȯ������ �ȳ���Ź�����... ��ۻ� ������ ��� �Ǵ�����..', 'two');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������ ��ȯ�ϰ� �;��.', '����� �ʹ� �۽��ϴ�. ��ȯ���� �ȳ���Ź�����.', 'one');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������� �����Դϴ�.', '���� ���� �� �ֳ���?', 'two');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '�ҷ�ǰ ��ȯ ����', '��ȯ �Ǵ� ȯ�� ���� �ȳ��� �ʿ��մϴ�. ���� �ȳ� ��Ź�����.', 'one');

insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '��۰��� �����Դϴ�.', '���� ��ۻ��¿� ���� ����� �亯 ��Ź�մϴ�.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, 'ȯ�Ұ���', 'ȯ������ �ȳ���Ź�����... ��ۻ� ������ ��� �Ǵ�����..', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������ ��ȯ�ϰ� �;��.', '����� �ʹ� �۽��ϴ�. ��ȯ���� �ȳ���Ź�����.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������� �����Դϴ�.', '���� ���� �� �ֳ���?', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '�ҷ�ǰ ��ȯ ����', '��ȯ �Ǵ� ȯ�� ���� �ȳ��� �ʿ��մϴ�. ���� �ȳ� ��Ź�����.', 'scott');

insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '��۰��� �����Դϴ�.', '���� ��ۻ��¿� ���� ����� �亯 ��Ź�մϴ�.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, 'ȯ�Ұ���', 'ȯ������ �ȳ���Ź�����... ��ۻ� ������ ��� �Ǵ�����..', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������ ��ȯ�ϰ� �;��.', '����� �ʹ� �۽��ϴ�. ��ȯ���� �ȳ���Ź�����.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������� �����Դϴ�.', '���� ���� �� �ֳ���?', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '�ҷ�ǰ ��ȯ ����', '��ȯ �Ǵ� ȯ�� ���� �ȳ��� �ʿ��մϴ�. ���� �ȳ� ��Ź�����.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '��۰��� �����Դϴ�.', '���� ��ۻ��¿� ���� ����� �亯 ��Ź�մϴ�.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, 'ȯ�Ұ���', 'ȯ������ �ȳ���Ź�����... ��ۻ� ������ ��� �Ǵ�����..', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������ ��ȯ�ϰ� �;��.', '����� �ʹ� �۽��ϴ�. ��ȯ���� �ȳ���Ź�����.', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '������� �����Դϴ�.', '���� ���� �� �ֳ���?', 'scott');
insert into qna(qseq, subject, content, id)
values(qna_seq.nextval, '�ҷ�ǰ ��ȯ ����', '��ȯ �Ǵ� ȯ�� ���� �ȳ��� �ʿ��մϴ�. ���� �ȳ� ��Ź�����.', 'scott');




select * from qna;

-- �������� 2 ���õ� ���ο� �ʵ� �ֱ� 
alter table qna add pass varchar2(20);
-- secret 'N'


-- cart���� ��ǰ��ȣ�� ����� ���̵�� ��ǰ�̸��� ����� �̸��� �Բ� ��ȸ�ϴ� view�� �����մϴ�.
create or replace view cart_view
as
select c.cseq, c.id, m.name as mname, c.pseq, p.name as pname, c.quantity, p.price2, c.result,c.indate
from cart c, product p, member m
where c.pseq = p.pseq and c.id = m.id;

select * from cart_view;

-- oders �� order_detail�� join����
-- 1. �ֹ���ȣ(oseq)�� ���� �ֹ���ǰ���� ǥ��
-- 2. ��ǰ��ȣ�� ���� ��ǰ �̸��� ���� ���� ���� ǥ��
-- 3. ���̵� ���� �� �̸��� ����ּ� ���� ���� ǥ��
create or replace view order_view
as
select d.odseq, o.oseq, o.indate, o.id,
		m.name as mname, m.zip_num, m.address1, m.address2, m.phone,
		d.pseq, p.name as pname, p.price2, d.quantity, d.result
from orders o, order_detail d, member m, product p
where o.oseq=d.oseq and o.id = m.id and d.pseq=p.pseq;

select * from order_detail;
select * from order_view;

-- �Ż�ǰ view ����
create or replace view new_pro_view
as
select * from
(select rownum, pseq, name, price2, image from product where useyn='Y' order by indate desc)
where rownum <= 4;

select * from new_pro_view;

-- 4���� ����Ʈ ��ǰ view ����
create or replace view best_pro_view
as
select * from
(select rownum, pseq, name, price2, image from product where bestyn='Y' order by indate desc)
where rownum <=4;

select * from best_pro_view;

select * from product;

update product set useyn='Y';
update product set bestyn='Y';

update product set bestyn='N' where pseq IN(2,4,6,8,10);

update order_detail set result='2' where oseq=1;

update member set useyn='Y' where id='three';

commit

