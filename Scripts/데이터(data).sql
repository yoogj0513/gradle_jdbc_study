select user(), database();

-- title
-- desc title;
insert into title values
(1, '����'),
(2, '����'),
(3, '����'),
(4, '�븮'),
(5, '���');

-- department
-- desc department;
insert into department values
(1, '����', 8),
(2, '��ȹ', 10),
(3, '����', 9),
(4, '�ѹ�', 7);

-- employee
desc employee;

insert into employee(emp_no, emp_name, title, manager, salary, dept, pass) values
(4377, '�̼���', 1, null, 5000000, 2, password('1234567')), 
(3426, '�ڿ���', 3, 4377, 3000000, 1, password('1234567')), 
(1003, '������', 3, 4377, 3000000, 2, password('1234567')), 
(3011, '�̼���', 2, 4377, 4000000, 3, password('1234567')),
(2106, '��â��', 4, 1003, 2500000, 2, password('1234567')),
(3427, '����ö', 5, 3011, 1500000, 3, password('1234567'));

