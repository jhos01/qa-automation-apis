Feature: Actualizar completamente un usuario
  Como tester de APIs
  Quiero actualizar todos los datos de un usuario
  Para asegurar que el servicio soporta modificaciones completas.

  Scenario: Actualizar todos los datos del usuario
    Given que existe un usuario registrado
    When envio una solicitud PUT para actualizar todos los datos del usuario
    Then la respuesta debe tener un status 200
    And los datos del usuario deben reflejar los cambios

  Scenario: Actualizar usuario con PUT sin token
    Given que existe un usuario registrado
    When envio una solicitud PUT sin token para actualizar el usuario
    Then la respuesta debe tener un status 401