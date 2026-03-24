# Guide d'installation et de mise en route du projet Race API

 installer et configurer le projet Race API  par étape

## Prérequis

Avant de commencer, assurez-vous d'avoir installé sur votre machine :

- Docker Desktop 
- Java 25 
- Maven 
- Git 

## 1. Récupérer le projet

1. Rendez-vous sur le dépôt GitHub du projet (ou votre fork si vous en avez un).
2. Cliquez sur le bouton "Code" puis copiez l'URL du dépôt.
3. Ouvrez un terminal et exécutez la commande suivante pour cloner le projet :

   ```bash
   git clone <URL_DU_DEPOT>
   ```

4. Accédez au dossier du projet :

   ```bash
   cd race-api
   ```

## 2. Lancer la base de données et Adminer

1. Vérifiez que Docker Desktop est bien lancé.
2. Dans le dossier du projet, exécutez :

   ```bash
   docker compose up -d
   ```

   Cela démarre la base PostgreSQL et Adminer.

3. Pour accéder à Adminer (interface web de gestion de la base), ouvrez votre navigateur à l'adresse :

   http://localhost:8081

   Utilisez les paramètres suivants pour vous connecter :

   - Système : PostgreSQL
   - Serveur : race_postgres
   - Utilisateur : race
   - Mot de passe : race
   - Base de données : race_db

## 3. Lancer l'application Java

1. Toujours dans le dossier du projet, lancez l'application avec Maven :

   ```bash
   mvn spring-boot:run
   ```

2. L'API sera alors accessible à l'adresse suivante :

   http://localhost:8080

## 4. Tester l'API

Vous pouvez utiliser Postman pour tester les endpoints suivants :

### Gestion des coureurs
- **GET /runners** : liste des coureurs
- **GET /runners/{id}** : récupérer un coureur par son identifiant
- **POST /runners** : ajouter un coureur
- **PUT /runners/{id}** : modifier un coureur
- **DELETE /runners/{id}** : supprimer un coureur
- **GET /runners/{runnerId}/races** : lister les courses d'un coureur

### Gestion des courses
- **GET /races** : liste des courses (filtrage possible par location)
- **GET /races/{id}** : récupérer une course par son identifiant
- **POST /races** : ajouter une course
- **GET /races/{raceId}/participants/count** : nombre de participants à une course

### Gestion des inscriptions
- **POST /races/{raceId}/registrations** : inscrire un coureur à une course
- **GET /races/{raceId}/registrations** : lister les participants d'une course

N'hésitez pas à consulter le code source pour plus de détails sur les paramètres attendus et les formats de réponse.

## 5. Arrêter les services

Pour arrêter la base de données et Adminer, exécutez :

```bash
docker compose down
```

Pour arrêter l'application Java, faites simplement Ctrl+C dans le terminal où elle tourne.

