package service

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
)

type KostService interface {
	GetAllKostService(filters web.FilterKostRequest) ([]domain.Kos, error)
	GetByIDKostService(id uint) (domain.Kos, error)
}
