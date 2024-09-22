CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    document VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type INTEGER NOT NULL
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    value DECIMAL(19, 2) NOT NULL,
    sender_id SERIAL NOT NULL,
    receiver_id SERIAL NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES users(id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES users(id)
);
