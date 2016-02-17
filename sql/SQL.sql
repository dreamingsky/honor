CREATE TABLE user_info (
  id varchar(32) NOT NULL,
  login_name varchar(50) NOT NULL,
  user_name varchar(50) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  user_type int(2) DEFAULT NULL,
  user_email varchar(50) DEFAULT NULL,
  status int(2) DEFAULT NULL
) 