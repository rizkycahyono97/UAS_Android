package main

import (
	"github.com/gofiber/fiber/v2"
	"github.com/joho/godotenv"
	"github.com/rizkycahyono97/UAS_Android/config"
	"github.com/rizkycahyono97/UAS_Android/controllers"
	"github.com/rizkycahyono97/UAS_Android/repositories"
	"github.com/rizkycahyono97/UAS_Android/routes"
	"github.com/rizkycahyono97/UAS_Android/service"
	"log"
	"os"
)

func main() {

	//load env
	err := godotenv.Load()
	if err != nil {
		log.Println("Error loading .env file")
	}

	//initialize database
	config.InitDB()

	//dependency
	kostRepo := repositories.NewKostRepository(config.DB)
	kostService := service.NewKostService(kostRepo)
	kostController := controllers.NewKostController(kostService)

	//initialize fiber
	app := fiber.New()

	//initialize routes
	routes.SetupRoutes(app, kostController)

	// Jalankan server
	port := os.Getenv("APP_PORT")
	if port == "" {
		port = "9001"
	}
	log.Printf("Listening on port %s", port)
	log.Fatal(app.Listen(":" + port))
}
