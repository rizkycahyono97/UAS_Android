package service

import (
	"github.com/go-playground/validator/v10"
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
	"github.com/rizkycahyono97/UAS_Android/repositories"
)

type KostServiceImpl struct {
	kostRepo repositories.KostRepository
	validate *validator.Validate
}

func NewKostService(kostRepo repositories.KostRepository) KostService {
	return &KostServiceImpl{
		kostRepo: kostRepo,
		validate: validator.New(),
	}
}

func (s *KostServiceImpl) GetAllKostService(filters web.FilterKostRequest) ([]domain.Kos, error) {
	//validasi struct
	err := s.validate.Struct(filters)
	if err != nil {
		return nil, err
	}

	//panggil service
	kost, err := s.kostRepo.FindAllKostRepository(filters)
	if err != nil {
		return nil, err
	}

	return kost, nil
}

func (s *KostServiceImpl) GetByIDKostService(id uint) (domain.Kos, error) {
	//validate.Var untuk validasi satu variable
	err := s.validate.Var(id, "required,gt=0")
	if err != nil {
		return domain.Kos{}, err
	}

	//repository
	kost, err := s.kostRepo.FindByIDKostRepository(id)
	if err != nil {
		return domain.Kos{}, err
	}

	return kost, nil
}
