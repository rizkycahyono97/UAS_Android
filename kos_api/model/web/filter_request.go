package web

type FilterKostRequest struct {
	Nama               string `json:"nama" validate:"omitempty,max=100"`
	Tipe               string `json:"tipe" validate:"omitempty,oneof=putra putri campur"`
	Alamat             string `json:"alamat" validate:"omitempty,max=255"`
	StatusKetersediaan string `json:"status_ketersediaan" validate:"omitempty,oneof=tersedia penuh"`
	MinHarga           *uint  `json:"min_harga" validate:"omitempty,gte=0"`
	MaxHarga           *uint  `json:"max_harga" validate:"omitempty,gte=0,gtfield=MinHarga"`
}
