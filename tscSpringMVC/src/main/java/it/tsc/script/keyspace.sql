CREATE KEYSPACE ks_tsc
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 }
  AND DURABLE_WRITES = true;