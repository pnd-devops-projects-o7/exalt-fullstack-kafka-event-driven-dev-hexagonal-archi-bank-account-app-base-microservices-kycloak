INSERT INTO addresses (address_id, street_num, street_name, postal_code, city, country, birth_country)
VALUES
    ('A1', 123, 'Oak Street', 94105, 'San Francisco', 'USA', 'USA'),
    ('A2', 456, 'Maple Avenue', 10001, 'New York', 'USA', 'Canada'),
    ('A3', 789, 'Pine Road', 90210, 'Beverly Hills', 'USA', 'USA'),
    ('A4', 321, 'Birch Drive', 30301, 'Atlanta', 'USA', 'Mexico'),
    ('A5', 654, 'Cedar Lane', 60601, 'Chicago', 'USA', 'USA');

INSERT INTO customers (customer_id, firstname, lastname, state, email, created_at, address_id)
VALUES
    ('C001', 'John', 'Doe', 'California', 'john.doe@example.com', '2025-01-01', 'A1'),
    ('C002', 'Jane', 'Smith', 'New York', 'jane.smith@example.com', '2025-01-02', 'A2'),
    ('C003', 'Alice', 'Johnson', 'California', 'alice.johnson@example.com', '2025-01-03', 'A3'),
    ('C004', 'Bob', 'Williams', 'Georgia', 'bob.williams@example.com', '2025-01-04', 'A4'),
    ('C005', 'Charlie', 'Brown', 'Illinois', 'charlie.brown@example.com', '2025-01-05', 'A5');

