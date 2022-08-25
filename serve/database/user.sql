-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER Alex
WITH PASSWORD 'Alex';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO Alex;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO Alex;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO Alex;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO Alex;
