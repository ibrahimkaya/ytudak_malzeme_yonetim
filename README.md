# YTUDAK ITEM MANAGEMENT 
ytudak item management project,

- It was designed for this purpose to better management of the Climbing materials of Yıldız Technical University Mountaineering Club.

- The design decisions made were made to follow the current material management method in the web environment.

## Specifications
- Login
- View, add, delete and edit items, add categories for accounts with the role of the item manager
- Authority of the people in the materialist relay to borrowing and receive the materials (short-term use by a club member for the purpose of climbing)
- The account with the BASKAN role has the authority to approve materials in critical situations such as adding, editing, deleting
- Standard users to see all materials and past borrowing status, to search for information in these lists, to obtain information about which material is currently available.


### Tech
Library uses a couple of open source projects to work properly:
* [Spring Framework](https://spring.io/) - Dependency Injection Container
* [Maven](https://maven.apache.org/) - Project Management Tool
* [MySQL](https://www.mysql.com/) - Relational Database Management System
* [Thymeleaf](https://www.thymeleaf.org/) - Server-Side Java Template Engine
* [Bootstrap](https://getbootstrap.com/) - Front-end Framework 
* [jQuery](https://jquery.com/) - JavaScript Library

### Installation
 - Create MySQL User & Database
```sh

$ CREATE DATABASE ytudak CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

```
Or Change These lines
* [Connection String](https://github.com/ibrahimkaya/ytudak_malzeme_yonetim/blob/989f0ebd89cfbda65034de92b9495cb7a9b20d96/src/main/resources/application.properties#L2)
* [DB Username](https://github.com/ibrahimkaya/ytudak_malzeme_yonetim/blob/989f0ebd89cfbda65034de92b9495cb7a9b20d96/src/main/resources/application.properties#L3)
* [DB Password](https://github.com/ibrahimkaya/ytudak_malzeme_yonetim/blob/989f0ebd89cfbda65034de92b9495cb7a9b20d96/src/main/resources/application.properties#L4)


### Run
```sh
$ mvn clean spring-boot:run
```

### Predefined Admin & User
```sh
ROLE   login - pass
-------------------
ADMIN  admin  - 1
BASKAN baskan - 1
USER   user   - 123
```
### Login Page
[http://localhost:8080/login](http://localhost:8080/login)

### Authors
* [İbrahim Kaya](https://github.com/ibrahimkaya)
* [Ufuk Güler](https://github.com/ufukguler)



License
--- 
MIT


**Free Software**
