CREATE SEQUENCE SEQ_TEAM_MEMBER increment BY 1 minvalue 1 NO MAXVALUE START WITH 1000;

ALTER TABLE TEAM_MEMBER
  ADD COLUMN ID NUMERIC(8);

UPDATE TEAM_MEMBER
SET ID = (SELECT nextval('SEQ_TEAM_MEMBER'))
WHERE ID IS NULL;

ALTER TABLE TEAM_MEMBER
  MODIFY COLUMN ID NUMERIC(8) NOT NULL;

ALTER TABLE TEAM_MEMBER
  DROP CONSTRAINT PK_TEAM_MEMBER;

ALTER TABLE TEAM_MEMBER
  ADD CONSTRAINT PK_TEAM_MEMBER PRIMARY KEY (id)


