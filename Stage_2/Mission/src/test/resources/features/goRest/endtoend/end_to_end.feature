Feature: Flujo End-to-End de GoRest
  Como tester de APIs
  Quiero validar el funcionamiento completo del flujo de usuario, publicación y comentario
  Para asegurar que todos los servicios trabajan de forma integrada correctamente.

  Scenario: Crear usuario → crear publicación → crear comentario → validar consultas
    Given envio una solicitud para crear un usuario con datos válidos para el flujo e2e
    And envio una solicitud para crear una publicación asociada al usuario para el flujo e2e
    And envio una solicitud para crear un comentario asociado a la publicación para el flujo e2e

    When consulto el usuario por su ID en el flujo e2e
    Then la respuesta del flujo e2e debe tener un status 200

    When consulto la publicación por su ID en el flujo e2e
    Then la respuesta del flujo e2e debe tener un status 200

    When consulto los comentarios de la publicación en el flujo e2e
    Then la respuesta del flujo e2e debe tener un status 200

    When consulto los comentarios realizados por el usuario en el flujo e2e
    Then la respuesta del flujo e2e debe tener un status 200
