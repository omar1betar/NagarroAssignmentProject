-- Seeding data into Account table
INSERT INTO users(id, username, password) VALUES(1, 'user', '$2a$12$vzD5hMkQojXO2uHhT2Q1LeH4MRZNOA15BGsCCQSBJbOjNkl8xDCKa');
INSERT INTO users(id, username, password) VALUES (2, 'admin', '$2a$12$jWu8oxKALY.EuotPwQOYvu0PEy/H1hiJOW8sL5WK3pKbOCPQX/h/S');

INSERT INTO user_roles(user_id,roles) VALUES(1,"USER");
INSERT INTO user_roles(user_id,roles) VALUES(2,"ADMIN");

INSERT INTO accounts (id,account_type, account_number,user_id) VALUES (1,'Saving', '12343452654235',1);
INSERT INTO accounts (id,account_type, account_number,user_id) VALUES (2,'Checking', '76589452654235',2);

-- Seeding data into Statement table
-- Statements for the past 4 months
-- For Account 1
INSERT INTO statements (id,account_id, date_field, amount) VALUES (1,1, '2024-08-15', '1000');
INSERT INTO statements (id,account_id, date_field, amount) VALUES (2,1, '2024-07-15', '5367');
INSERT INTO statements (id,account_id, date_field, amount) VALUES (3,1, '2024-06-15', '76522');
INSERT INTO statements (id,account_id, date_field, amount) VALUES (4,1, '2024-05-15', '675234');
INSERT INTO statements (id,account_id, date_field, amount) VALUES (5,1, '2024-04-15', '561');
INSERT INTO statements (id,account_id, date_field, amount) VALUES (6,1, '2024-03-15', '1424');

