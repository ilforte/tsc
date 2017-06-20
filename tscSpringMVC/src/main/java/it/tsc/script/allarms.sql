CREATE TABLE ks_tsc.tb_allarms(
  data_arrivo timestamp,
  serial_uuid text,
  evento text,
  ab_codi text,
  matricola text,
  tel text,
  user text,
  PRIMARY KEY (serial_uuid)
);

DROP TABLE IF EXISTS ks_tsc.tb_allarms;

SELECT * FROM ks_tsc.tb_allarms;

TRUNCATE TABLE ks_tsc.tb_allarms;

CREATE MATERIALIZED VIEW ks_tsc.vw_allarms AS
       SELECT data_arrivo,serial_uuid,evento,ab_codi,matricola,tel,user FROM ks_tsc.tb_allarms
       WHERE user IS NOT NULL AND serial_uuid IS NOT NULL
       PRIMARY KEY (serial_uuid,user)
       WITH CLUSTERING ORDER BY (user desc);
       
DROP MATERIALIZED VIEW IF EXISTS ks_tsc.vw_allarms; 

select * FROM ks_tsc.vw_allarms;
       
       
       