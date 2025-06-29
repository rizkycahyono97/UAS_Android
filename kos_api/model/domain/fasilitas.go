package domain

type Fasilitas struct {
	ID            uint64 `json:"id" gorm:"primaryKey;autoIncrement;type=bigint not null;unique"`
	KosID         uint64 `json:"kos_id" gorm:"not null"`
	NamaFasilitas string `json:"nama_fasilitas" gorm:"type:varchar(255);not null"`

	// M:M with kost
	Kost []Kos `json:"kos" gorm:"many2many:fasilitas"`
}

func (Fasilitas) TableName() string {
	return "fasilitas"
}
