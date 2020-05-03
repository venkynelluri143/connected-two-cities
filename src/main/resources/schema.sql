DROP TABLE IF EXISTS Card;
 
CREATE TABLE Card (
  cardId INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  expirationMonth INT NOT NULL,
  expirationyear INT DEFAULT NULL
);
