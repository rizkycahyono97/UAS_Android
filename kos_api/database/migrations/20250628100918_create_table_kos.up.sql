CREATE TABLE kos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    nama VARCHAR(255) NOT NULL,
    deskripsi TEXT,
    tipe ENUM('putra', 'putri', 'campur') DEFAULT 'putra',
    alamat TEXT,
    harga BIGINT NOT NULL,
    status_ketersedian ENUM('tersedia', 'penuh') DEFAULT 'tersedia',
    url_foto VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);