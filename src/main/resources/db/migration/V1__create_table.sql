DROP TABLE IF EXISTS exam."user";
DROP SCHEMA IF EXISTS "exam";
CREATE SCHEMA "exam";
CREATE TABLE exam."user"
(
    id           serial
                 constraint user_pk
                 primary key,
    name         varchar(255),
    username     varchar(50),
    email        varchar(50),
    street       varchar(255),
    suite        varchar(255),
    city         varchar(50),
    zipcode      varchar(50),
    lat          varchar(50),
    lng          varchar(50),
    phone        varchar(50),
    website      varchar(50),
    company_name varchar(50),
    catch_phrase  varchar(255),
    bs           varchar(50)
);