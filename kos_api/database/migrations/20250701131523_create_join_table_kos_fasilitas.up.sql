CREATE TABLE kos_fasilitas (
    kos_id BIGINT,
    fasilitas_id BIGINT,
    PRIMARY KEY (kos_id, fasilitas_id),
    FOREIGN KEY (kos_id) REFERENCES kos(id) ON DELETE CASCADE,
    FOREIGN KEY (fasilitas_id) REFERENCES fasilitas(id) ON DELETE CASCADE
);