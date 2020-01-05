CREATE TABLE games(
    id SERIAL not NULL,
    price decimal,
    title VARCHAR(512) not NULL,
    description VARCHAR(16384),
    tag VARCHAR(256),
    PRIMARY KEY ( id )
);
