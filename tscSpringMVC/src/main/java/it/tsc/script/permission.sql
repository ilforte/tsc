CREATE ROLE cassandradbuser WITH PASSWORD = 'v7^bEWnuHaFXtXfv' 
    AND SUPERUSER = true 
    AND LOGIN = true;
    
ALTER ROLE cassandra WITH PASSWORD='v7^bEWnuHaFXtXfv'
    AND SUPERUSER=false;    
    
DROP ROLE IF EXISTS cassandradbuser; 

ALTER ROLE cassandra WITH PASSWORD='cassandra' AND SUPERUSER=false;

DROP USER IF EXISTS cassandra;