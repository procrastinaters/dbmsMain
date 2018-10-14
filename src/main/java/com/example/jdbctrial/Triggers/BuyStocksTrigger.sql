delimiter //
create trigger BuyStocks
after insert on boughtstocks
for each row
begin

update account,boughtstocks
set balance = balance - new.totalprice
where ( new.Username=account.Username);

END //
delimiter ;



   