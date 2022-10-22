insert into users(name,surname, role,login,password,type)
values
    ('X','Y','STUDENT','I','1','Student');
insert into users(name,surname, role,login,password,type)
values
    ('Y','X','USER','Me','2','Student');
insert into teacher(id,cathedra, degree,rank)
values
    ('1','Y','tr','1'),
    ('3','Y','tr','1');

insert into student(id,faculty, "group",subgroup)
values
    ('2','FTCH','343','A1'),
    ('4','FTCH','343','A1'),
    ('5','FTCH','343','A1'),
    ('6','FTCH','343','A1');
insert into author(name,surname)
values
    ('Y','X');
insert into book(name,description, price,category,count)
values
    ('Y','X',200,'Науково-Популярна',2);