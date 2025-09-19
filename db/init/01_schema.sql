CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (email)
);

CREATE INDEX idx_users_email ON users(email);

CREATE TABLE ingredients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    type VARCHAR(255),
    brand VARCHAR(255),
    measurement_unit VARCHAR(255),
    unit_size DOUBLE,
    calories_per_unit INT,
    proteins_per_unit DOUBLE,
    carbohydrates_per_unit DOUBLE,
    fats_per_unit DOUBLE,
    fibre_per_unit DOUBLE,
    shop_link VARCHAR(255),
    PRIMARY KEY (id)
);
