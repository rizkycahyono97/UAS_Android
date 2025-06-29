package domain

import "time"

type Users struct {
	ID           uint64    `json:"id" gorm:"primaryKey;autoIncrement;type=bigint not null;unique"`
	Username     string    `json:"username" gorm:"type:varchar(255);not null;unique"`
	Email        string    `json:"email" gorm:"type:varchar(255;not null;unique"`
	Password     string    `json:"password" gorm:"type:varchar(255);not null"`
	NomorTelepon string    `json:"nomor_telepon" gorm:"type:varchar(20);not null"`
	UpdatedAt    time.Time `json:"updated_at" gorm:"type:DATETIME;column:updated_at; autoUpdateTime"`
	DeletedAt    time.Time `json:"deleted_at" gorm:"column:deleted_at;type:DATETIME;index"`

	// 1:M ke table kos
	Kosts []Kos `json:"kosts" gorm:"foreignKey:UserID"`
}

func (Users) TableName() string {
	return "users"
}
