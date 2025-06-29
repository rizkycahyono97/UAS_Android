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

func (s *KostServiceImpl) GetAllService(filters web.FilterKostRequest) ([]domain.Kost, error) {
	//validasi struct
	err := s.validate.Struct(filters)
	if err != nil {
		return nil, err
	}

	//panggil service
	kost, err := s.kostRepo.FindAllRepository(filters)
	if err != nil {
		return nil, err
	}

	return kost, nil
}

func (s *KostServiceImpl) GetByIDService(id uint) (domain.Kost, error) {
	//validate.Var untuk validasi satu variable
	err := s.validate.Var(id, "required,gt=0")
	if err != nil {
		return domain.Kost{}, err
	}

	//repository
	kost, err := s.kostRepo.FindByIDRepository(id)
	if err != nil {
		return domain.Kost{}, err
	}

	return kost, nil
}
