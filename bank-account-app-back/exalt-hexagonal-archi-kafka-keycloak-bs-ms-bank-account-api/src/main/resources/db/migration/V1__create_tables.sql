create table if not exists bank_account_api_db.bank_accounts
(
    account_id    varchar(255) not null primary key,
    type          varchar(255) not null,
    account_state varchar(255) not null,
    balance       decimal      not null,
    created_at    varchar(255) not null,
    overdraft     double,
    interest_rate double,
    customer_id   binary(36)   not null
);