delete from DOCUMENT;

insert into DOCUMENT values (1,'first document','2019-05-05','Hello!');
insert into DOCUMENT values (2,'second document','2019-06-05','Hello!');
insert into DOCUMENT values (3,'third document','2019-05-07','Hello!');

alter sequence hibernate_sequence restart with 4;
