insert into user_details (id, birth_date, name)
values (1001, current_date(), 'nour');

insert into user_details (id, birth_date, name)
values (1002, current_date(), 'nada');

insert into user_details (id, birth_date, name)
values (1003, current_date(), 'sara');

insert into post(id, description, user_id)
values(2001, 'i want to learn spring boot', 1001);

insert into post(id, description, user_id)
values(2002, 'i want to learn Design pattern', 1001);

insert into post(id, description, user_id)
values(2003, 'i want to learn AWS', 1002);

insert into post(id, description, user_id)
values(2004, 'i want to learn nestjs', 1003);