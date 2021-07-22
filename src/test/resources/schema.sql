CREATE TABLE account (
  account_number VARCHAR(255) PRIMARY KEY,
  pin VARCHAR(10) NOT NULL,
  balance DOUBLE,
  overdraft DOUBLE
);

CREATE TABLE atm_info (
  id VARCHAR(255) PRIMARY KEY,
  amount DOUBLE
);

CREATE TABLE atm_currencies (
  id INT PRIMARY KEY,
  denomination VARCHAR(10),
  totalCount INT,
  atm_id VARCHAR(255),  foreign key (atm_id) references atm_info(id)
);