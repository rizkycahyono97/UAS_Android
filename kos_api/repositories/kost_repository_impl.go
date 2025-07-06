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

func (r *KostRepositoryImpl) FindAllKostRepository(filters web.FilterKostRequest) ([]domain.Kos, error) {
	var kos []domain.Kos

	//pilih model yg dijalankan untuk  query => select * from kost
	query := r.db.Model(&domain.Kos{})

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
	query = query.Preload("Fasilitas").Preload("KosImages").Preload("User")
	err := query.Find(&kos).Error
	if err != nil {
		return nil, err
	}
	return kos, nil
}

func (r *KostRepositoryImpl) FindByIDKostRepository(id uint) (domain.Kos, error) {
	var kos domain.Kos

	//Preload berdasarkan id => "where id = ?"
	err := r.db.Preload("Fasilitas").Preload("KosImages").Preload("User").First(&kos, id).Error // &kos => simpan data di variable kos
	if err != nil {
		return domain.Kos{}, err
	}

	return kos, nil
}
