# DekhoJob Clone (Backend)

A LinkedIn-style backend built with Spring Boot 4, starting as a modular
monolith and evolving into microservices on AWS.

## Tech stack
Java 25 · Spring Boot 4 · PostgreSQL 17 · Liquibase · Docker

## Run locally
1. Copy `.env.example` to `.env`
2. `docker compose up -d`
3. Run `DekhojobApplication`
4. Check `http/actuator.http`