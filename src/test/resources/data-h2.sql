INSERT INTO ACCOUNT(id, balance, currency) values(1, 500, 'euro');
INSERT INTO ACCOUNT(id, balance, currency) values(2, 500, 'euro');
INSERT INTO CREDIT_CARD(id, card_number, expiration_date, cvv_number) values(3, '234567898765435678', '12/25', '123');
INSERT INTO USER(id, first_name, last_name, email, password, account_id) values(1, 'John', 'Doe', 'john.doe@gmail.com', 'password', 1);
INSERT INTO USER(id, first_name, last_name, email, password, account_id, credit_card_id) values(2, 'Marc', 'Doe', 'marc.doe@gmail.com', 'password', 2, 3);
