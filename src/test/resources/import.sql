SET @SON_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @DAUGHTER_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @ROAD_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @STREET_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @AVENUE_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @SYNONYM_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @MATCH_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @ANTONYM_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @GOOD_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);
SET @BAD_ID=(VALUES NEXT VALUE FOR WORD_SEQUENCE);

INSERT INTO word (id, word) VALUES (@SON_ID, 'son');
INSERT INTO word (id, word) VALUES (@DAUGHTER_ID, 'daughter');
INSERT INTO word (id, word) VALUES (@ROAD_ID, 'road');
INSERT INTO word (id, word) VALUES (@STREET_ID, 'street');
INSERT INTO word (id, word) VALUES (@AVENUE_ID, 'avenue');
INSERT INTO word (id, word) VALUES (@SYNONYM_ID, 'synonym');
INSERT INTO word (id, word) VALUES (@MATCH_ID, 'match');
INSERT INTO word (id, word) VALUES (@ANTONYM_ID, 'antonym');
INSERT INTO word (id, word) VALUES (@GOOD_ID, 'good');
INSERT INTO word (id, word) VALUES (@BAD_ID, 'bad');

INSERT INTO relation (id, word1_id, type, word2_id) VALUES ((VALUES NEXT VALUE FOR RELATION_SEQUENCE), @SON_ID, 'antonym', @DAUGHTER_ID);
INSERT INTO relation (id, word1_id, type, word2_id) VALUES ((VALUES NEXT VALUE FOR RELATION_SEQUENCE), @ROAD_ID, 'synonym', @STREET_ID);
INSERT INTO relation (id, word1_id, type, word2_id) VALUES ((VALUES NEXT VALUE FOR RELATION_SEQUENCE), @ROAD_ID, 'related', @AVENUE_ID);
INSERT INTO relation (id, word1_id, type, word2_id) VALUES ((VALUES NEXT VALUE FOR RELATION_SEQUENCE), @SYNONYM_ID, 'related', @MATCH_ID);
INSERT INTO relation (id, word1_id, type, word2_id) VALUES ((VALUES NEXT VALUE FOR RELATION_SEQUENCE), @ANTONYM_ID, 'antonym', @SYNONYM_ID);

