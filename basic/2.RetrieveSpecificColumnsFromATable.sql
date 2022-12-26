CREATE SCHEMA cd;

create table cd.facilities(
    name varchar(100) not null,
    membercost numeric not null
);
 
insert into cd.facilities(name, membercost) values('Tennis Court 1', 5);
insert into cd.facilities(name, membercost) values('Tennis Court 2', 5);
insert into cd.facilities(name, membercost) values('Badminton Court', 0);
insert into cd.facilities(name, membercost) values('Table Tennis', 0);
insert into cd.facilities(name, membercost) values('Massage Room 1', 35);
insert into cd.facilities(name, membercost) values('Massage Room 2', 35);
insert into cd.facilities(name, membercost) values('Squash Court', 3.5);
insert into cd.facilities(name, membercost) values('Snooker Table', 0);
insert into cd.facilities(name, membercost) values('Pool Table', 0);


select name, membercost from cd.facilities