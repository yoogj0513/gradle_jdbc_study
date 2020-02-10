select user(), database();

select * from department d;
select * from title t;
select * from employee e;

select title_no, title_name from title;
select title_no, title_name from title where title_no = 2;

insert into Title values(6, "인턴");
update Title set title_name = "계약직" where title_no = 6;
delete from Title where title_no = 6;

select dept_no, dept_name, floor from department;
select dept_no, dept_name, floor from department where dept_no = 1;

insert into  department values(5, "디자인", 5);
update department set dept_name = "회계" where dept_no = 5;
delete from department where dept_no = 5;