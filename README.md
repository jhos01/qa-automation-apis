# Portafolio de Automatización de Pruebas - Jhoselin Luizaga

Este repositorio contiene proyectos de automatizacion de pruebas API donde desarrolle diferentes estrategias de testing, herramientas y frameworks durante el curso QA Pro Level de QAX

## 🎯 1. Propósito: ¿Por Qué Usar esta Plantilla?

- **Organización Total:** Tendrás un lugar central y ordenado para cada una de tus entregas del programa (Stages), evitando archivos sueltos o desordenados. 
- **Consistencia Profesional:** La estructura es la misma para proyectos de API, Web o Mobile, lo que demuestra que sabes mantener un estándar de trabajo.
- **Portafolio de Impacto:** En lugar de solo decir que sabes automatizar, podrás mostrar tu código, tu lógica y tu forma de trabajar directamente desde tu perfil de GitHub. 

## 🧱 2. Estructura del Repositorio: ¿Dónde Va Cada Cosa? 

Esta es la organización de carpeta. Piensa en ella como un archivador digital perfectamente etiquetado.

```
.
├── .gitignore   <-- Archivo para ignorar archivos que no deben subirse.
├── README.md    <-- ESTE ES EL MÁS IMPORTANTE: La portada de tu portafolio.
├── assets/      <-- Carpeta para guardar imágenes o recursos que uses en tu documentación.
├── Stage_1/     <-- Carpeta para todo el trabajo del Módulo 1.
│   ├── Challenge/
│   │   └── README.md
│   ├── Mission/
│   │   └── README.md
│   └── Quick_Tasks/
│       └── README.md
└── Stage_2/     <-- Carpeta para todo el trabajo del Módulo 2.
    ├── Challenge/
    │   └── README.md
    ├── Mission/
    │   └── README.md
    └── Quick_Tasks/
        └── README.md
```

**Ejemplo Práctico:** Si en el `Stage_1` el `Challenge` consiste en automatizar el endpoint de login de una API con Rest Assured:

- Dentro de la carpeta `Stage_1/Challenge/` guardarás todo tu código fuente (los archivos `.java`, el `pom.xml`, etc.). 
- En el archivo `Stage_1/Challenge/README.md` escribirás algo como: 

> ### Challenge 1: Automatización de API Login
>
> Este proyecto automatiza las pruebas para el endpoint `/login` de la API de ejemplo.
>
> **Tecnologías Usadas:** 
> - Java 11 
> - Rest Assured 
> - TestNG 
> - Maven 
>
> **Cómo ejecutar las pruebas:** 
> 1. Clonar el repositorio. 
> 2. Navegar a la carpeta `Stage_1/Challenge/`. 
> 3. Ejecutar el comando `mvn clean test` desde la terminal. 
> 4. El reporte de resultados se generará en la carpeta `target/surefire-reports`. 

## 🚀 3. Guía Paso a Paso para Empezar 
Sigue estas instrucciones en orden para configurar tu portafolio. 

1.  **Paso 1: Crea tu Propio Repositorio desde la Plantilla** 
    - Ve a la página del repositorio plantilla en GitHub. 
    - Busca y haz clic en el botón verde `"Use this template"` y selecciona `"Create a new repository"`. 
    - Nombra tu repositorio: Dale un nombre descriptivo, por ejemplo: `portafolio-automatizacion-juan-perez` o `qax-api-portfolio`. 
    - Asegúrate de que sea **Público** para que los reclutadores puedan verlo. 
    - Haz clic en `"Create repository"`. ¡Listo! Ya tienes una copia personal en tu cuenta de GitHub. 

2.  **Paso 2: Clona el Repositorio a tu Computador** 
    - En la página de tu nuevo repositorio, haz clic en el botón verde `<> Code`. 
    - Copia la URL HTTPS (ej. `https://github.com/tu-usuario/portafolio-automatizacion-juan-perez.git`).
    - Abre una terminal o Git Bash en tu computador y ejecuta el comando: 
      ```
      git clone https://github.com/tu-usuario/portafolio-automatizacion-juan-perez.git
      ```
    - Ahora ya tienes el proyecto en tu máquina local y puedes empezar a añadir tus archivos. 

3.  **Paso 3: Personaliza el `README.md` Principal**
    Este es el archivo más importante. Es tu carta de presentación. Ábrelo y reemplaza el contenido con algo como esto: 

> # Portafolio de Automatización de Pruebas - [Tu Nombre Completo] 
>
> ¡Hola! Soy un apasionado QA en formación, enfocado en la automatización de pruebas de API.
> Este repositorio contiene los proyectos que he desarrollado como parte de la mentoría QA Pro Level de QAX. 
>
> ### Tecnologías que utilizo 
> - **Lenguaje:** Java 
> - **Frameworks de Automatización:** Rest Assured, Selenium WebDriver 
> - **Gestión de Pruebas:** TestNG, JUnit 
> - **Herramientas Adicionales:** Postman, Git, Maven, Docker (opcional)
>
> ### Proyectos
>
> #### Stage 1: Fundamentos de API Testing 
> - **Challenge:** Automatización de un CRUD completo para la API de gestión de usuarios. Link a la carpeta del Challenge 
> - **Mission:** Creación de un framework base para pruebas de API con reportes personalizados. 
Link a la carpeta de la Mission 
>
> #### Stage 2: ... 
>
> ### Contacto: 
> - **LinkedIn:** [Tu Perfil de LinkedIn] 
> - **Email:** tu.correo@ejemplo.com 

## 📝 4. Convenciones de Versionamiento (Reglas de Oro de Git)

Para demostrar que trabajas de forma limpia y profesional, sigue estas reglas al guardar tus cambios.

### Commits: Mensajes Claros y Descriptivos 

Usa el formato: `tipo(scope): mensaje`

- `tipo`: ¿Qué tipo de cambio hiciste? 
  - `feat`: Si agregaste una nueva funcionalidad (ej. una nueva prueba). 
  - `fix`: Si corregiste un error (un bug). 
  - `docs`: Si solo cambiaste la documentación (como un `README.md`). 
  - `test`: Si agregaste o modificaste pruebas existentes. 
  - `chore`: Tareas de mantenimiento que no afectan el código (ej. actualizar el `.gitignore`). 
- `scope` (opcional): ¿Qué parte del proyecto modificaste? (ej. `login`, `users-api`, `reports`)
- `mensaje`: Una descripción corta y en minúsculas de lo que hiciste.

**Ejemplos:** 
- `feat(users): agrega prueba para crear usuario con datos válidos` 
- `fix(login): corrige el selector para el botón de enviar` 
- `docs(readme): actualiza la sección de tecnologías usadas` 
- `test(reports): implementa la generación de reportes en HTML` 

### Ramas (Branches): Organización del Trabajo

Crea una nueva rama para cada tarea importante. Usa el formato: `stageX/tipo/nombre-descriptivo` 

- `stageX`: El módulo en el que estás trabajando (`stage1`, `stage2`).
- `tipo`: El tipo de entrega (`quicktask`, `challenge`, `mission`). 
- `nombre-descriptivo`: Una descripción corta separada por guiones. 

**Ejemplos:** 
- `stage1/challenge/automatizar-crud-usuarios` 
- `stage2/mission/implementar-autenticacion-jwt` 
- `stage1/fix/corregir-error-en-reporte` 

¡Listo! Con estas instrucciones detalladas, tienes todo lo necesario para no solo cumplir con tus entregas, sino para construir un portafolio profesional del que te sientas orgulloso. 
