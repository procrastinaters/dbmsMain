delimiter //
create trigger UpdateQuantity
after insert on soldstocks
for each row
begin

declare updated_quantity int;

set updated_quantity = (select quantity from CurrentStocks where Ticker = new.Ticker);

set updated_quantity = updated_quantity - new.quantity;

update CurrentStocks set 
quantity = quantity - new.quantity
where (new.Ticker = Ticker);

if (updated_quantity = 0) then
delete from CurrentStocks 
where (Ticker = new.Ticker);
end if;

END //
delimiter ;



   