INSERT INTO GATEWAYS(SERIAL_NUMBER, IP_ADDRESS, NAME) VALUES ("1232564556", "10.192.10.10", "GATEWAY1");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("1232564556",now(),"online","Google");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("1232564556",now(),"offline","Amazon");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("1232564556",now(),"online","Apple");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("1232564556",now(),"offline","Microsoft");

INSERT INTO GATEWAYS(SERIAL_NUMBER, IP_ADDRESS, NAME) VALUES ("123256455689", "10.192.10.11", "GATEWAY2");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("123256455689",now(),"online","Amazon");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("123256455689",now(),"offline","Apple");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("123256455689",now(),"online","Netflix");
INSERT INTO DEVICES(FK_GATEWAY, CREATED_DATE, STATUS, VENDOR) VALUES ("123256455689",now(),"offline","Chattigo");