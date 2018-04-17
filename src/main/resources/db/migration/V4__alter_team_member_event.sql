ALTER TABLE TEAM_MEMBER_EVENT
  ADD COLUMN TEAM_MEMBER_ID NUMERIC(8);

UPDATE TEAM_MEMBER_EVENT e
SET TEAM_MEMBER_ID = (SELECT ID
                      FROM TEAM_MEMBER TM
                      WHERE TM.OPEN_ID = e.OPEN_ID)
WHERE TEAM_MEMBER_ID IS NULL;

ALTER TABLE TEAM_MEMBER_EVENT
  DROP COLUMN OPEN_ID;



