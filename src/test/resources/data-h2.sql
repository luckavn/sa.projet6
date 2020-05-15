INSERT into ACCOUNT(id, balance, currency) values(1, 500, 'euro');
INSERT into BANK_ACCOUNT(id, bic, iban, swift) values(1, 'PPFRTTSSKR', 'FR293456789876543456', '45678654');
INSERT into CREDIT_CARD(id, card_number, expiration_date, secret_code) values(1, '1234567898765432', '12/24', 123);
INSERT into USER(id, first_name, last_name, email, password, account_id, bank_account_id, credit_card_id) values(1, 'John', 'Doe', 'jd@mail.com', 'motdepasse', 1, 1, 1);
INSERT into USER(id, first_name, last_name, email, password) values(2, 'Marcus', 'Doe', 'md@mail.com', 'motdepasse2');