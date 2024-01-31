INSERT INTO role (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'SUPPORT');

INSERT INTO app_user (id, name, login, password, role_id)
VALUES (1, 'John', 'login_1', 'pass_1', 1),
       (2, 'Jane', 'login_2', 'pass_2', 1),
       (3, 'David', 'login_3', 'pass_3', 2),
       (4, 'Sarah', 'login_4', 'pass_4', 2),
       (5, 'Michael', 'login_5', 'pass_5', 3),
       (6, 'Emily', 'login_6', 'pass_6', 3),
       (7, 'Daniel', 'login_7', 'pass_7', 3),
       (8, 'Olivia', 'login_8', 'pass_8', 3),
       (9, 'William', 'login_9', 'pass_9', 3);

INSERT INTO task (status, description)
VALUES ('IN WORK', 'the Internet is not working'),
       ('NEED INFO', 'The computer is not working'),
       ('DONE', 'need a new keyboard'),
       ('CREATED', 'The printer is not working'),
       ('IN WORK', 'The computer is not working');

INSERT INTO task_user (task_id, user_id)
VALUES (1, 3),
       (1, 5),

       (2, 4),
       (2, 6),

       (3, 3),
       (3, 7),

       (4, 3),
       (4, 8),

       (5, 4),
       (5, 9);