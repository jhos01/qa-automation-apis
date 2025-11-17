Feature: Crear usuario en GoRest
  Como tester de APIs
  Quiero crear usuarios con datos válidos
  Para asegurarme que el servicio permite registrar nuevos usuarios correctamente.

  Scenario: Crear usuario con datos válidos
    Given envio una solicitud para crear un usuario con datos válidos
    Then la respuesta debe tener un status 201

  Scenario: Crear usuario con email repetido
    Given que existe un usuario registrado
    When envio una solicitud para crear un usuario con el mismo email
    Then la respuesta debe tener un status 422

  Scenario: Crear usuario sin email
    When envio una solicitud para crear un usuario sin email
    Then la respuesta debe tener un status 422

  Scenario: Crear usuario sin nombre
    When envio una solicitud para crear un usuario sin nombre
    Then la respuesta debe tener un status 422

  Scenario: Crear usuario sin token
    When envio una solicitud sin token para crear un usuario
    Then la respuesta debe tener un status 401

  Scenario: Crear usuario con token inválido
    When envio una solicitud con token inválido para crear un usuario
    Then la respuesta debe tener un status 401

  Scenario: Crear usuario con gender inválido
    When envio una solicitud para crear un usuario con gender invalido
    Then la respuesta debe tener un status 422

  Scenario: Crear usuario con email generado dinámicamente
    When envio una solicitud para crear un usuario con email dinámico
    Then la respuesta debe tener un status 201
