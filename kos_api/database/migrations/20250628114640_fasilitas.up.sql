CREATE TABLE fasilitas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kos_id BIGINT NOT NULL,
    nama_fasilitas VARCHAR(255) NOT NULL,
    FOREIGN KEY (kos_id) REFERENCES kos(id)
)