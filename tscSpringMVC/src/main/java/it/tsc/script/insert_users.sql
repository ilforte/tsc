INSERT INTO ks_tsc.tb_users (username, password, email, role)
  VALUES('admin', '$2a$10$0hfDFZ/MroZz62ttl762guvdeFnc1iXwL6fm1603Et4LzXY6qxYHO', 'admin@infamiglia.it', 'ROLE_SADMIN');
  
DELETE * FROM ks_tsc.tb_users WHERE username = 'admin';