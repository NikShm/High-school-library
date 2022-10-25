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
insert into author(name)
values
       ('Hare'),
       ('Mare'),
       ('Lire');
insert into book(name, description, price, category, count)
values ('Y', 'A 1.5 mile wide swath of winds gusting to around 95 mph created **tornado-like** damage along Kentucky Highway 259 in Edmons
on County. The winds, extending 3/4 of a mile north and south of Bee Spring, destroyed or heavily damaged several small outbuildings, tore
part of the roof off of one home, uprooted and snapped the trunks of numerous trees, and snapped around a dozen power poles', 200, 'Науково-Популярна', 2);

INSERT INTO book_author(bookid, authorid)
VALUES
    (1, 2),
    (2, 1),
    (3, 3);

SELECT * FROM book Left
    JOIN book_author ba on book.id = ba.bookid
                        Left JOIN author a on ba.authorid = a.id where a.id = 1 ORDER BY book.id  limit 4 offset 0*4;

SELECT * FROM book Left JOIN book_author ba on book.id = ba.bookid Left JOIN author a on ba.authorid = a.id where a.id = 2