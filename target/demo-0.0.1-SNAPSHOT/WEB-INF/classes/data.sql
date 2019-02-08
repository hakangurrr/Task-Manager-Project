-- Authorities
CREATE TABLE authorities(
  username VARCHAR (128) NOT NULL ,
  authority VARCHAR (128) NOT NULL
);

-- Created test users
INSERT INTO users(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ENABLED) VALUES ('user1', '{noop}12345', 'User1 FirstName', 'User1 LastName', true);
INSERT INTO users(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ENABLED) VALUES ('user2', '{noop}12345', 'User2 FirstName', 'User2 LastName', true);
INSERT INTO users(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ENABLED) VALUES ('user3', '{noop}12345', 'User3 FirstName', 'User3 LastName', true);

-- Inserted test users authorities
insert into authorities values ('user1', 'ROLE_USER');
insert into authorities values ('user2', 'ROLE_USER');
insert into authorities values ('user3', 'ROLE_ADMIN');

-- Inserted test todos
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (1, 'Todo One', 'user1');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (2, 'Todo Two', 'user1');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (3, 'Todo Three', 'user1');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (4, 'Todo Four', 'user2');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (5, 'Todo Five', 'user2');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (6, 'Todo Six', 'user2');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (7, 'Todo Seven', 'user1');
INSERT INTO TODO(ID,TODO_NAME,USERNAME) VALUES (8, 'Todo Six', 'user2');

-- Inserted test items
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID)
VALUES (1, 'Item One', 'Desc One', '2019-01-01', true, 1);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID)
VALUES (2, 'Item Two', 'Desc Two', '2019-02-02', true, 1);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(3, 'Item Three', 'Desc Three', '2019-03-03', true, 1);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(4, 'Item Four', 'Desc Four', '2019-04-04', true, 1);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(5, 'Item Five', 'Desc Five', '2019-05-05', true, 2);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(6, 'Item Six', 'Desc Six', '2019-06-06', true, 2);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(7, 'Item Seven', 'Desc Seven', '2019-07-07', true, 2);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(8, 'Item Eight', 'Desc Eight', '2019-08-08', true, 2);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(9, 'Item Nine', 'Desc Nine', '2019-09-09', true, 5);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(10, 'Item Ten', 'Desc Ten', '2019-10-10', true, 5);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(11, 'Item Eleven', 'Desc Eleven', '2019-11-11', true, 5);
INSERT INTO ITEMS(ID,ITEM_NAME,ITEM_DESCRIPTION,ITEM_DEADLINE,ITEM_STATUS,TODO_ID) VALUES
(12, 'Item Twelve', 'Desc Twelve', '2019-12-12', true, 5);