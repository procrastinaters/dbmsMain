delimiter //
create trigger InsertIntoCurrentStocks
after insert on boughtstocks
for each row
begin

insert into CurrentStocks values(new.stockid,new.quantity);

END //
delimiter ;



   