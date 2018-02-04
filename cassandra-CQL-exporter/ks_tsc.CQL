DROP KEYSPACE IF EXISTS ks_tsc
CREATE KEYSPACE ks_tsc WITH REPLICATION = { 'class' : 'org.apache.cassandra.locator.SimpleStrategy', 'replication_factor': '1' } AND DURABLE_WRITES = true;

CREATE TABLE ks_tsc.tb_groups (
    username text,
    role text,
    groupname text,
    PRIMARY KEY ((username, role), groupname)
) WITH read_repair_chance = 0.0
   AND dclocal_read_repair_chance = 0.1
   AND gc_grace_seconds = 864000
   AND bloom_filter_fp_chance = 0.01
   AND caching = { 'keys' : 'ALL', 'rows_per_partition' : 'NONE' }
   AND comment = ''
   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold' : 32, 'min_threshold' : 4 }
   AND compression = { 'chunk_length_in_kb' : 64, 'class' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
   AND default_time_to_live = 0
   AND speculative_retry = '99PERCENTILE'
   AND min_index_interval = 128
   AND max_index_interval = 2048
   AND crc_check_chance = 1.0;

CREATE TABLE ks_tsc.tb_allarms (
    serial_uuid text,
    ab_codi text,
    data_arrivo timestamp,
    evento text,
    matricola text,
    tel text,
    user text,
    PRIMARY KEY (serial_uuid)
) WITH read_repair_chance = 0.0
   AND dclocal_read_repair_chance = 0.1
   AND gc_grace_seconds = 864000
   AND bloom_filter_fp_chance = 0.01
   AND caching = { 'keys' : 'ALL', 'rows_per_partition' : 'NONE' }
   AND comment = ''
   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold' : 32, 'min_threshold' : 4 }
   AND compression = { 'chunk_length_in_kb' : 64, 'class' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
   AND default_time_to_live = 0
   AND speculative_retry = '99PERCENTILE'
   AND min_index_interval = 128
   AND max_index_interval = 2048
   AND crc_check_chance = 1.0;

CREATE TABLE ks_tsc.tb_anagrafica (
    ab_codi text,
    centrale text,
    nominativo text,
    sesso text,
    PRIMARY KEY (ab_codi)
) WITH read_repair_chance = 0.0
   AND dclocal_read_repair_chance = 0.1
   AND gc_grace_seconds = 864000
   AND bloom_filter_fp_chance = 0.01
   AND caching = { 'keys' : 'ALL', 'rows_per_partition' : 'NONE' }
   AND comment = ''
   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold' : 32, 'min_threshold' : 4 }
   AND compression = { 'chunk_length_in_kb' : 64, 'class' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
   AND default_time_to_live = 0
   AND speculative_retry = '99PERCENTILE'
   AND min_index_interval = 128
   AND max_index_interval = 2048
   AND crc_check_chance = 1.0;

CREATE TABLE ks_tsc.tb_users (
    username text,
    role text,
    base32secret text,
    email text,
    keyid text,
    mfaenabled boolean,
    "password" text,
    PRIMARY KEY (username, role)
) WITH read_repair_chance = 0.0
   AND dclocal_read_repair_chance = 0.1
   AND gc_grace_seconds = 864000
   AND bloom_filter_fp_chance = 0.01
   AND caching = { 'keys' : 'ALL', 'rows_per_partition' : 'NONE' }
   AND comment = ''
   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold' : 32, 'min_threshold' : 4 }
   AND compression = { 'chunk_length_in_kb' : 64, 'class' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
   AND default_time_to_live = 0
   AND speculative_retry = '99PERCENTILE'
   AND min_index_interval = 128
   AND max_index_interval = 2048
   AND crc_check_chance = 1.0;

INSERT INTO ks_tsc.tb_allarms (serial_uuid,ab_codi,data_arrivo,evento,matricola,tel,user) VALUES ('2018013123:05:25.186_65f4c1d3','0000',1517439925000,'Allarme','EUROPE_ASSISTANCE',NULL,'admin');
INSERT INTO ks_tsc.tb_allarms (serial_uuid,ab_codi,data_arrivo,evento,matricola,tel,user) VALUES ('2018013123:04:10.296_3357a4d9','0000',1517439850000,'Allarme','EUROPE_ASSISTANCE',NULL,'admin');
INSERT INTO ks_tsc.tb_allarms (serial_uuid,ab_codi,data_arrivo,evento,matricola,tel,user) VALUES ('2018013123:03:32.406_e66e4ea','0000',1517439812000,'Allarme','EUROPE_ASSISTANCE',NULL,'admin');
INSERT INTO ks_tsc.tb_users (username,role,base32secret,email,keyid,mfaenabled,password) VALUES ('matteo','ROLE_ADMIN',NULL,'matteo.freddi@infamiglia.it',NULL,false,'$2a$10$hHBe6M5abq9Jokv.Qt74nO7jffGJYchdYXDLGPeALKkjVcssmp7He');
INSERT INTO ks_tsc.tb_users (username,role,base32secret,email,keyid,mfaenabled,password) VALUES ('admin','ROLE_SADMIN','DR6FTMRWTSRGV52F','admin@infamiglia.it','admin@teleassistenzainfamiglia.it',false,'$2a$10$0hfDFZ/MroZz62ttl762guvdeFnc1iXwL6fm1603Et4LzXY6qxYHO');
