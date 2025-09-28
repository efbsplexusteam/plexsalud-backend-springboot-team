# PLUGINS VSCODE

> "extensions": [ "vscjava.vscode-java-pack", "vscjava.vscode-spring-initializr" ]

# STAR NEW PROJECT

> ctrl + shift + p 

> Spring Initializr: Create a Maven Project
>
> Spring Initializr: Create a Gradle Project
>
> Specify Spring Boot version
>
> Specify project languaje
>
> Input Group Id `com.example` (url al reves)
>
> Input Artifact Id `demo` (nombre de la aplicacion)
>
> Specify packaging type `Jar || War`
>
> Specify Java version
>
> Choose dependencies `Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security, Lombok, Gateway, OAuth2 Client, Spring Batch, etc.`
> 
> Spring Initializr: Add Starters *`add libraries`*

> Application `com.example.demo`

# MAVEN

> en el explorador de vscode hay una pestaña llamada MAVEN si la desplegamos veremos la opcion package para crear en archivo que lo ebcontraremos en la carpeta target


# EJECUTAR

> convencion camelcase

> src/java/com/`example`/`demo` en esta ruta comenzaremos a crear nuestro proyecto como modulos y esas cosas
>
> `Demo`Application.java
>
> en la funcion main tendremos la opcion de Run 
---

### Proyecto Maven

Primero, asegúrate de tener el siguiente `pom.xml` para un proyecto Maven básico:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>scaffold-example</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>scaffold-example</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>11</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Estructura del Proyecto

La estructura de un proyecto Spring Boot típica es la siguiente:

```
scaffold-example/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── scaffoldexample
│   │   │               ├── ScaffoldApplication.java
│   │   │               ├── controller
│   │   │               │   └── HelloController.java
│   │   │               └── service
│   │   │                   └── GreetingService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── scaffoldexample
│       │               ├── ScaffoldApplicationTests.java
│       │               ├── controller
│       │               │   └── HelloControllerTest.java
│       │               └── service
│       │                   └── GreetingServiceTest.java
│       └── resources
</pre>
```

### Código Fuente

#### ScaffoldApplication.java

Este es el punto de entrada principal de la aplicación Spring Boot.

```java
package com.example.scaffoldexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }

}
```

#### HelloController.java

Este controlador maneja las solicitudes web y devuelve una respuesta.

```java
package com.example.scaffoldexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

}
```

#### GreetingService.java

Este servicio puede contener lógica de negocio.

```java
package com.example.scaffoldexample.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting() {
        return "Hello from Greeting Service!";
    }

}
```

### Anotaciones Usadas

- **@SpringBootApplication**: Esta anotación es un atajo para `@Configuration`, `@EnableAutoConfiguration` y `@ComponentScan`. Es el punto de entrada principal de la aplicación Spring Boot.
  
- **@RestController**: Combina `@Controller` y `@ResponseBody`, convirtiendo el valor devuelto por las funciones en el cuerpo de la respuesta HTTP.

- **@GetMapping("/hello")**: Esta anotación es una forma concisa de manejar solicitudes GET. Es equivalente a escribir un método anotado con `@RequestMapping(method = RequestMethod.GET)`.

- **@Service**: Marca una clase como un componente que proporciona lógica de negocio.

- **@Autowired**: Inyecta dependencias automáticamente en las clases.

- **@Value("${property.name}")**: Utiliza propiedades del archivo `application.properties`.

### Prueba Unitaria

Para probar los componentes, puedes crear pruebas unitarias utilizando JUnit y Spring Boot Test:

#### HelloControllerTest.java

```java
package com.example.scaffoldexample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/hello"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello, World!"));
    }

}
```

### Ejecutar la Aplicación

Para ejecutar la aplicación Spring Boot:

```sh
./mvnw spring-boot:run
```

Luego, puedes acceder a tu servidor en `http://localhost:8080/hello`.