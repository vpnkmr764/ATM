DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS atm_currencies;

DROP TABLE IF EXISTS atm_info;

CREATE TABLE account (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(255),
  pin VARCHAR(10) NOT NULL,
  balance DOUBLE,
  overdraft DOUBLE
);

CREATE TABLE atm_info (
  atm_id INT AUTO_INCREMENT PRIMARY KEY,
  atm_number VARCHAR(255),
  amount DOUBLE
);

CREATE TABLE atm_currencies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  denomination VARCHAR(10),
  count INT,
  atm_id INT,  foreign key (atm_id) references atm_info(atm_id)
);