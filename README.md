# Account card
> Desenvolvido por Fabricio Jacob

---
### Integrações

- [RabbitMQ](https://www.rabbitmq.com/)
```
docker run -d --name rabbit -p 15672:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management
```

#### Contratos via integração HTTP
- request post: **/customers

### Executando localmente as aplicações:

1 - Construa o projeto:
```
./gradlew clean build
```
2 - Inicie a aplicação
```
./gradlew bootRun
```

3 - Faça uma requisição em cada serviço
```
curl -x GET http://localhost:por/actuator/info
```
Em cada projeto existe um arquivo Dockerfile, que pode ser criado para gerar imagem e ser utilizado em um docker-compose.

### Documentação

A documentação básica dos endpotins encontram-se ```http://server/swagger-ui.html```

### Banco de dados
- [Mysql]
```
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql
```

### Tecnologias utilizadas
- [Sring boot]
- [Spring cloud config server]
- [Spring cloud stream]
- [Eureka server]
- [Spring data]
- [Java 11]
- [Model mapper]
- [Spring oauth2]
- [Arquitetura em microservices]
