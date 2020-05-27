CREATE TABLE user
(
    id          INT          NOT NULL AUTO_INCREMENT,
    firstName   VARCHAR(100) NOT NULL,
    lastName    VARCHAR(100) NOT NULL,
    password    VARCHAR(300) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    account     INT          NOT NULL,
    credit_card INT,
    PRIMARY KEY (id)
);

CREATE TABLE account
(
    id       INT         NOT NULL AUTO_INCREMENT,
    balance  FLOAT       NOT NULL,
    user     INT         NOT NULL,
    currency VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bank_account
(
    id    INT         NOT NULL AUTO_INCREMENT,
    iban  VARCHAR(50) NOT NULL,
    bic   VARCHAR(25) NOT NULL,
    swift VARCHAR(12) NOT NULL,
    user  INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE credit_card
(
    id             INT         NOT NULL AUTO_INCREMENT,
    cardNumber     VARCHAR(50) NOT NULL,
    expirationDate VARCHAR(5)  NOT NULL,
    cvvNumber      VARCHAR(3)  NOT NULL,
    user           INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE refill
(
    id         INT   NOT NULL AUTO_INCREMENT,
    creditCard INT   NOT NULL,
    account    INT   NOT NULL,
    sum        FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE payment
(
    id                 INT   NOT NULL AUTO_INCREMENT,
    accountTransmitter INT   NOT NULL,
    accountRecipient   INT   NOT NULL,
    sum                FLOAT NOT NULL,
    paymentDate        DATE  NOT NULL,
    commissionAmount   INT   NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE transfer
(
    id          INT   NOT NULL AUTO_INCREMENT,
    user        INT   NOT NULL,
    account     INT   NOT NULL,
    bankAccount INT   NOT NULL,
    sum         FLOAT NOT NULL,
    PRIMARY KEY (id)
);










