

INSERT INTO users (username, password, enabled, apikey, email) VALUES ('henk', '$2y$10$5AiMvCASciUQktAUZl/RfelglEU35n3pnVCFgmlkMybDfENSbXpXO', true, '7847493', 'test@testy.tst');
INSERT INTO authorities (username, authority) VALUES ('henk', 'ADMIN');
INSERT INTO authorities (username, authority) VALUES ('henk', 'USER');