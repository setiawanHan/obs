create table itemTBL (
    ID INT NOT NULL AUTO_INCREMENT ,
    NAME VARCHAR(100) NOT NULL ,
    PRICE DECIMAL(10,2) NOT NULL ,
    PRIMARY KEY (ID)
);
create table orderTBL (
    ID INT NOT NULL AUTO_INCREMENT ,
    ORDER_ID VARCHAR(100) NOT NULL,
    ITEM_ID INT NOT NULL,
    QTY INT NOT NULL ,
    PRIMARY KEY (ID)
);
