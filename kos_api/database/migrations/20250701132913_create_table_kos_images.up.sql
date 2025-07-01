CREATE TABLE kos_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kos_id BIGINT NOT NULL,
    url_image VARCHAR(255),
    FOREIGN KEY (kos_id) REFERENCES kos(id)
);