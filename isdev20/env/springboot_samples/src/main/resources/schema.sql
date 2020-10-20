CREATE TABLE chamber (
    id IDENTITY,
    user CHAR NOT NULL,
    number INT NOT NULL
);
CREATE TABLE userinfo (
    user CHAR NOT NULL PRIMARY KEY,
    height DOUBLE NOT NULL
);
