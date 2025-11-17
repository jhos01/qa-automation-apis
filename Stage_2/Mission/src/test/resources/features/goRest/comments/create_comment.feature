Feature: Crear comentario en una publicación
  Como tester de APIs
  Quiero crear comentarios en publicaciones
  Para validar que el servicio permite agregar contenido asociado.

  Scenario: Crear comentario válido
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    When envio una solicitud para crear un comentario para esa publicación
    Then la respuesta debe tener un status 201
    And el comentario debe contener un id

  Scenario: Crear comentario sin post_id
    When envio una solicitud para crear un comentario sin post_id
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario con post_id inexistente
    When envio una solicitud para crear un comentario con post_id inexistente
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario sin email
    When envio una solicitud para crear un comentario sin email
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario con email inválido
    When envio una solicitud para crear un comentario con email invalido
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario con body vacío
    When envio una solicitud para crear un comentario con body vacio
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario sin nombre
    When envio una solicitud para crear un comentario sin nombre
    Then la respuesta debe tener un status 422

  Scenario: Crear comentario sin token
    When envio una solicitud sin token para crear un comentario
    Then la respuesta debe tener un status 401

  Scenario: Crear comentario con token inválido
    When envio una solicitud con token inválido para crear un comentario
    Then la respuesta debe tener un status 401

  Scenario: Crear comentario dinámico
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    When envio una solicitud para crear un comentario dinámico
    Then la respuesta debe tener un status 201
