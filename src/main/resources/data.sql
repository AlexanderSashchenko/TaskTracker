insert into task_tracker.statuses(status_id, status_name) values
(1, 'View');

insert into task_tracker.statuses(status_id, status_name) values
(2, 'In Progress');

insert into task_tracker.statuses(status_id, status_name) values
(3, 'Done');

insert into task_tracker.users (user_id, first_name, last_name, email, password, username) values
(1, 'firstname', 'lastname', 'email@test.com', 'pass', 'username');

insert into task_tracker.users (user_id, first_name, last_name, email, password, username) values
(2, 'firstname2', 'lastname2', 'email2@test.com', 'pass2', 'username2');

insert into task_tracker.users (user_id, first_name, last_name, email, password, username) values
(3, 'firstname3', 'lastname3', 'email3@test.com', 'pass3', 'username3');

insert into task_tracker.tasks(task_id, description, title, assignee_user_id, author_user_id, status_status_id)
values(1, 'description1', 'title1', 1, 3, 1);

insert into task_tracker.tasks(task_id, description, title, assignee_user_id, author_user_id, status_status_id)
values(2, 'description2', 'title2', 3, 1, 1);

insert into task_tracker.tasks(task_id, description, title, assignee_user_id, author_user_id, status_status_id)
values(3, 'description3', 'title3', 2, 2, 2);
