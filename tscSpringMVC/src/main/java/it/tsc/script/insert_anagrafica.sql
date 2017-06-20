INSERT INTO ks_tsc.tb_users (username, password, email, role)
  VALUES('admin', '$2a$10$/DdipbqH/qKgGdACSYCccuvfDVOiC0/Hj0efMmn4t0etlECDex65i', 'admin@infamiglia.it', 'ROLE_SADMIN');
  
DELETE * FROM ks_tsc.tb_users WHERE username = 'admin';