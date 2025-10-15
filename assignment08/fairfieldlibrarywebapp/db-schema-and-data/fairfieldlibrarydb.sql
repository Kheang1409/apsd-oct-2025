CREATE DATABASE fairfieldlibrarydb;


-- =============================
-- Table: primary_addresses
-- =============================
DROP TABLE IF EXISTS primary_addresses CASCADE;
CREATE TABLE primary_addresses (
  addr_id SERIAL PRIMARY KEY,
  city VARCHAR(255),
  state VARCHAR(255),
  street VARCHAR(255),
  zip_code VARCHAR(255)
);

INSERT INTO primary_addresses (city, state, street, zip_code) VALUES
('Phoenix', 'AZ', '161 Wiley Boulevard', '85020'),
('New York', 'NY', 'One New York Plaza, Suite 4600', '10004-1562'),
('Raleigh', 'NC', '1200 Corporation Road', '23112-0001'),
('Manhattan', 'NY', '1230, Avenue of the Americas, Rockefeller Center', '10020-0098'),
('Westminster', 'MD', '400 Bennett Cert Drive', '21157'),
('Miami', 'FL', 'Suite 8A, The Galleria Plaza, Collins Avenue', '71552'),
('Stuttgart', 'Germany', '26 Gaensheidestrasse', '70184'),
('Sebastopol', 'CA', '1005 Gravenstein Highway North', '95472'),
('New York', 'NY', '121 Oakwood Boulevard', '10023'),
('Fairfield', 'IA', '1000 N 4th Street, Suite V12 Verrill Hall', '52556');

-- =============================
-- Table: publishers
-- =============================
DROP TABLE IF EXISTS publishers CASCADE;
CREATE TABLE publishers (
  publisher_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  primary_addr_id INTEGER,
  FOREIGN KEY (primary_addr_id) REFERENCES primary_addresses(addr_id)
);

INSERT INTO publishers (name, primary_addr_id) VALUES
('John Wiley and Sons, Inc.', 1),
('Apress, Incorporated', 2),
('McGraw-Hill Publishers, LLC', 3),
('Simon & Schuster, Inc.', 5),
('Penguin Random House', 6),
('Addison-Wesley, Incorporated', 7),
('Holtzbrinck Publishing Group, GmbH', 7),
('HarperCollins Publishers, Inc.', NULL),
('O''reilly Media, Inc.', 8),
('Pearsons, Inc.', 9),
('MIU Press', 10);

-- =============================
-- Table: authors
-- =============================
DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors (
  author_id SERIAL PRIMARY KEY,
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255)
);

-- =============================
-- Table: books
-- =============================
DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books (
  book_id SERIAL PRIMARY KEY,
  date_published DATE,
  isbn VARCHAR(255) UNIQUE NOT NULL,
  number_of_copies_in_stock INTEGER,
  currency_code VARCHAR(255),
  price_amount DOUBLE PRECISION,
  title VARCHAR(255) NOT NULL,
  publisher_id INTEGER,
  FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id)
);

-- =============================
-- Table: books_authors
-- =============================
DROP TABLE IF EXISTS books_authors CASCADE;
CREATE TABLE books_authors (
  book_id INTEGER NOT NULL,
  author_id INTEGER NOT NULL,
  PRIMARY KEY (book_id, author_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id),
  FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

-- =============================
-- Table: roles
-- =============================
DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles (
  role_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_LIBRARIAN'),
('ROLE_LIBMEMBER');

-- =============================
-- Table: users
-- =============================
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  account_non_expired BOOLEAN NOT NULL,
  account_non_locked BOOLEAN NOT NULL,
  credentials_non_expired BOOLEAN NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  enabled BOOLEAN NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255),
  password VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO users (
  account_non_expired, account_non_locked, credentials_non_expired,
  email, enabled, first_name, last_name, middle_name,
  password, username
) VALUES (
  TRUE, TRUE, TRUE,
  'ana.admin@fairfieldlibrary.com', TRUE, 'Ana', 'Smith', 'Admin',
  '$2a$10$mFK9vsvFvZCnbojzH8C7dO3t0s1/Z5htcfB/x7lrP4bNGmHk1yTui',
  'ana.admin@fairfieldlibrary.com'
);

-- =============================
-- Table: users_roles
-- =============================
DROP TABLE IF EXISTS users_roles CASCADE;
CREATE TABLE users_roles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(1, 2),
(1, 3);
