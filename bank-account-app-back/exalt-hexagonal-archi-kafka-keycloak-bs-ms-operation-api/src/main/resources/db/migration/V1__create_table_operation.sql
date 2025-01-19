create table if not exists operation_api_db.operations
(
    operation_id       varchar(255) not null primary key,
    operation_type     varchar(255) not null,
    transaction_amount decimal      not null,
    created_at         timestamp    not null,
    description        text         not null,
    account_id         varchar(255) not null
);