Feature: Crear publicación
  Como tester de APIs
  Quiero crear publicaciones para un usuario
  Para asegurar que el endpoint funciona correctamente.

  Scenario: Crear publicación válida para usuario existente
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para crear una publicación para ese usuario
    Then la respuesta debe tener un status 201
    And la publicación debe contener un id

  Scenario: Crear publicación sin user_id
    When envio una solicitud para crear una publicación sin user_id
    Then la respuesta debe tener un status 422

  Scenario: Crear publicación con user_id inexistente
    When envio una solicitud para crear una publicación con user_id inexistente
    Then la respuesta debe tener un status 422

  Scenario: Crear publicación sin titulo
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para crear una publicación sin titulo
    Then la respuesta debe tener un status 422

  Scenario: Crear publicación sin body
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para crear una publicación sin body
    Then la respuesta debe tener un status 422

  Scenario: Crear publicación con titulo vacío
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para crear una publicación con titulo vacio
    Then la respuesta debe tener un status 422

  Scenario: Crear publicación sin token
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud sin token para crear una publicación
    Then la respuesta debe tener un status 401

  Scenario: Crear publicación con token inválido
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud con token inválido para crear una publicación
    Then la respuesta debe tener un status 401

  Scenario: Crear publicación con body muy largo
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud con body largo para crear una publicación
    Then la respuesta debe tener un status 201

  Scenario: Crear publicación con datos dinámicos
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para crear una publicación con datos dinámicos
    Then la respuesta debe tener un status 201
