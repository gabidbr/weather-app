# Aplicatie Forecast YonderApp

YonderApp este o aplicatie web reactiva creata pentru a furinza forecast-ul pentru o lista de orase.


## Tehnologii

- Spring Boot
- Spring WebFlux
- R2DBC (nu e utilizat inca)
- PostgreSQL (nu e utilizat inca)
- Docker
- Lombok
- WireMock

Aplicatia este reactiva pentru a gestiona fluxurile de date intr-un mod asincron.
Acest lucru e realizat prin utilizarea Mono si Flux din Spring WebFlux.

### Prerequisites

- Java 11 
- Docker
- Maven

### Setup

1. Clonarea proiectului local
2. Mergi in directorul proiectului si ruleaza 'docker-compose up' pentru a porni serviciile Docker
3. Ruleaza 'mvn clean install' pentru a instala dependintele si a rula testele
4. Ruleaza aplicatia
5. Aplicatia ar trebui sa fie functionala si sa poti accesa endpoint-ul pentru forecast

### Exemplu de request

- GET http://localhost:8081/api/v1/weather?city=Bucuresti,Cluj-Napoca,Focsani,Oradea