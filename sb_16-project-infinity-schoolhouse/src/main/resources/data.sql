INSERT INTO contact_msg
    (name, mobile_num, email, subject, message, status, created_at, created_by)
VALUES
    ('John Doe', '1234567890', 'john@example.com', 'enquiry', 'Hello, I am interested in your school. Can you please provide me with more information?', 'open', CURRENT_TIMESTAMP, 'system'),
    ('Jane Doe', '0987654321', 'doe.jane@gmail.com', 'question', 'I would like to know more about the admission process. Can you please provide me with the details?', 'open', CURRENT_TIMESTAMP, 'system');

SELECT * FROM contact_msg;

---

INSERT INTO holidays
    (name, date, type, created_at, created_by)
VALUES
    ('Republic Day', '2024-01-26', 'NATIONAL', CURRENT_TIMESTAMP, 'admin'),
    ('Holi', '2024-03-25', 'FESTIVAL', CURRENT_TIMESTAMP, 'admin'),
    ('Good Friday', '2024-03-29', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Id-ul-Fitr', '2024-04-11', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Ram Navmi', '2024-04-17', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Mahavir Jayanti', '2024-04-21', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Buddha Purnima', '2024-05-23', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Id-ul-Zuha', '2024-06-17', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Muharram', '2024-07-17', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Independence Day', '2024-08-15', 'NATIONAL', CURRENT_TIMESTAMP, 'admin'),
    ('Janamashtami', '2024-08-26', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Id-e-Milad', '2024-09-16', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Gandhi Jayanti', '2024-10-02', 'NATIONAL', CURRENT_TIMESTAMP, 'admin'),
    ('Dussehra', '2024-10-12', 'FESTIVAL', CURRENT_TIMESTAMP, 'admin'),
    ('Diwali', '2024-10-31', 'FESTIVAL', CURRENT_TIMESTAMP, 'admin'),
    ('Guru Nanak Birthday', '2024-11-15', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin'),
    ('Christmas', '2024-12-25', 'RELIGIOUS', CURRENT_TIMESTAMP, 'admin');

SELECT * FROM holidays;

---

INSERT INTO roles (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN', CURDATE(), 'DBA'), ('STUDENT', CURDATE(), 'DBA');

SELECT * FROM roles;

---

INSERT INTO person (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
    VALUES ('Techno Sorcerer', 'ts_admin@ish.com', '8000000008', '$2a$12$bgFGpqVwGKfdxIt9AGklQ.8pzuLJfrUG9bnY3THNsHt7DqFBhNR3i', 1 , CURDATE(), 'DBA');

SELECT * FROM person;

---

INSERT INTO `contact_msg` (`name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES
  ('Adam', '2176436587', 'zadam@gmail.com', 'Regarding a job', 'Wanted to join as teacher', 'Open', CURDATE(), 'anonymousUser'),
  ('Zara', '3412654387', 'zarabaig@hotmail.com', 'Course Admission', 'Wanted to join a course', 'Open', CURDATE(), 'anonymousUser'),
  ('Marques', '8547643673', 'kmarques@yahoo.com', 'Course Review', 'Review of Development course', 'Open', CURDATE(), 'anonymousUser'),
  ('Shyam', '4365328776', 'gshyam@gmail.com', 'Admission Query', 'Need to talk about admission', 'Open', CURDATE(), 'anonymousUser'),
  ('John', '5465765453', 'doejohn@gmail.com', 'Holiday Query', 'Query on upcoming holidays', 'Open', CURDATE(), 'anonymousUser'),
  ('Taniya Bell', '3987463827', 'belltaniya@gmail.com', 'Child Scholarship', 'Can my child get scholarship?', 'Open', CURDATE(), 'anonymousUser'),
  ('Willie Lara', '4568764801', '476lara@gmail.com', 'Need Admission', 'My son need an admission', 'Open', CURDATE(), 'anonymousUser'),
  ('Jonathan Parsons', '4321768902', 'jonathan.parsons@gmail.com', 'Course feedback', 'Music course is good', 'Open', CURDATE(), 'anonymousUser'),
  ('Cloe Rubio', '9854438719', 'rubio987@gmail.com', 'Correct Date of Birth', 'My Child DOB needs to be corrected', 'Open', CURDATE(), 'anonymousUser'),
  ('Camilla Stein', '6545433254', 'camillas@gmail.com', 'Transport Query', 'Is Transport provided?', 'Open', CURDATE(), 'anonymousUser'),
  ('Lizeth Gross', '4678783434', 'grossliz@yahoo.com', 'Progress report', 'Please send progress report', 'Open', CURDATE(), 'anonymousUser'),
  ('Yael Howe', '1243563254', 'howeyael@gmail.com', 'Certificate Query', 'Need Certificate hard copy', 'Open', CURDATE(), 'anonymousUser'),
  ('Ian Moreno', '2312231223', 'moreno.ian@gmail.com', 'Food feedback', 'Food quality can be improved', 'Open', CURDATE(), 'anonymousUser'),
  ('Desirae Ibarra', '3445235667', 'ibarrades@gmail.com', 'Traffic Complaint', 'Traffic around school can be controlled', 'Open', CURDATE(), 'anonymousUser'),
  ('Oswaldo Jarvis', '4556121265', 'jarvissmile@hotmail.com', 'Study Tour', 'Study tour details needed', 'Open', CURDATE(), 'anonymousUser'),
  ('Miah Perkins', '2367784512', 'perkinsmiah@hotmail.com', 'Vaccination Support', 'Vaccination center in the school', 'Open', CURDATE(), 'anonymousUser'),
  ('Zion Bolton', '8990678900', 'boltzion@gmail.com', 'Course fees', 'Pls share fees of music course', 'Open', CURDATE(), 'anonymousUser'),
  ('Dominik Tanner', '4556127834', 'tannerdominik@gmail.com', 'Games schedule', 'Provide Summer games schedule', 'Open', CURDATE(), 'anonymousUser'),
  ('Alice Johnson', '1234567891', 'alice.johnson@example.com', 'Inquiry', 'I would like to know more about your programs.', 'Open', CURDATE(), 'anonymousUser'),
  ('Bob Smith', '2345678901', 'bob.smith@example.com', 'Feedback', 'Great experience with your services.', 'Open', CURDATE(), 'anonymousUser'),
  ('Charlie Brown', '3456789012', 'charlie.brown@example.com', 'Complaint', 'I have an issue with my recent order.', 'Open', CURDATE(), 'anonymousUser'),
  ('Diana Prince', '4567890123', 'diana.prince@example.com', 'Support', 'Need help with my account.', 'Open', CURDATE(), 'anonymousUser'),
  ('Eve Adams', '5678901234', 'eve.adams@example.com', 'Suggestion', 'I have a suggestion for your website.', 'Open', CURDATE(), 'anonymousUser'),
  ('Frank Castle', '6789012345', 'frank.castle@example.com', 'Query', 'Can you provide more details about your services?', 'Open', CURDATE(), 'anonymousUser'),
  ('Grace Hopper', '7890123456', 'grace.hopper@example.com', 'Request', 'I would like to request a callback.', 'Open', CURDATE(), 'anonymousUser'),
  ('Hank Pym', '8901234567', 'hank.pym@example.com', 'Issue', 'Facing issues with the payment process.', 'Open', CURDATE(), 'anonymousUser'),
  ('Ivy Green', '9012345678', 'ivy.green@example.com', 'Complaint', 'The product I received is damaged.', 'Open', CURDATE(), 'anonymousUser'),
  ('Jack White', '0123456789', 'jack.white@example.com', 'Inquiry', 'Interested in learning more about your courses.', 'Open', CURDATE(), 'anonymousUser');