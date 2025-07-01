package domain

type KosImages struct {
	ID       uint64 `json:"id" gorm:"primaryKey;autoIncrement;type=bigint not null;unique"`
	KosID    uint64 `json:"kos_id" gorm:"not null"`
	UrlImage string `json:"url_image" gorm:"type:varchar(255)"`
}

func (KosImages) TableName() string {
	return "kos_images"
}
