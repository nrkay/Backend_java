create or replace procedure editThisMerchant()
language plpgsql
as $$
begin 
	update merchant set open=false where name='Toko Pak Golak';
	commit;
end;$$


create or replace procedure editMerchant(merchant_name varchar, is_boolean bool)
language plpgsql
as $$
begin 
	update merchant set open=is_boolean where  name=merchant_name;
	commit;
end;$$



CREATE OR REPLACE PROCEDURE deleteUser(user_name VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Menghapus pengguna dari tabel customer_user berdasarkan username
    DELETE FROM customer_user WHERE username = user_name;
END;
$$;








