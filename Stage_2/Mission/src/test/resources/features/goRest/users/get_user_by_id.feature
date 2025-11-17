Feature: Obtener un usuario por ID
  Como tester de APIs
  Quiero obtener un usuario específico usando su ID
  Para validar que el endpoint devuelve los datos correctos.

  Scenario: Obtener usuario recién creado por ID
    Given que existe un usuario registrado
    When envio una solicitud para obtener el usuario por su ID
    Then la respuesta debe tener un status 200
    And los datos del usuario deben ser correctos

  Scenario: Obtener usuario con ID inexistente
    When envio una solicitud para obtener el usuario con ID inexistente
    Then la respuesta debe tener un status 404

  Scenario: Obtener usuario con ID inválido
    When envio una solicitud para obtener el usuario con ID inválido
    Then la respuesta debe tener un status 404

  Scenario: Obtener usuario y validar email
    Given que existe un usuario registrado
    When envio una solicitud para obtener el usuario por su ID
    Then el email del usuario debe ser correcto