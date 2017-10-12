INSERT INTO ks_tsc.tb_anagrafica (ab_codi, nominativo,centrale,sesso)
  VALUES('000000', 'Centrale Milano','MILANO','M');
  
INSERT INTO ks_tsc.tb_anagrafica (ab_codi, nominativo,centrale,sesso)
  VALUES('N00001', 'Centrale Milano2','MILANO','F');
  
DELETE FROM ks_tsc.tb_anagrafica WHERE ab_codi = 'N00001';