-- liquibase formatted sql

-- changeset marketing_campaing:20250221-10
CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     user_name VARCHAR(255) NOT NULL,
     user_email VARCHAR(255) NOT NULL UNIQUE
);

-- changeset marketing_campaing:20250221-20
CREATE TABLE IF NOT EXISTS segments (
     id SERIAL PRIMARY KEY,
     segment_name VARCHAR(255) NOT NULL,
     segment_description TEXT
);

-- changeset marketing_campaing:20250221-30
CREATE TABLE IF NOT EXISTS  user_segment(
   fk_user_id int4 NOT NULL,
   fk_segment_id int4 NOT NULL,
   PRIMARY KEY (fk_user_id, fk_segment_id),
   FOREIGN KEY (fk_user_id) REFERENCES users(id) ON DELETE CASCADE,
   FOREIGN KEY (fk_segment_id) REFERENCES segments(id) ON DELETE CASCADE
);

-- changeset marketing_campaing:20250221-40
INSERT INTO users (user_name, user_email) VALUES
  ('Redi Ramaj', 'redi.ramaj@gmail.com'),
  ('Blerina Hodaj', 'blerina.hodaj@gmail.com'),
  ('Ejona Kodra', 'ejona.kodra@gmail.com'),
  ('Donald Beqiri', 'donald.beqiri@gmail.com');


-- changeset marketing_campaing:20250221-50
INSERT INTO segments (segment_name, segment_description) VALUES
  ('Marketing', 'Users who are part of the marketing campaign'),
  ('Finance', 'Users who are subscribed to the finance newsletter'),
  ('Logistics', 'Users responsible for logistics');

-- changeset marketing_campaing:20250221-60
INSERT INTO user_segment (fk_user_id, fk_segment_id) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 3),
  (3, 2),
  (4, 3);