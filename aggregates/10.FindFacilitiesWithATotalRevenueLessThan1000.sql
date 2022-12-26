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



create table cd.bookings(
    facid int references cd.facilities(facid),
    memid int not null,
    slots int not null
);

insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(4, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 1, 2);
insert into cd.bookings(facid, memid, slots) values(8, 1, 1);
insert into cd.bookings(facid, memid, slots) values(8, 1, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(8, 2, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(1, 0, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(3, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(1, 3, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 2, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 2);
insert into cd.bookings(facid, memid, slots) values(0, 3, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(1, 1, 3);
insert into cd.bookings(facid, memid, slots) values(1, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 2, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(7, 1, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(1, 0, 3);
insert into cd.bookings(facid, memid, slots) values(1, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 6);
insert into cd.bookings(facid, memid, slots) values(2, 0, 3);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 2, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 1, 2);
insert into cd.bookings(facid, memid, slots) values(7, 0, 4);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(3, 2, 2);
insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(3, 2, 2);
insert into cd.bookings(facid, memid, slots) values(3, 1, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 4, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 4);
insert into cd.bookings(facid, memid, slots) values(5, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 4, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(8, 0, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 4, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(1, 0, 3);
insert into cd.bookings(facid, memid, slots) values(1, 0, 3);
insert into cd.bookings(facid, memid, slots) values(4, 1, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(5, 4, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 0, 2);
insert into cd.bookings(facid, memid, slots) values(8, 4, 1);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 0, 3);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(1, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 4);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(7, 4, 2);
insert into cd.bookings(facid, memid, slots) values(7, 4, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 4, 3);
insert into cd.bookings(facid, memid, slots) values(0, 3, 3);
insert into cd.bookings(facid, memid, slots) values(1, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(2, 0, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 0, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 4, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 0, 2);
insert into cd.bookings(facid, memid, slots) values(7, 1, 2);
insert into cd.bookings(facid, memid, slots) values(7, 4, 2);
insert into cd.bookings(facid, memid, slots) values(8, 0, 1);
insert into cd.bookings(facid, memid, slots) values(8, 2, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 4, 3);
insert into cd.bookings(facid, memid, slots) values(0, 3, 3);
insert into cd.bookings(facid, memid, slots) values(1, 3, 3);
insert into cd.bookings(facid, memid, slots) values(1, 3, 3);
insert into cd.bookings(facid, memid, slots) values(1, 0, 3);
insert into cd.bookings(facid, memid, slots) values(2, 1, 3);
insert into cd.bookings(facid, memid, slots) values(3, 2, 2);
insert into cd.bookings(facid, memid, slots) values(4, 3, 2);
insert into cd.bookings(facid, memid, slots) values(4, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 2);
insert into cd.bookings(facid, memid, slots) values(6, 1, 2);
insert into cd.bookings(facid, memid, slots) values(6, 0, 20000);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(7, 2, 2);
insert into cd.bookings(facid, memid, slots) values(7, 4, 2);
insert into cd.bookings(facid, memid, slots) values(7, 1, 2);
insert into cd.bookings(facid, memid, slots) values(8, 3, 1);
insert into cd.bookings(facid, memid, slots) values(8, 1, 1);
insert into cd.bookings(facid, memid, slots) values(0, 2, 3);
insert into cd.bookings(facid, memid, slots) values(0, 0, 30000);

select name, sum(slots * case
							when memid = 0 then guestcost
							else membercost end) as revenue
from cd.bookings bk join cd.facilities fc on bk.facid = fc.facid
group by name
having  sum(slots * case
						when memid = 0 then guestcost
						else membercost end) < 1000
order by revenue;