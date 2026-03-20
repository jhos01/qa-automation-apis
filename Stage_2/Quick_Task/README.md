# Stage 2 - Quick Task

## Título de la entrega

Quick Task - Clases básicas, POJO y archivo Feature en Java

## Objetivo / Historia de usuario

Como aprendiz de automatización QA,
quiero practicar la creación de clases Java básicas, un POJO y un archivo feature en Gherkin,
para reforzar la sintaxis, la estructura de archivos y las buenas prácticas iniciales de organización del proyecto.

## Criterios de aceptación

* Se crea una clase `Animal.java` con los atributos `especie` y `edad`.
* La clase `Animal` incluye el método `hacerSonido()` que imprime el mensaje: `El animal hace un sonido`.
* Se crea un POJO `Producto.java` con los atributos privados `codigo`, `nombre` y `precio`.
* La clase `Producto` incluye constructor vacío, getters y setters.
* Se crea el archivo `producto.feature` con la feature **Consultar producto**.
* El escenario en Gherkin consulta el producto con código `101` y valida que el nombre sea `Laptop`.
* La estructura de carpetas permite identificar claramente código fuente, recursos y evidencias.

## Estructura sugerida del proyecto

```bash
Stage_2/
└── Quick_Task/
    └── RestAssuredWarmUp2/
        ├── src/
        │   └── main/
        │       └── java/
        │       │   └── Main.java
        │       │
        │       └── test/
        │       └── java/
        │           └── Animal.java
        │           └── FirstApiTest.java
        │           └── Producto.java
        │
        │
        ├── Producto.feature.txt
        └── README/
```

## Estrategia de prueba

### Alcance

La práctica se enfoca en validar la correcta creación y estructura de archivos, sin necesidad de ejecución de pruebas automatizadas ni integración con frameworks.

### Casos a revisar

1. Verificar que `Animal.java` exista y contenga:

   * clase `Animal`
   * atributo `String especie`
   * atributo `int edad`
   * método `hacerSonido()`

2. Verificar que `Producto.java` exista y contenga:

   * atributos privados `codigo`, `nombre`, `precio`
   * constructor vacío
   * métodos getter y setter para cada atributo

3. Verificar que `producto.feature` exista y contenga:

   * `Feature: Consultar producto`
   * un escenario en sintaxis Gherkin
   * pasos `Given`, `When`, `Then`
   * validación del producto con código `101`
   * validación del nombre esperado `Laptop`

### Datos de prueba

* Código de producto: `101`
* Nombre esperado: `Laptop`
* Mensaje esperado en `Animal`: `El animal hace un sonido`

### Precondiciones

* Tener creado un proyecto Java básico.
* Contar con un editor como VS Code, IntelliJ IDEA o Eclipse.
* Crear los archivos en rutas separadas dentro del proyecto.
* No se requiere configuración de Maven, Gradle, JUnit o Cucumber para esta actividad.

## Ejecución

### Pasos

1. Crear la carpeta de entrega en la ruta solicitada:
   `Stage_2/Quick_Task/tu-entrega-aqui`
2. Crear el archivo `Animal.java` con la clase básica solicitada.
3. Crear el archivo `Producto.java` con estructura POJO.
4. Crear el archivo `producto.feature` con sintaxis Gherkin.
5. Guardar evidencias en la carpeta `evidencias/`.
6. Subir la carpeta completa al repositorio GitHub.

### Comandos sugeridos para Git

```bash
git add .
git commit -m "Add Quick Task: Java classes, POJO and feature file"
git push origin main
```


## Archivos de soporte

### `Animal.java`

```java
public class Animal {
    String especie;
    int edad;

    public void hacerSonido() {
        System.out.println("El animal hace un sonido");
    }
}
```

### `Producto.java`

```java
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
```

### `producto.feature`

```gherkin
Feature: Consultar producto

  Scenario: Consultar producto por código
    Given existe un producto con código 101
    When consulto el producto
    Then el nombre del producto debe ser "Laptop"
```
