ALTER TABLE login_details
    DROP CONSTRAINT email_password_account_id_fkey,
    ADD CONSTRAINT email_password_account_id_fkey
        FOREIGN KEY (account_id)
            REFERENCES accounts(id)
            ON DELETE CASCADE;