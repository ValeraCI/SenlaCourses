create table email_password(
	account_id bigint primary key references account(id),
	email varchar(129) not null unique,
	password varchar(60) not null
);

