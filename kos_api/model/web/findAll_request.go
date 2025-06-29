package web

type FilterKostRequest struct {
	Nama               string `json:"nama"`
	Tipe               string `json:"tipe"`
	Alamat             string `json:"alamat"`
	StatusKetersediaan string `json:"status_ketersediaan"`
	MinHarga           *uint  `json:"min_harga"`
	MaxHarga           *uint  `json:"max_harga"`
}
