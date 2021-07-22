INSERT INTO account (account_number, pin, balance, overdraft) VALUES
  ('123456789', '1234', 800.00 , 200.00);
  
INSERT INTO account (account_number, pin, balance, overdraft) VALUES
  ('987654321', '4321', 1230.00, 150.00);

INSERT INTO atm_info VALUES
  ('ATM_1', 1500.00);
  
INSERT INTO atm_currencies  VALUES
  (1, '50', 10, 'ATM_1');
  
INSERT INTO atm_currencies  VALUES
  (2, '20', 30, 'ATM_1');
  
INSERT INTO atm_currencies  VALUES
  (3, '10', 30, 'ATM_1');

INSERT INTO atm_currencies  VALUES
  (4, '5',  20, 'ATM_1');
  
commit;