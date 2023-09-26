CREATE TABLE users(
                      id INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(45) NOT NULL UNIQUE,
                      password VARCHAR(60) NOT NULL,
                      enabled TINYINT NOT NULL DEFAULT 1,
                      PRIMARY KEY (`id`)
);

CREATE TABLE authorities(
                            id INT NOT NULL AUTO_INCREMENT,
                            user_id INT NOT NULL,
                            authority VARCHAR(45) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE INDEX `user_id_authority_unique` (`user_id` ASC, `authority` ASC),
                            CONSTRAINT `fk_authorities_users`
                                FOREIGN KEY (`user_id`)
                                    REFERENCES `users` (`id`)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE
);

INSERT INTO users(username, password, enabled)
VALUES ('ricky', '$2a$10$zmDZtdE7rGb3OpmEc1pXwePaORIcxFXgPxaPBeNJnxXnXMPYeRwVy', 1);

INSERT INTO users(username, password, enabled)
VALUES ('admin', '$2a$10$rg6qSHD9KRaRxO.75aRfh.35JK/j1qW/ZUaCHJgaPiNy.NDlkv4hG', 1);

SELECT * FROM users;

SELECT * FROM authorities;

INSERT INTO authorities(user_id, authority)
VALUES (1, 'ROLE_USER');

INSERT INTO authorities(user_id, authority)
VALUES (2, 'ROLE_USER');

INSERT INTO authorities(user_id, authority)
VALUES (2, 'ROLE_ADMIN');