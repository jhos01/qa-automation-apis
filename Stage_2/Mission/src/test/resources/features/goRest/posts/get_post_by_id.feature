Feature: Obtener publicación por ID
  Como tester de APIs
  Quiero obtener una publicación específica
  Para validar que el endpoint devuelve la información correcta.

  Scenario: Obtener publicación recién creada por ID
    Given que existe un usuario registrado para publicaciones
    And que existe una publicación creada para ese usuario
    When envio una solicitud para obtener la publicación por su ID
    Then la respuesta debe tener un status 200

  Scenario: Obtener publicación con ID inexistente
    When envio una solicitud para obtener la publicación con ID inexistente
    Then la respuesta debe tener un status 404

  Scenario: Obtener publicación con ID inválido
    When envio una solicitud para obtener la publicación con ID inválido
    Then la respuesta debe tener un status 404

  Scenario: Validar que los datos de una publicación son correctos
    Given que existe un usuario registrado para publicaciones
    And que existe una publicación creada para ese usuario
    When envio una solicitud para obtener la publicación por su ID
    Then los datos de la publicación deben ser correctos

  Scenario: Obtener publicación de otro usuario
    Given que existe un usuario registrado para publicaciones
    When envio una solicitud para obtener una publicación de otro usuario
    Then la respuesta debe tener un status 200