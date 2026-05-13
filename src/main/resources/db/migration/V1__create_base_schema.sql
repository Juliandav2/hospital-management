CREATE TABLE specialties (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR (100) NOT NULL UNIQUE,
    description VARCHAR (255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE patients (
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR (100) NOT NULL,
    last_name     VARCHAR (100) NOT NULL,
    email         VARCHAR (150) NOT NULL UNIQUE,
    phone         VARCHAR (20),
    date_of_birth DATE,
    gender        VARCHAR (20),
    address       VARCHAR (255),
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted       BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE doctors (
    id             BIGSERIAL PRIMARY KEY,
    first_name     VARCHAR (100) NOT NULL,
    last_name      VARCHAR (100) NOT NULL,
    email          VARCHAR (150) NOT NULL UNIQUE,
    license_number VARCHAR (50) NOT NULL UNIQUE,
    phone          VARCHAR (20),
    specialty_id   BIGINT REFERENCES specialties(id),
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted        BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_patients_email    ON patients(email);
CREATE INDEX idx_doctors_email     ON doctors(email);
CREATE INDEX idx_doctors_license   ON doctors(license_number);
CREATE INDEX idx_doctors_specialty ON doctors(specialty_id);