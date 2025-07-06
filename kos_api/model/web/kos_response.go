package web

import (
	"github.com/rizkycahyono97/UAS_Android/model/domain"
	"time"
)

type FasilitasResponse struct {
	ID            int    `json:"id"`
	NamaFasilitas string `json:"nama_fasilitas"`
}

type KosImagesResponse struct {
	ID       int    `json:"id"`
	KosID    uint64 `json:"kos_id"`
	UrlImage string `json:"url_image"`
}

type UsersResponse struct {
	ID           int    `json:"id"`
	Username     string `json:"username"`
	Email        string `json:"email"`
	NomorTelepon string `json:"nomor_telepon"`
}

type KosDetailResponse struct {
	ID                 int                 `json:"id"`
	UserID             uint64              `json:"user_id"`
	Nama               string              `json:"nama"`
	Deskripsi          string              `json:"deskripsi"`
	Tipe               string              `json:"tipe"`
	Alamat             string              `json:"alamat"`
	Harga              uint64              `json:"harga"`
	StatusKetersediaan string              `json:"status_ketersediaan"`
	UpdatedAt          time.Time           `json:"updated_at"`
	User               UsersResponse       `json:"user"`
	Fasilitas          []FasilitasResponse `json:"fasilitas"`
	KosImages          []KosImagesResponse `json:"kos_images"`
}

type KostResponse struct {
	ID                 int                 `json:"id"`
	UserID             uint64              `json:"user_id"`
	Nama               string              `json:"nama"`
	Deskripsi          string              `json:"deskripsi"`
	Tipe               string              `json:"tipe"`
	Alamat             string              `json:"alamat"`
	Harga              uint64              `json:"harga"`
	StatusKetersediaan string              `json:"status_ketersediaan"`
	UpdatedAt          time.Time           `json:"updated_at"`
	User               UsersResponse       `json:"user"`
	Fasilitas          []FasilitasResponse `json:"fasilitas"`
	KosImages          []KosImagesResponse `json:"kos_images"`
}

// ================
// formatter======
// ================
func FormatUser(user domain.Users) UsersResponse {
	return UsersResponse{
		ID:           int(user.ID),
		Username:     user.Username,
		Email:        user.Email,
		NomorTelepon: user.NomorTelepon,
	}
}

// DTO kost by id
func FormatDetailKos(kos domain.Kos) KosDetailResponse {
	var fasilitasFormatted []FasilitasResponse
	for _, kos := range kos.Fasilitas {
		fasilitasFormatted = append(fasilitasFormatted, FasilitasResponse{
			ID:            int(kos.ID),
			NamaFasilitas: kos.NamaFasilitas,
		})
	}

	var imagesFormatted []KosImagesResponse
	for _, image := range kos.KosImages {
		imagesFormatted = append(imagesFormatted, KosImagesResponse{
			ID:       int(image.ID),
			KosID:    image.KosID,
			UrlImage: image.UrlImage,
		})
	}

	return KosDetailResponse{
		ID:                 int(kos.ID),
		UserID:             kos.UserID,
		Nama:               kos.Nama,
		Deskripsi:          kos.Deskripsi,
		Tipe:               kos.Tipe,
		Alamat:             kos.Alamat,
		Harga:              kos.Harga,
		StatusKetersediaan: kos.StatusKetersediaan,
		UpdatedAt:          kos.UpdatedAt,
		User:               FormatUser(kos.User),
		Fasilitas:          fasilitasFormatted,
		KosImages:          imagesFormatted,
	}
}

// DTO all kost
func FormatKost(kos domain.Kos) KostResponse {
	// ... (logika format gambar sama seperti di atas)
	var imagesFormatted []KosImagesResponse
	for _, image := range kos.KosImages {
		imagesFormatted = append(imagesFormatted, KosImagesResponse{
			ID:       int(image.ID),
			KosID:    image.KosID,
			UrlImage: image.UrlImage,
		})
	}
	return KostResponse{
		ID:                 int(kos.ID),
		Nama:               kos.Nama,
		Deskripsi:          kos.Deskripsi,
		Alamat:             kos.Alamat,
		Harga:              kos.Harga,
		Tipe:               kos.Tipe,
		StatusKetersediaan: kos.StatusKetersediaan,
		User:               FormatUser(kos.User),
		KosImages:          imagesFormatted,
	}
}

func FormatKosts(kots []domain.Kos) []KostResponse {
	var kotsFormatted []KostResponse
	for _, kos := range kots {
		kotsFormatted = append(kotsFormatted, FormatKost(kos))
	}
	return kotsFormatted
}
