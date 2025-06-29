package repositories

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"github.com/rizkycahyono97/UAS_Android/model/web"
	"gorm.io/gorm"
)

type KostRepositoryImpl struct {
	db *gorm.DB
}

func NewKostRepository(db *gorm.DB) KostRepository {
	return &KostRepositoryImpl{db: db}
}

func (r *KostRepositoryImpl) FindAllRepository(filters web.FilterKostRequest) ([]domain.Kost, error) {
	var kosts []domain.Kost

	//pilih model yg dijalankan untuk  query => select * from kost
	query := r.db.Model(&domain.Kost{})

	//filter
	if filters.Nama != "" {
		query = query.Where("nama LIKE ?", "%"+filters.Nama+"%")
	}
	if filters.Alamat != "" {
		query = query.Where("alamat LIKE ?", "%"+filters.Alamat+"%")
	}
	if filters.Tipe != "" {
		query = query.Where("tipe LIKE ?", "%"+filters.Tipe+"%")
	}
	if filters.StatusKetersediaan != "" {
		query = query.Where("status_ketersediaan LIKE ?", "%"+filters.StatusKetersediaan+"%")
	}
	if filters.MinHarga != nil {
		query = query.Where("harga >= ?", filters.MinHarga)
	}
	if filters.MaxHarga != nil {
		query = query.Where("harga <= ?", filters.MaxHarga)
	}

	//eager loading
	query = query.Preload("Fasilitas")
	err := query.Find(&kosts).Error
	if err != nil {
		return nil, err
	}
	return kosts, nil
}

func (r *KostRepositoryImpl) FindByIDRepository(id uint) (domain.Kost, error) {
	var kos domain.Kost

	//Preload berdasarkan id => "where id = ?"
	err := r.db.Preload("Fasilitas").First(&kos, id).Error // &kos => simpan data di variable kos
	if err != nil {
		return domain.Kost{}, err
	}

	return kos, nil
}
