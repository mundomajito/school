CREATE TABLE IF NOT EXISTS  teacher (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  email      VARCHAR(150) UNIQUE,
  hire_date  DATE NOT NULL,
  salary     NUMERIC(10,2),
  is_active  BOOLEAN DEFAULT TRUE
);


