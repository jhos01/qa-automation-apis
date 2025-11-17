Feature: Actualizar parcialmente un usuario
  Como tester de APIs
  Quiero modificar algunos atributos de un usuario existente
  Para validar que el método PATCH funciona correctamente.

  Scenario: Actualizar parcialmente el nombre
    Given que existe un usuario registrado
    When envio una solicitud PATCH para modificar parcialmente los datos del usuario
    Then la respuesta debe tener un status 200
    And los datos actualizados deben reflejar los cambios parciales

  Scenario: Actualizar usuario con PATCH usando email inválido
    Given que existe un usuario registrado
    When envio una solicitud PATCH para actualizar el email invalido
    Then la respuesta debe tener un status 422