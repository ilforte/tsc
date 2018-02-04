CREATE TABLE ks_tsc.tb_anagrafica(
  PRIMARY KEY (ab_codi),
  ab_codi text,
  nominativo text,
  centrale text,
  sesso text
);

DROP TABLE IF EXISTS ks_tsc.tb_anagrafica;

SELECT * FROM ks_tsc.tb_anagrafica;

TRUNCATE TABLE ks_tsc.tb_anagrafica;
       
       
       