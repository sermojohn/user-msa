CREATE TABLE users (
  uuid   VARCHAR(36) PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);

create table log(
   time DATE,
   event VARCHAR(100)
)