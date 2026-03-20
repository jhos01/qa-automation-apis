Feature: Consultar product

	Scenario: Consultar laptop
	
		- Given la API esta disponible
		- When consulto el producto laptop
		- Then la respuesta debe tener status 101
		- And el contenido debe tener el campo producto = laptop
