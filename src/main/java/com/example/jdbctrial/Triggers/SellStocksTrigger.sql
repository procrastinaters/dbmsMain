delimiter //
create trigger SellStocks
after insert on soldstocks

for each row
begin

update account,soldstocks
set Balance = Balance + new.Sell_Price
where ( new.Username=account.Username);

END //
delimiter ;



   
