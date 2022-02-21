-- Production
CREATE DATABASE IF NOT EXISTS `my_prod_db`;
CREATE USER 'production'@'localhost' IDENTIFIED BY 'prod';
GRANT ALL PRIVILEGES ON * . * TO 'prod'@'localhost';
ALTER USER 'prod'@'localhost' IDENTIFIED WITH mysql_native_password BY 'prod';

USE  `my_prod_db`;
DROP TABLE IF EXISTS `player_data`;
CREATE TABLE IF NOT EXISTS`player_data` (
                                            `uuid` varchar(36) NOT NULL,
                                            `kills` int DEFAULT 0,
                                            `deaths` int DEFAULT 0,
                                            `money` double DEFAULT 0.0,
                                            PRIMARY KEY (`uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

-- Test
CREATE DATABASE  IF NOT EXISTS `my_test_db`;
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON * . * TO 'test'@'localhost';
ALTER USER 'test'@'localhost' IDENTIFIED WITH mysql_native_password BY 'test';

USE  `my_test_db`;
DROP TABLE IF EXISTS `player_data`;
CREATE TABLE IF NOT EXISTS`player_data` (
                                            `uuid` varchar(36) NOT NULL,
                                            `kills` int DEFAULT 0,
                                            `deaths` int DEFAULT 0,
                                            `money` double DEFAULT 0.0,
                                            PRIMARY KEY (`uuid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;




