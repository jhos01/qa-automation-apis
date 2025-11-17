Feature: Obtener lista de publicaciones

  Scenario: Obtener todas las publicaciones
    When envio una solicitud para obtener todas las publicaciones
    Then la respuesta debe tener un status 200

  Scenario: Validar estructura de publicaciones
    When envio una solicitud para obtener todas las publicaciones
    Then la respuesta debe tener un status 200
    And la lista de publicaciones debe contener registros

  Scenario: Validar que GET no requiere token
    When envio una solicitud para obtener todas las publicaciones sin token
    Then la respuesta debe tener un status 200

  Scenario: Obtener publicaciones con query inválida
    When envio una solicitud para obtener publicaciones con query invalida
    Then la respuesta debe tener un status 422

  Scenario: Validar campos obligatorios en cada publicación
    When envio una solicitud para obtener todas las publicaciones
    Then todas las publicaciones deben tener los campos obligatorios
