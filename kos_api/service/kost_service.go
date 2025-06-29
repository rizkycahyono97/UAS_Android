package service

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
)

type KostService interface {
	GetAllService(filters web.FilterKostRequest) ([]domain.Kost, error)
	GetByIDService(id uint) (domain.Kost, error)
}
