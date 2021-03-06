--create database movie_rental;

--\connect movie_rental

drop table if exists Clients cascade;
drop table if exists Orders cascade;
drop table if exists Types cascade;
drop table if exists Movies cascade;
drop table if exists Disks cascade;

create table Clients (
    Client_id int generated always as identity,
    Client_name text,
    Client_phone text,
    primary key(Client_id)
);

create table Orders(
    Order_id int generated always as identity,
    Order_client int not null,
    Order_issued date not null,
    Order_returned date check (Order_issued <= Order_returned),
    Order_cost float not null check (Order_cost >= 0),
    Order_is_paid boolean default false,
    primary key(Order_id),
    constraint ord_cli
        foreign key(Order_client)
            references Clients(Client_id) on delete cascade
);

create table Types(
    Type_id int generated always as identity,
    Type_name text not null,
    Type_cost int not null check(Type_cost >= 0),
    primary key(Type_id)
);

create table Movies(
    Movie_id int generated always as identity,
    Movie_name text not null,
    Movie_date int not null,
    Movie_director text not null,
    primary key(Movie_id)
);

create table Disks(
    Disk_id int generated always as identity,
    Disk_order int,
    Disk_movie int not null,
    Disk_type int not null,
    primary key(Disk_id),
    constraint disk_ord
        foreign key(Disk_order)
            references Orders(Order_id) on delete cascade,
    constraint disk_mov
        foreign key(Disk_movie)
            references Movies(Movie_id) on delete cascade,
    constraint disk_typ
        foreign key(Disk_type)
            references Types(Type_id) on delete cascade
);

