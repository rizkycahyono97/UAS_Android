package domain

import "time"

type Kost struct {
	ID                 uint64    `json:"id" gorm:"primaryKey;autoIncrement;type=bigint not null;unique"`
	UserID             uint64    `json:"user_id" gorm:"not null"`
	Nama               string    `json:"nama" gorm:"type:varchar(255);not null"`
	Deskripsi          string    `json:"deskripsi" gorm:"type:text;no null"`
	Tipe               string    `json:"tipe" gorm:"type:enum('putra', 'putri', 'campur')"`
	Alamat             string    `json:"alamat" gorm:"type:text"`
	Harga              uint64    `json:"harga" type:"bigint;not null"`
	StatusKetersediaan string    `json:"status_ketersediaan" gorm:"type:enum('tersedia', 'penuh')"`
	URlFoto            string    `json:"url_foto" gorm:"type:varchar(255)"`
	UpdatedAt          time.Time `json:"updated_at" gorm:"type:DATETIME;column:updated_at; autoUpdateTime"`
	DeletedAt          time.Time `json:"deleted_at" gorm:"column:deleted_at;type:DATETIME;index"`

	// M:M with fasilitas
	Fasilitas []Fasilitas `json:"fasilitas" gorm:"many2many:fasilitas_kost"`
}
