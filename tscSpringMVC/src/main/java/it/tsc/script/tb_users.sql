CREATE TABLE ks_tsc.tb_users(
  username text,
  password text,
  email text,
  role text,
  PRIMARY KEY (username,role)
);

DROP TABLE IF EXISTS ks_tsc.tb_users;

SELECT * FROM ks_tsc.tb_users;