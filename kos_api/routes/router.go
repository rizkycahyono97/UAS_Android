package routes

import (
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/logger"
	"github.com/rizkycahyono97/UAS_Android/controllers"
)

func SetupRoutes(app *fiber.App, kostController *controllers.KostController) {
	//middleware untuk logging
	app.Use(logger.New())

	//group route
	r := app.Group("/api")

	kostRoutes := r.Group("/v1")
	kostRoutes.Get("/kosts", kostController.GetAllKostController)
	kostRoutes.Get("/kosts/kos/:id", kostController.GetKostByIdController)
}
