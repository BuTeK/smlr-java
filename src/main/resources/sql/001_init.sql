BEGIN TRANSACTION;

    DROP TABLE IF EXISTS "links" CASCADE;
    DROP SEQUENCE IF EXISTS "links_seq" CASCADE;
    CREATE SEQUENCE "links_seq" START 100000;

    CREATE TABLE "links" (
        "id" BIGINT PRIMARY KEY DEFAULT "nextval"('"links_seq"'),
        "text" TEXT NOT NULL
    );

END TRANSACTION;