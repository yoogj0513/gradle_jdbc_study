select user(), database();

select * from department d;
select * from title t;
select * from employee e;

-- title
select title_no, title_name from title;
select title_no, title_name from title where title_no = 2;

insert into Title values(6, "인턴");
update Title set title_name = "계약직" where title_no = 6;
delete from Title where title_no = 6;

-- department
select dept_no, dept_name, floor from department;
select dept_no, dept_name, floor from department where dept_no = 1;

insert into  department values(5, "디자인", 5);
update department set dept_name = "회계" where dept_no = 5;
delete from department where dept_no = 5;

-- employee
select emp_no, emp_name, title, manager, salary, dept, pic, passwd, hire_date, pic
	from employee;
select emp_no, emp_name, title, manager, salary, dept, passwd, hire_date, pic
	from employee
	where emp_no = 1003;

select emp_no, emp_name, title, manager, salary, dept, hire_date from employee;
select emp_no, emp_name, title, manager, salary, dept, hire_date from employee;

select emp_no, emp_name, title, manager, salary, dept, hire_date, pic from employee where emp_no= 1003;

insert employee (emp_no, emp_name, title, manager, salary, dept, passwd, hire_date) values
(1004, '이유영', 2, 1003, 2000000, 2, password('rootroot'), '2016-03-03');

delete from employee where emp_no = 1004;

-- 조민희가 로그인하려고 할 경우
select emp_no, emp_name, title, manager, salary, dept, hire_date
	from employee
	where emp_no = 1003 and passwd = password('1234567');
	
