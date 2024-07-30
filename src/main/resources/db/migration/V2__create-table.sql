CREATE TABLE provincias (
    id BIGINT PRIMARY KEY NOT NULL,
    designation VARCHAR(50) NOT NULL
);

CREATE TABLE distritos (
    id BIGINT PRIMARY KEY NOT NULL,
    designation VARCHAR(60) NOT NULL,
    province_id BIGINT NOT NULL,
    FOREIGN KEY(province_id) REFERENCES provincias(id)
);