CREATE TABLE ks_tsc.tb_groups(
  username text,
  role text,
  groupname text,
  PRIMARY KEY ((username,role),groupname)
);

/**
CREATE TABLE ks_tsc.tb_groups(
  groupid text,
  username text,
  role text,
  groupname text,
  PRIMARY KEY (username,role,groupname)
);
**/

DROP TABLE IF EXISTS ks_tsc.tb_groups;

SELECT * FROM ks_tsc.tb_groups;