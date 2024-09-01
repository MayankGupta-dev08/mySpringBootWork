INSERT INTO contact_msg
    (name, mobile_num, email, subject, message, status, created_at, created_by)
VALUES
    ('John Doe', '1234567890', 'john@example.com', 'Subject 1', 'Message 1', 'open', CURRENT_TIMESTAMP, 'system'),
    ('Jane Doe', '0987654321', ' jane@gamil.com', 'Subject 2', 'Message 2', 'open', CURRENT_TIMESTAMP, 'system');

SELECT * FROM contact_msg;

--

INSERT INTO holidays
    (name, date, type, created_at, created_by)
VALUES
    ('Republic Day', '2024-01-26', 'National', CURRENT_TIMESTAMP, 'admin'),
    ('Holi', '2024-03-25', 'Festival', CURRENT_TIMESTAMP, 'admin'),
    ('Good Friday', '2024-03-29', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Id-ul-Fitr', '2024-04-11', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Ram Navmi', '2024-04-17', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Mahavir Jayanti', '2024-04-21', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Buddha Purnima', '2024-05-23', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Id-ul-Zuha', '2024-06-17', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Muharram', '2024-07-17', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Independence Day', '2024-08-15', 'National', CURRENT_TIMESTAMP, 'admin'),
    ('Janamashtami', '2024-08-26', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Id-e-Milad', '2024-09-16', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Gandhi Jayanti', '2024-10-02', 'National', CURRENT_TIMESTAMP, 'admin'),
    ('Dussehra', '2024-10-12', 'Festival', CURRENT_TIMESTAMP, 'admin'),
    ('Diwali', '2024-10-31', 'Festival', CURRENT_TIMESTAMP, 'admin'),
    ('Guru Nanak Birthday', '2024-11-15', 'Religious', CURRENT_TIMESTAMP, 'admin'),
    ('Christmas', '2024-12-25', 'Religious', CURRENT_TIMESTAMP, 'admin');

SELECT * FROM holidays;