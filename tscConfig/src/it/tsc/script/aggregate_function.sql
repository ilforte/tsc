CREATE OR REPLACE FUNCTION ks_tsc.create_group_from_element( elements set<text>, element_a text, element_b text )
CALLED ON NULL INPUT
RETURNS set<text>
LANGUAGE java AS '
elements.add(element_a);
return elements; ';

DROP FUNCTION IF EXISTS ks_tsc.create_group_from_element;

CREATE OR REPLACE AGGREGATE ks_tsc.group_by_element(text, text) 
SFUNC create_group_from_element 
STYPE set<text>
INITCOND {};

DROP AGGREGATE IF EXISTS ks_tsc.group_by_element;

SELECT group_by_element(username, role) FROM ks_tsc.tb_users;

