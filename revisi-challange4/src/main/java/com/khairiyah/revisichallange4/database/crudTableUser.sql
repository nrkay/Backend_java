
    -- Menambahkan Data user
CREATE OR REPLACE PROCEDURE addUser(
    p_username VARCHAR(255),
    p_password VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO merchant (username, password)
    VALUES (p_username, p_password);
END;
$$;


    -- edit Data user
CREATE OR REPLACE PROCEDURE editUser(
	p_user_id UUID,
    p_username VARCHAR(255),
    p_password VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE users 
    SET
        username  = p_username,
        password  = p_password
    WHERE id = p_user_id;
END;
$$;


    -- delete Data user
CREATE OR REPLACE PROCEDURE deleteUserById(user_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    update users set deleted=true  WHERE id = user_id;
END;
$$;


    -- Menghapus data merchant dari tabel Merchant berdasarkan id
CREATE OR REPLACE PROCEDURE deleteMerchantById(merchant_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    update merchant set deleted=true  WHERE id = merchant_id;
END;
$$;

    -- Menambahkan Data Merchant

CREATE OR REPLACE PROCEDURE addMerchant(
    p_name_merchant VARCHAR(255),
    p_location VARCHAR(255),
    p_open BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO merchant (name_merchant, location, open)
    VALUES (p_name_merchant, p_location, p_open);
END;
$$;


    -- Edit Data Merchant
CREATE OR REPLACE PROCEDURE editMerchant(
    p_merchant_id UUID,
    p_name_merchant VARCHAR(255),
    p_location VARCHAR(255),
    p_open BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE merchant
    SET
        name_merchant = p_name_merchant,
        location = p_location,
        open = p_open
    WHERE id = p_merchant_id;
END;
$$;




