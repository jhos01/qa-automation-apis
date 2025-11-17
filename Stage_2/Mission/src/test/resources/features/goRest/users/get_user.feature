Feature: Obtener lista de usuarios
  Como tester de APIs
  Quiero obtener la lista de usuarios existentes
  Para validar que el endpoint responde correctamente.

  Scenario: Obtener todos los usuarios
    When envio una solicitud para obtener la lista de usuarios
    Then la respuesta debe tener un status 200
    And la lista de usuarios debe contener registros

  Scenario: Validar que la respuesta sea una lista
    When envio una solicitud para obtener la lista de usuarios
    Then la respuesta debe tener un status 200
    And la respuesta debe ser una lista

  Scenario: Validar estructura mínima de un usuario
    When envio una solicitud para obtener la lista de usuarios
    Then la respuesta debe tener un status 200
    And los usuarios deben incluir los campos obligatorios

  Scenario: Obtener usuarios con query inválida
    When envio una solicitud para obtener usuari
