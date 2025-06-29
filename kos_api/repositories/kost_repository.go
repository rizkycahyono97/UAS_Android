package repositories

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
)

type KostRepository interface {
	FindAllKostRepository(filters web.FilterKostRequest) ([]domain.Kost, error)
	FindByIDKostRepository(id uint) (domain.Kost, error)
}
