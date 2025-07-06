package service

import (
	"github.com/go-playground/validator/v10"
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

func (s *KostServiceImpl) GetAllKostService(filters web.FilterKostRequest) ([]web.KostResponse, error) {
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

	kostDTO := web.FormatKosts(kost)

	return kostDTO, nil
}

func (s *KostServiceImpl) GetByIDKostService(id uint) (web.KosDetailResponse, error) {
	//validate.Var untuk validasi satu variable
	err := s.validate.Var(id, "required,gt=0")
	if err != nil {
		return web.KosDetailResponse{}, err
	}

	//repository
	kost, err := s.kostRepo.FindByIDKostRepository(id)
	if err != nil {
		return web.KosDetailResponse{}, err
	}

	//DTO
	kostDTO := web.FormatDetailKos(kost)

	return kostDTO, nil
}
