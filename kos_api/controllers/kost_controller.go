package controllers

import (
	"errors"
	"github.com/go-playground/validator/v10"
	"github.com/gofiber/fiber/v2"
	"github.com/rizkycahyono97/UAS_Android/model/web"
	"github.com/rizkycahyono97/UAS_Android/service"
	"gorm.io/gorm"
	"strconv"
)

type KostController struct {
	service service.KostService
}

func NewKostController(service service.KostService) *KostController {
	return &KostController{service: service}
}

func (ks *KostController) GetAllKostController(c *fiber.Ctx) error {
	//validasi query params
	allowedParams := map[string]bool{
		"nama":     true,
		"tipe":     true,
		"status":   true,
		"minHarga": true,
		"maxHarga": true,
	}
	sentParams := c.Queries()

	for paramName := range sentParams {
		if !allowedParams[paramName] {
			return c.Status(fiber.StatusBadRequest).JSON(web.ApiResponse{
				Code:    "400",
				Message: "query params tidak valid",
				Data:    nil,
			})
		}
	}

	//validasi untuk field query paramsnya
	filters := web.FilterKostRequest{}

	filters.Nama = c.Query("nama")
	filters.Tipe = c.Query("tipe")
	filters.StatusKetersediaan = c.Query("status_ketersediaan")
	filters.Alamat = c.Query("alamat")

	//parsing dari string ke uint
	if minHargaStr := c.Query("minHarga"); minHargaStr != "" {
		minHarga, err := strconv.Atoi(minHargaStr)
		if err == nil {
			minHargaUint := uint(minHarga)
			filters.MinHarga = &minHargaUint
		}
	}
	if maxHargaStr := c.Query("maxHarga"); maxHargaStr != "" {
		maxHarga, err := strconv.Atoi(maxHargaStr)
		if err == nil {
			maxHargaUint := uint(maxHarga)
			filters.MaxHarga = &maxHargaUint
		}
	}

	//panggil service
	kost, err := ks.service.GetAllKostService(filters)
	if err != nil {
		//jika error dari validasi service
		if _, ok := err.(validator.ValidationErrors); ok {
			return c.Status(fiber.StatusBadRequest).JSON(web.ApiResponse{
				Code:    "400",
				Message: "Input Validation Failed",
				Data:    nil,
			})
		}

		//untuk error lainya
		return c.Status(fiber.StatusInternalServerError).JSON(web.ApiResponse{
			Code:    "500",
			Message: "Internal Server Error",
			Data:    nil,
		})
	}

	//jika berhasil
	return c.Status(fiber.StatusOK).JSON(web.ApiResponse{
		Code:    "200",
		Message: "OK",
		Data:    kost,
	})
}

func (ks *KostController) GetKostByIdController(c *fiber.Ctx) error {
	//ambil parameternya
	idStr := c.Params("id")

	// parsing dari string ke uint
	id, err := strconv.ParseUint(idStr, 10, 32)
	if err != nil {
		return c.Status(fiber.StatusBadRequest).JSON(web.ApiResponse{
			Code:    "400",
			Message: "Invalid ID Format",
			Data:    nil,
		})
	}

	//service
	kos, err := ks.service.GetByIDKostService(uint(id))
	if err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return c.Status(fiber.StatusNotFound).JSON(web.ApiResponse{
				Code:    "404",
				Message: "Kost Not Found",
				Data:    nil,
			})
		}

		if _, ok := err.(validator.ValidationErrors); ok {
			return c.Status(fiber.StatusBadRequest).JSON(web.ApiResponse{
				Code:    "400",
				Message: "Invalid ID Format",
				Data:    nil,
			})
		}

		return c.Status(fiber.StatusInternalServerError).JSON(web.ApiResponse{
			Code:    "500",
			Message: "Internal Server Error",
			Data:    nil,
		})
	}

	return c.Status(fiber.StatusOK).JSON(web.ApiResponse{
		Code:    "200",
		Message: "OK",
		Data:    kos,
	})
}
