create database users_requests;


create table roles (
  id serial primary key,
  role varchar(2000)
);
create table users (
  id serial primary key,
  name varchar (2000),
  role_id int references roles(id)
);
create table rules (
  id serial primary key,
  rule text
);
create table rule_for_role (
	id serial primary key,
	rule_id int references rules(id),
  role_id int references roles(id)
);
create table categories (
  id serial primary key,
  category varchar(2000)
);
create table states (
  id serial primary key,
  state text
);
create table items (
  id serial primary key,
  item text,
  user_id int references users(id),
  categoty_id int references categories(id),
  state_id int references states(id)
);
create table comments(
  id serial primary key,
  comments text,
  item_id int references items(id)
);
create table attaches (
  id serial primary key,
  attach text,
  item_id int references items(id)
);


insert into roles (role) values ('admin');
insert into rules (rule) values ('Do not be affraid to be sober');
insert into categories (category) values ('something looking like terrible');
insert into states (state) values ('never worked before and now again');


