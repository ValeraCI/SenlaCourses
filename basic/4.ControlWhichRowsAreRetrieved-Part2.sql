CREATE SCHEMA cd;

create table cd.facilities(
    facid int not null,
    name varchar(100) not null,
    membercost numeric not null,
    guestcost numeric not null,
    monthlymaintenance numeric not null
);
 
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(0, 'Tennis Court 1', 5, 25, 200);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(1, 'Tennis Court 2', 5, 25, 200);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(2, 'Badminton Court', 0, 15.5, 50);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(3, 'Table Tennis', 0, 5, 10);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(4, 'Massage Room 1', 35, 80, 3000);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(5, 'Massage Room 2', 35, 80, 3000);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(6, 'Squash Court', 3.5, 17.5, 80);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(7, 'Snooker Table', 0, 5, 15);
insert into cd.facilities(facid, name, membercost, guestcost, monthlymaintenance) values(8, 'Pool Table', 0, 5, 15);

select facid, name, membercost, monthlymaintenance from cd.facilities where membercost < monthlymaintenance / 50 and membercost > 0;
