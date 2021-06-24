##Installation
It's possible to simple import project into your favourite IDE.
Clone this repository:
```bash
git clone https://github.com/damianbregier/conference
```

Quick info on how to run a Spring Boot application
1. Build using maven: ```mvn install```
2. Execute: ```java -jar target/conference-0.0.1-SNAPSHOT.jar```

Access the deployed web application at: http://localhost:8080/

Note: this project by default uses H2 database. You can change it in the application.properties file.
```spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.url=jdbc:h2:file:./conference
   spring.h2.console.path=/console
   spring.h2.console.enabled=true
   
   spring.datasource.username=root
   spring.datasource.password=
   
   spring.jpa.hibernate.ddl-auto=create
```

##Usage

Database is automatically populated with example users and lectures, informations about these objects will be used in the examples.

1. List of all lectures/conference plan

    GET ```http://localhost:8080/api/lectures```

2. List of user's lectures

    GET ```http://localhost:8080/api/users/{login}```
    
    Example: http://localhost:8080/api/users/mkwisniewski

3. Reservation process

    PUT ```http://localhost:8080/api/users/{login}/{email}/reservations/{lecture_id}```

    Example: http://localhost:8080/api/users/mkwisniewski/wisniewski@gmail.com/reservations/1

4. Canceling reservation

    PUT ```http://localhost:8080/api/users/{login}/{email}/reservations-cancel/{lecture_id}```

    Example: http://localhost:8080/api/users/mkwisniewski/wisniewski@gmail.com/reservations-cancel/1

5. Email update

    It updates ONLY email

    PUT ```http://localhost:8080/api/users/{id}/email```

    Example: http://localhost:8080/api/users/1/email

6. List of users

    GET ``http://localhost:8080/api/users``

##Additional methods

7. Delete user by id

    DELETE ```http://localhost:8080/api/users/{id}```
    
    Example: http://localhost:8080/api/users/1

8. Delete ALL users

    DELETE ```http://localhost:8080/api/users```

9. Delete lecture by id

    DELETE ```http://localhost:8080/api/lectures/{id}```
    
    Example: http://localhost:8080/api/lectures/1

10. Update lecture by id

    PUT ```http://localhost:8080/api/lectures/{id}```
    
    Example: http://localhost:8080/api/lectures/2

11. Create lecture

    POST ````http://localhost:8080/api/lectures````

12. Create user

    POST ```http://localhost:8080/api/users```
