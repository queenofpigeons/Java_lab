insert into Clients(Client_name, Client_phone) values
    ('Селезнёв Егор Парфеньевич', '235-67-19'),
    ('Петров Андрей Ефимович', '654-70-27'),
    ('Федосеев Олег Леонидович', '861-81-97'),
    ('Потапов Роман Тарасович', '996-24-82');

insert into Orders(Order_client, Order_issued, Order_returned, Order_cost, Order_is_paid) values
    (1, '2020-08-24', '2020-08-25', 250, default),
    (4, '2020-08-25', '2020-08-29', 700, default),
    (2, '2020-09-01', null, 250, default);

insert into Types(Type_name, Type_cost) values
    ('CD', 250),
    ('DVD', 350),
    ('Bluray', 450);

insert into Movies(Movie_name, Movie_date, Movie_director) values
    ('Drive', '2011', 'Nicolas Winding Refn'),
    ('American Psycho', '2000', 'Mary Harron'),
    ('Blade Runner 2049', '2017', 'Denis Villeneuve');

insert into Disks(Disk_order, Disk_movie, Disk_type) values
    (1, 1, 1),
    (null, 2, 2),
    (2, 1, 1),
    (2, 2, 3),
    (3, 3, 1);