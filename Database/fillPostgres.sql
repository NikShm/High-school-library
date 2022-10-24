insert into users(name, surname, role, login, password, type)
values ('X', 'Y', 'USER', 'Q', '1', 'Teacher'),
       ('X', 'Y', 'USER', 'E', '2', 'Student'),
       ('X', 'Y', 'USER', 'R', '3', 'Teacher'),
       ('X', 'Y', 'USER', 'Y', '4', 'Student'),
       ('X', 'Y', 'USER', 'F', '5', 'Student'),
       ('X', 'Y', 'USER', 'J', '6', 'Student');


insert into teacher(id, cathedra, degree, rank)
values ('1', 'Y', 'tr', '1'),
       ('3', 'Y', 'tr', '1');

insert into student(id, faculty, "group", subgroup)
values ('2', 'FTCH', '343', 'A1'),
       ('4', 'FTCH', '343', 'A1'),
       ('5', 'FTCH', '343', 'A1'),
       ('6', 'FTCH', '343', 'A1');
insert into author(name, surname)
values ('Y', 'X');
insert into book(name, description, price, category, count)
values ('Y', 'X', 200, 'Науково-Популярна', 2);