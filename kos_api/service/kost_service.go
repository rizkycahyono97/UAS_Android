package service

import (
	"github.com/rizkycahyono97/UAS_Android/model/web"
)

type KostService interface {
	GetAllKostService(filters web.FilterKostRequest) ([]web.KostResponse, error)
	GetByIDKostService(id uint) (web.KosDetailResponse, error)
}
