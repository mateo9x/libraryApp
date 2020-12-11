CREATE TABLE BOOK (
                       id IDENTITY AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR (30) NOT NULL,
                       author VARCHAR(30) NOT NULL,
                       comment VARCHAR(30),
                       releasedate DATE

);

INSERT INTO BOOK(name, author, comment, releasedate) VALUES
('Star Wars','George Lucas','SCI-FI','2003-10-13'),
('Django','Adrian Holovaty','Western','2015-01-09'),
('Harry Potter','J.K. Rowling','Fantastic','2001-12-05'),
('The Witcher','A. Sapkowski','Fantastic','2005-05-23');
