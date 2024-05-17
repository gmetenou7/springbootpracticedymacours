# Étape de construction
# Utilise une image Maven avec JDK 17 pour construire le projet
FROM maven:3.8.4-openjdk-17 as builder

# Copie les fichiers de configuration Maven
COPY pom.xml /build/pom.xml

# Copie les sources de l'application
COPY src /build/src

# Définit le répertoire de travail pour toutes les commandes suivantes
WORKDIR /build
# Pré-téléchargement des dépendances pour améliorer l'utilisation du cache
RUN mvn dependency:go-offline

# Construit l'application et crée le fichier .jar dans le dossier target
RUN mvn clean package

# Étape de l'exécution
# Utilise l'image de base JDK 17 Alpine pour l'exécution
FROM openjdk:17-jdk-alpine

# Information du mainteneur
LABEL maintainer="dev.it@dookecorporation.com"

# Ajoute un volume (optionnel, peut être omis si non nécessaire)
VOLUME /tmp

# Expose le port sur lequel l'application s'exécute
EXPOSE 8080

# Copie le fichier .jar du constructeur à l'image
COPY --from=builder /build/target/*.jar /cours/backend_dooke_erp.jar

# Point d'entrée pour démarrer l'application Java
ENTRYPOINT ["java","-jar","/cours/backend_dooke_erp.jar"]