Feature: Obtener comentarios realizados por un usuario
  Como tester de APIs
  Quiero obtener todos los comentarios asociados a un usuario
  Para validar que el endpoint funciona correctamente.

  Scenario: Obtener comentarios por user_id válido
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    And que existe un comentario creado para esa publicación
    When envio una solicitud para obtener los comentarios realizados por ese usuario
    Then la respuesta debe tener un status 200

  Scenario: Obtener comentarios de usuario sin comentarios
    Given que existe un usuario registrado para comentarios
    When envio una solicitud para obtener los comentarios realizados por ese usuario
    Then la respuesta debe tener un status 200

  Scenario: Obtener comentarios con user_id inexistente
    When envio una solicitud para obtener comentarios con user_id inexistente
    Then la respuesta debe tener un status 404

  Scenario: Obtener comentarios con user_id inválido
    When envio una solicitud para obtener comentarios con user_id inválido
    Then la respuesta debe tener un status 404

  Scenario: Validar estructura del comentario por usuario
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    And que existe un comentario creado para esa publicación
    When envio una solicitud para obtener los comentarios realizados por ese usuario
    Then los comentarios por usuario deben tener campos obligatorios