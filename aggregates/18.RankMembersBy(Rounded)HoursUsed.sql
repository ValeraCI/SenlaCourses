CREATE SCHEMA cd;


create table cd.members(
    memid int primary key,
    surname varchar(200) not null,
    firstname varchar(200) not null
);

insert into cd.members(memid, surname, firstname) values(0 , 'GUEST', 'GUEST');
insert into cd.members(memid, surname, firstname) values(1 , 'Smith', 'Darren');
insert into cd.members(memid, surname, firstname) values(2 , 'Smith', 'Tracy');
insert into cd.members(memid, surname, firstname) values(3 , 'Rownam', 'Tim');
insert into cd.members(memid, surname, firstname) values(4 , 'Joplette', 'Janice');
insert into cd.members(memid, surname, firstname) values(5 , 'Butters', 'Gerald');
insert into cd.members(memid, surname, firstname) values(6 , 'Tracy', 'Burton');
insert into cd.members(memid, surname, firstname) values(7 , 'Dare', 'Nancy');
insert into cd.members(memid, surname, firstname) values(8 , 'Boothe', 'Tim');
insert into cd.members(memid, surname, firstname) values(9 , 'Stibbons', 'Ponder');
insert into cd.members(memid, surname, firstname) values(10 , 'Owen', 'Charles');


create table cd.bookings(
    memid int references cd.members(memid),
    slots int not null
);

insert into cd.bookings(memid, slots) values(0, 200000);
insert into cd.bookings(memid, slots) values(1, 100000);
insert into cd.bookings(memid, slots) values(2, 90000);
insert into cd.bookings(memid, slots) values(3, 80000);
insert into cd.bookings(memid, slots) values(4, 70000);
insert into cd.bookings(memid, slots) values(5, 70000);
insert into cd.bookings(memid, slots) values(6, 60000);
insert into cd.bookings(memid, slots) values(7, 50000);
insert into cd.bookings(memid, slots) values(8, 40000);
insert into cd.bookings(memid, slots) values(9, 30000);
insert into cd.bookings(memid, slots) values(10, 20000);


select firstname, surname, hours, rank() over(order by hours desc)
from(
	select firstname, surname, ((sum(slots)+10)/20)*10 as hours
	from cd.bookings bk join cd.members mem on bk.memid = mem.memid 
	group by firstname, surname
  ) as db1
order by rank, surname, firstname