package repositories

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
)

type KostRepository interface {
	FindAllKostRepository(filters web.FilterKostRequest) ([]domain.Kos, error)
	FindByIDKostRepository(id uint) (domain.Kos, error)
}
