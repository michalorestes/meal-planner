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

CREATE TABLE recipes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    servings INT NOT NULL,
    instructions VARCHAR(10000) NOT NULL,
    calories_per_serving INT NOT NULL,
    proteins_per_serving DOUBLE NOT NULL,
    carbohydrates_per_serving DOUBLE NOT NULL,
    fats_per_serving DOUBLE NOT NULL,
    fibre_per_serving DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredients (
   recipe_id BIGINT NOT NULL,
   ingredient_id BIGINT NOT NULL,
   FOREIGN KEY (recipe_id) REFERENCES recipes(id),
   FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
)
