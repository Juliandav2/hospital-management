CREATE TABLE appointments (
    id           BIGSERIAL PRIMARY KEY,
    patient_id   BIGINT NOT NULL REFERENCES patients(id),
    doctor_id    BIGINT NOT NULL REFERENCES doctors(id),
    scheduled_at TIMESTAMP WITH TIME ZONE NOT NULL,
    status       VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    notes        TEXT,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted      BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_appointments_patient  ON appointments(patient_id);
CREATE INDEX idx_appointments_doctor   ON appointments(doctor_id);
CREATE INDEX idx_appointments_status   ON appointments(status);
CREATE INDEX idx_appointments_scheduled ON appointments(scheduled_at);