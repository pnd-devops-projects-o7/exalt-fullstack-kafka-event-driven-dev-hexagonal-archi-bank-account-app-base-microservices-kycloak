create table if not exists operation_api_db.transfers
(
    transfer_id varchar(255) not null primary key,
    origin_account_id varchar(255) not null,
    destination_account_id varchar(255) not null,
    transfer_amount decimal not null,
    description text not null,
    created_at timestamp not null
);