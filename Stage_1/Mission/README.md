# Mission

- 📁 Carpeta: `Stage_1/Mission`

## Implementacion de pruebas automatizadas para consultar personajes de la API "Los Simpson" usando Rest Assured con Cucumber
 - **Objetivo / Historia de usuario**

En este proyecto se utlizo la API publica Notes API para generar casos de prueba de Gestion de Usuarios automatizados a traves de Rest Assured, Maven,y Cucumber para Java.
 Challenge 1: poder registrar un usuario y hacer login
 Challenge 2:

  - **Criterios de aceptación**

Challenge 1:

        Registro de usuario
            El usuario debe poder crear una cuenta exitosamente.
            El sistema debe confirmar que la cuenta fue creada.
            Debe mostrarse un mensaje claro indicando éxito en el registro.
            El nombre y correo ingresados deben reflejarse correctamente en la cuenta creada.
            El sistema debe asignar un identificador único al nuevo usuario.
            Login de usuario
            El usuario registrado debe poder iniciar sesión exitosamente.
            El sistema debe confirmar que el inicio de sesión fue exitoso.
            El usuario debe ver su información básica (nombre y correo).
            El sistema debe entregar un token o permiso de acceso para continuar usando la aplicación.

Challenge 2: 

    Registro de usuario
            El usuario debe poder crear una cuenta exitosamente.
            El sistema debe confirmar que la cuenta fue creada.
            Debe mostrarse un mensaje claro indicando éxito en el registro.
            El nombre y correo ingresados deben reflejarse correctamente en la cuenta creada.
            El sistema debe asignar un identificador único al nuevo usuario.
            Luego del registro, el usuario queda listo para iniciar sesión.
            Login de usuario
            
            El usuario registrado debe poder iniciar sesión exitosamente.
            El sistema debe confirmar que el inicio de sesión fue exitoso.
            El usuario debe ver su información básica (nombre y correo).
            El sistema debe entregar un token o permiso de acceso para continuar usando la aplicación.
    
  
  - **Ejecución** (comandos o pasos)

   Challenge 1:
   
   1. Descarga la coleccion de Postman Api Testing - jhos01 QAXPERT.postman_collection
   2. Importar la coleccion en Postman
   3. Ejecutar los requests

   Challenge 2:

   1. Descargar el repositorio
   2. Abre 1 nueva terminal en la carpeta raiz
   3. Cambia al directorio del proyecto con el comando: cd Stage_1\Challenge\Challenge2
   4. Tener 1 terminal abierta en el directorio anterior 
   5. Ejecuta el comando "mvn test" para inicializar las pruebas
   

