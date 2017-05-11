CREATE KEYSPACE ks_tsc
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 }
  AND DURABLE_WRITES = true;
  
ALTER KEYSPACE ks_tsc WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

ALTER KEYSPACE ks_tsc WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 };