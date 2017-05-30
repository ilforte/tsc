CREATE TABLE ks_tsc.tb_allarms(
  data_arrivo timestamp,
  serial_uuid text,
  evento text,
  ab_codi text,
  matricola text,
  tel text,
  user text,
  PRIMARY KEY ((serial_uuid),data_arrivo)
)
WITH CLUSTERING ORDER BY (data_arrivo DESC);

DROP TABLE IF EXISTS ks_tsc.tb_allarms;

SELECT * FROM ks_tsc.tb_allarms;