DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id        INT          NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName  VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL,
    account   INT          NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    id       INT         NOT NULL AUTO_INCREMENT,
    balance  FLOAT       NOT NULL,
    user     INT         NOT NULL,
    currency VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS bank_account;
CREATE TABLE bank_account
(
    id    INT         NOT NULL AUTO_INCREMENT,
    iban  VARCHAR(50) NOT NULL,
    bic   VARCHAR(25) NOT NULL,
    swift VARCHAR(12) NOT NULL,
    user  INT         NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card
(
    id             INT         NOT NULL AUTO_INCREMENT,
    cardNumber     VARCHAR(50) NOT NULL,
    expirationDate VARCHAR(5)  NOT NULL,
    secretCode     VARCHAR(3)  NOT NULL,
    user           INT         NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS app_revenue;
CREATE TABLE app_revenue
(
    id           INT   NOT NULL AUTO_INCREMENT,
    totalRevenue FLOAT NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS commission;
CREATE TABLE commission
(
    id               INT        NOT NULL AUTO_INCREMENT,
    commissionAmount FLOAT      NOT NULL,
    secretCode       VARCHAR(3) NOT NULL,
    user             INT        NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payment;
CREATE TABLE payment
(
    id                 INT         NOT NULL AUTO_INCREMENT,
    accountTransmitter INT         NOT NULL,
    accountRecipient   INT         NOT NULL,
    sum                FLOAT       NOT NULL,
    status             VARCHAR(15) NOT NULL,
    type               VARCHAR(15) NOT NULL,
    paymentDate        DATE        NOT NULL,
    paymentCommission  INT         NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS transfer;
CREATE TABLE transfer
(
    id                 INT         NOT NULL AUTO_INCREMENT,
    user INT         NOT NULL,
    account   INT         NOT NULL,
    bankAccount                INT       NOT NULL,
    sum              FLOAT NOT NULL,
    status               VARCHAR(15) NOT NULL,
    type        VARCHAR(15)        NOT NULL,
    PRIMARY KEY (id)
);








