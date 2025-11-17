Feature: Obtener comentarios de una publicación
  Como tester de APIs
  Quiero obtener los comentarios asociados a una publicación
  Para validar que el endpoint devuelve los registros correctos.

  Scenario: Obtener comentarios de publicación válida
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    And que existe un comentario creado para esa publicación
    When envio una solicitud para obtener los comentarios de esa publicación
    Then la respuesta debe tener un status 200

  Scenario: Obtener comentarios de publicación inexistente
    When envio una solicitud para obtener comentarios de publicación inexistente
    Then la respuesta debe tener un status 404

  Scenario: Obtener comentarios de publicación con ID inválido
    When envio una solicitud para obtener comentarios de publicación con ID inválido
    Then la respuesta debe tener un status 404

  Scenario: Validar que lista de comentarios es un array
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    And que existe un comentario creado para esa publicación
    When envio una solicitud para obtener los comentarios de esa publicación
    Then la lista de comentarios debe contener registros

  Scenario: Validar estructura del comentario
    Given que existe un usuario registrado para comentarios
    And que existe una publicación creada para ese usuario para comentarios
    And que existe un comentario creado para esa publicación
    When envio una solicitud para obtener los comentarios de esa publicación
    Then los comentarios deben tener campos obligatorios
