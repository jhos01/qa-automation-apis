# Challenge 1 & 2 - Automatizacion de pruebas API con serializacion y desearializacion de los requests

El Challenge 1 & 2 consisten en 1 proyecto de automatizacion de pruebas API usando Rest Assured, Java, Cucumber y Maven. El proyecto comprende la creacion de pruebas de obtencion de usuarios y validacion de la informacion devuelta 
## Seccion 1
Objetivo/ Historia de Usuario
Gestion de Publicaciones y Comentarios: En este proyecto quiero poder listar usuarios para validar que la API [JSON Placeholder](https://jsonplaceholder.typicode.com) funcione correctamente y que los datos sean consistentes.

📁 Carpeta: Stage_2/Challenge

## Criterios de aceptación

BaseUrl: https://jsonplaceholder.typicode.com"

Listar usuarios:

- GET /users debe devolver HTTP 200.
- La lista no debe estar vacía y cada usuario debe tener id, name, username y email.

## 🧪 Casos de Prueba 
Archivo .feature
```
Feature: Gestión de comentarios en la aplicación

Scenario: CP01 El usuario crea un comentario exitosamente
  Given que el usuario quiere agregar un comentario a un post existente
  And el usuario proporciona la siguiente información:
    | postId | nombre | email             | comentario                        |
    | 1      | Juan   | juan@email.com    | Este es un comentario de prueba   |
  When el usuario envía la solicitud para crear el comentario
  Then el sistema debe registrar el comentario correctamente
  And debe responder con un código 201
  And el comentario registrado debe incluir un ID generado por el sistema
  And los datos enviados deben coincidir con la información registrada

Scenario: CP02 El usuario consulta los comentarios de un post
  Given que el usuario quiere ver los comentarios asociados a un post específico
  And el post tiene comentarios registrados
  When el usuario solicita ver los comentarios del post con ID 1
  Then el sistema debe devolver la lista de comentarios existentes
  And debe responder con un código 200
  And cada comentario debe incluir un ID, nombre, email válido y contenido
  And el email de cada comentario debe contener "@"

```


## Ejecución 
1. Clona o descarga el repositorio https://github.com/jhos01/qax-automation-apis.git
2. Abre una nueva terminal en la carpeta raíz qax-automation-apis.
3. Cambia al directorio del proyecto con el comando: cd Stage_2/Challenge
4. Ejecuta el siguiente comando: "mvn test"

## Seccion 2 Automatizacion de la API Book Store
Objetivo/ Historia de Usuario
En este proyecto como usuario/automatizador de pruebas, quiero poder registrar un usuario, obtener un token de autenticación y consumir endpoints protegidos con ese token, para verificar que la API BookStore [BookStore](https://demoqa.com/swagger/) maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible.



📁 Carpeta: Stage_2/Challenge

## Criterios de aceptación



 
