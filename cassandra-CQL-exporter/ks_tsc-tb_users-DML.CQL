INSERT INTO ks_tsc.tb_users (username,role,base32secret,email,keyid,mfaenabled,password) VALUES ('matteo','ROLE_ADMIN',NULL,NULL,NULL,true,NULL) IF NOT EXISTS;
INSERT INTO ks_tsc.tb_users (username,role,base32secret,email,keyid,mfaenabled,password) VALUES ('matteo','ROLE_USER',NULL,NULL,NULL,true,NULL) IF NOT EXISTS;
