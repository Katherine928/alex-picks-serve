BEGIN TRANSACTION;

DROP TABLE IF EXISTS fights;
--DROP SEQUENCE IF EXISTS seq_user_id;

--CREATE SEQUENCE seq_user_id
--  INCREMENT BY 1
--  NO MAXVALUE
--  NO MINVALUE
--  CACHE 1;

 CREATE TABLE fights (
    id SERIAL UNIQUE,
 	fight_No varchar (8) NOT NULL,
 	player_name varchar (64) NOT NULL,
 	salary decimal(12,2) NOT NULL
 );


 INSERT INTO fights (fight_No, player_name, salary) VALUES('1', 'John Doe', 1960.00);


 COMMIT TRANSACTION;