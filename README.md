# Projeto para enviar notificações com java (notification-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AlissonMedeiros_notification-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=AlissonMedeiros_notification-java)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlissonMedeiros_notification-java&metric=coverage)](https://sonarcloud.io/dashboard?id=AlissonMedeiros_notification-java)


Projeto backend com java e __spring boot__.

Com uma implementação de __Clean Arch__.

A qualidade dos testes é validada pelo __JaCoCo__ e  __pitest (mutation testing)__

O PostgreSQL dentro do contexto de testes de serviço é provido pelo __https://www.testcontainers.org/__

CI da aplicação é o __Actions__ do github

Em qualquer merge para a master é feito deploy no __heroku__

__https://notificaiton-java.herokuapp.com/swagger-ui.html__

## Build da aplicação com testes :computer:
Para construir a aplicação e rodar toda a suite de testes __maven__.

`mvn clean install`

## Gerando a imagem do docker da aplicação :scroll:
Para gerar a imagem docker da aplicação utilize o comando:

`docker build -t notitication:1.0.0 .`

## Banco de dados com docker-compose :computer:
Para executar o banco de dados basta apenas executar o comando:

`docker-compose -f .docker-compose/stack.yml up`

## Aplicação com docker (docker-compose) :computer:
Para executar a imagem gerada da aplicação basta apenas executar o comando:

`docker-compose -f .docker-compose/app.yml up`

A aplicação estará disponível na porta `8080`

## Utilizando a aplicação
A Utilização da API pode ser feita através de seu Swagger `http://localhost:8080/swagger-ui.html`

Health Check: `http://localhost:8080/actuator/health`

## Rodando a aplicação via IDE :computer:
Para executar a aplicação pela sua IDE de preferência adicione as seguintes variáveis de ambiente:

`POSTGRES_URL=jdbc:postgresql://localhost:5432/notification`

`POSTGRES_USER=user`

`POSTGRES_PASSWORD=test123`

`Clicar em "run" na classe NotificationApplication.java`

Confira o log e com tudo correto a aplicação estará disponível na porta `8080`
