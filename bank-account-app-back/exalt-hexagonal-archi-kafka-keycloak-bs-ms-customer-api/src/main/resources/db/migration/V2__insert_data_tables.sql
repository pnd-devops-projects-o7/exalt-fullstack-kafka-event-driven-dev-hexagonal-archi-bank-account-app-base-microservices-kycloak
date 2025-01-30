INSERT INTO addresses (address_id, street_num, street_name, postal_code, city, country, birth_country)
VALUES
    ('A1', 123, 'Oak Street', 94105, 'San Francisco', 'USA', 'USA'),
    ('A2', 456, 'Maple Avenue', 10001, 'New York', 'USA', 'Canada'),
    ('A3', 789, 'Pine Road', 90210, 'Beverly Hills', 'USA', 'USA'),
    ('A4', 321, 'Birch Drive', 30301, 'Atlanta', 'USA', 'Mexico'),
    ('A5', 654, 'Cedar Lane', 60601, 'Chicago', 'USA', 'USA');

INSERT INTO customers (customer_id, firstname, lastname, state, email, created_at, address_id)
VALUES
    ('7b0b0f21-6e38-418a-ae79-0e38d3539ab1', 'John', 'Doe', 'ACTIVE', 'john.doe@example.com', '2025-01-01', 'A1'),
    ('394d5015-8053-4cc7-bb61-13db4ed625be', 'Jane', 'Smith', 'ACTIVE', 'jane.smith@example.com', '2025-01-02', 'A2'),
    ('9e1ccd5b-6610-4350-b7ce-755226746e18', 'Alice', 'Johnson', 'ACTIVE', 'alice.johnson@example.com', '2025-01-03', 'A3'),
    ('8c55b5e9-f145-4fa8-bb54-c92ef6629b11', 'Bob', 'Williams', 'ACTIVE', 'bob.williams@example.com', '2025-01-04', 'A4'),
    ('e8eb7e16-265c-4a40-9a0c-18bbfb0ac73c', 'Charlie', 'Brown', 'ACTIVE', 'charlie.brown@example.com', '2025-01-05', 'A5');

