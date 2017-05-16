CREATE OR REPLACE FUNCTION ks_tsc.create_group_from_element( elements map<text, text>, element_a text, element_b text )
CALLED ON NULL INPUT
RETURNS map<text, text>
LANGUAGE java AS '
String val = (String) elements.get(element_a);
if (val == null) elements.put(element_a, element_b); 
else if (!elements.containsKey(element_a)) elements.put(element_a, element_b);
return elements; ' ;

DROP FUNCTION IF EXISTS ks_tsc.create_group_from_element;

CREATE OR REPLACE AGGREGATE ks_tsc.group_by_element(text, text) 
SFUNC create_group_from_element 
STYPE map<text, text> 
INITCOND {};

DROP AGGREGATE IF EXISTS ks_tsc.group_by_element;

SELECT group_by_element(username, role) FROM ks_tsc.tb_users;

