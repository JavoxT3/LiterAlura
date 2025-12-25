# 📚 LiterAlura

Proyecto desarrollado en **Java con Spring Boot** que consume la API pública **Gutendex** para buscar libros, almacenar información en una base de datos relacional y permitir consultas estadísticas desde consola.

---

## 🚀 Funcionalidades

El sistema permite al usuario:

1. 🔍 Buscar libros por título usando la API de Gutendex  
2. 💾 Guardar libros y autores en la base de datos  
3. 📖 Listar libros registrados  
4. ✍️ Listar autores registrados  
5. 📆 Listar autores vivos en un año determinado  
6. 🌍 Mostrar la cantidad de libros por idioma  

---

## 🛠️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Jackson** (para conversión de JSON)
- **API Gutendex**
- **Maven**

---

## 🌐 API utilizada

**Gutendex**  
API pública de libros del Proyecto Gutenberg.

Ejemplo de búsqueda:
https://gutendex.com/books/?search=pride


---

## 🧩 Modelo de datos

### 📘 Libro
- Título
- Idioma
- Número de descargas
- Autor (relación ManyToOne)

### ✍️ Autor
- Nombre
- Año de nacimiento
- Año de fallecimiento

Un autor puede tener varios libros, pero un libro pertenece a un solo autor.

---

## 🗄️ Base de datos

Se utiliza **PostgreSQL** como base de datos relacional.

Configuración básica en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

##▶️ Ejecución del proyecto

Clonar el repositorio

Configurar la base de datos PostgreSQL

Ejecutar la clase LiterAluraApplication

Interactuar con el menú desde la consola


## Menú del sistema

<img width="517" height="342" alt="Captura de pantalla 2025-12-24 233031" src="https://github.com/user-attachments/assets/918d8ec1-d231-411e-9c69-7d8f87b6adc2" />

## Funciones del proyecto

Opción 1: Buscar libro por título

https://github.com/user-attachments/assets/02df1478-f4a0-4c6d-92a5-f21dbfec3bbe


Opción 2: Listar libros registrados

https://github.com/user-attachments/assets/d16caaaf-3221-47e2-91be-514a76e18700


Opción 3: Listar autores registrados

https://github.com/user-attachments/assets/b1c8e68d-ac02-41e5-93b4-33577bd96463


Opción 4: Listar autores vivos en un determinado año

https://github.com/user-attachments/assets/2b8c3197-2f46-4c08-b4f0-4a4d472e9f91


Opción 5: Listar libros por idioma

https://github.com/user-attachments/assets/fc6f193e-1895-4702-948b-6c960a62218c




