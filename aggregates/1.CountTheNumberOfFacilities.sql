CREATE SCHEMA cd;


create table cd.facilities(
    facid int primary key,
    name varchar(100) not null,
    membercost numeric not null,
    guestcost numeric not null
);
 
insert into cd.facilities(facid, name, membercost, guestcost) values(0, 'Tennis Court 1', 5, 25);
insert into cd.facilities(facid, name, membercost, guestcost) values(1, 'Tennis Court 2', 5, 25);
insert into cd.facilities(facid, name, membercost, guestcost) values(2, 'Badminton Court', 0, 15.5);
insert into cd.facilities(facid, name, membercost, guestcost) values(3, 'Table Tennis', 0, 5);
insert into cd.facilities(facid, name, membercost, guestcost) values(4, 'Massage Room 1', 35, 80);
insert into cd.facilities(facid, name, membercost, guestcost) values(5, 'Massage Room 2', 35, 80);
insert into cd.facilities(facid, name, membercost, guestcost) values(6, 'Squash Court', 3.5, 17.5);
insert into cd.facilities(facid, name, membercost, guestcost) values(7, 'Snooker Table', 0, 5);
insert into cd.facilities(facid, name, membercost, guestcost) values(8, 'Pool Table', 0, 5);


select count(*) from cd.facilities;
