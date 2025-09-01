# Spring Boot CRUD Web - Tienda de Libros

Este es un proyecto **Spring Boot CRUD Web** desarrollado con **Thymeleaf** para la gestión de una tienda de productos tecnologicos.

## 🚀 Tecnologías utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA (con MySQL)
- Thymeleaf
- Bootstrap
- Eclipse IDE

## ⚙️ Ejecución del proyecto

### 🔹 Opción 1: Desde Eclipse
El proyecto fue desarrollado en **Eclipse IDE**.  
Puedes ejecutarlo fácilmente haciendo clic en el botón **Play ▶️** dentro de Eclipse, seleccionando la clase principal:

```
src/main/java/com/crudexample/tienda/TiendaApplication.java
```

### 🔹 Opción 2: Desde consola con Maven
También puedes ejecutarlo desde la línea de comandos con:

```bash
mvn spring-boot:run
```

### 🔹 Opción 3: Ejecutar JAR
Si deseas empaquetar el proyecto y ejecutar el JAR:

```bash
mvn clean package
java -jar target/tienda-0.0.1-SNAPSHOT.jar
```

## 🛠 Configuración de la base de datos
En el archivo `src/main/resources/application.properties` asegúrate de configurar tu conexión a MySQL:

```properties
spring.application.name=tienda
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/dbtienda
spring.datasource.username=root
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

## 📸 Capturas de pantalla

**Pantalla principal**

![Pantalla principal](https://raw.githubusercontent.com/omarpedraza1979/springboot-crud-web-thymeleaf-technologyStore/main/docs/images/inicio.png)

**Productos disponibles**

![Productos Disponibles](https://raw.githubusercontent.com/omarpedraza1979/springboot-crud-web-thymeleaf-technologyStore/main/docs/images/listaproductos.png)

**Nuevo producto**

![Nuevo Producto](https://raw.githubusercontent.com/omarpedraza1979/springboot-crud-web-thymeleaf-technologyStore/main/docs/images/nuevoproducto.png)

**Editar Producto**

![Editar Producto](https://raw.githubusercontent.com/omarpedraza1979/springboot-crud-web-thymeleaf-technologyStore/main/docs/images/editarproducto.png)


```

## 📂 Estructura del proyecto

```bash
├───docs
│   └───images
├───public
│   └───images
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───crudexample
│   │   │           └───tienda
│   │   │               ├───controllers
│   │   │               ├───models
│   │   │               └───services
│   │   └───resources
│   │       ├───static
│   │       │   └───css
│   │       └───templates
│   │           └───products
│   └───test
│       └───java
│           └───com
│               └───crudexample
│                   └───tienda


```

## 👨‍💻 Autor
Proyecto desarrollado por **Omar Pedraza** como parte de práctica en **Spring Boot + Thymeleaf**.

## 📜 Licencia
Este proyecto está bajo la licencia [MIT](LICENSE).  
Eres libre de usarlo, modificarlo y distribuirlo con fines personales o educativos.
