# Lascaux - Cinemille

## Requisiti

- Java 17+
- Node.js 20+ e npm
- PostgreSQL o Docker
- Maven

## Configurazione del Backend

### Configurazione del Database

Il backend utilizza variabili d'ambiente per la connessione al database. Questo permette di committare nel repository pubblico le configurazioni nell'`application.properties`

Ci sono due modi per configurare il database:

#### 1. Utilizzo di Docker Compose

è presente un file `compose.yaml` nella cartella `backend` per avviare un container PostgreSQL in locale.:

```yaml
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=cinemille'
      - 'POSTGRES_PASSWORD=cinepassword'
      - 'POSTGRES_USER=cineuser'
    ports:
      - '5432'
```

Per avviare il container:

```bash
cd backend
docker-compose up -d
```

#### 2. Utilizzo del database già esistente hostato da me

è preferibile fare in questo modo. le informazioni di connessione saranno inviate per email

### Configurazione delle Variabili d'Ambiente

Il file `application.properties` utilizza variabili d'ambiente per la configurazione:

```properties
...
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
...
```

Puoi avviare il backend in 2 modi:

1. **Modificare direttamente le variabili d'ambiente** nel file `application.properties` con le credenziali del DB già esistente (dati di prova presenti) fornite via email, oppure con le credenziali indicate nel docker compose per il database locale (il database però sarà vuoto in questo caso)

2. **Impostare le variabili d'ambiente** nel tuo sistema:

   ```bash
   # linux
   export DB_URL=jdbc:postgresql://localhost:5432/cinemille
   export DB_USER=cineuser
   export DB_PASSWORD=cinepassword

   # windows
   set DB_URL=jdbc:postgresql://localhost:5432/cinemille
   set DB_USER=cineuser
   set DB_PASSWORD=cinepassword

   ```


## Avvio del Frontend

```bash
cd frontend
npm install
ng serve
```

## Presentazione

[Link](https://filippo-mancinelli.github.io/cinemille-docs/) alla presentazione del lavoro svolto
