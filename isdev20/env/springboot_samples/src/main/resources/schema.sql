CREATE TABLE chamber (
    id IDENTITY,
    user CHAR NOT NULL,
    number INT NOT NULL
);
CREATE TABLE userinfo (
    user CHAR NOT NULL PRIMARY KEY,
    height DOUBLE NOT NULL
);
CREATE TABLE fruit (
    id IDENTITY,
    name CHAR NOT NULL,
    price INT NOT NULL
);
CREATE TABLE userdetail (
    userId CHAR NOT NULL PRIMARY KEY,
    name CHAR NOT NULL,
    jobId INT NOT NULL
);
