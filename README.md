# DreamTrap

Rewrite of the L2 semester project for the *Algorithmique et programmation* course.

## Requirements

- JDK 25 or later
- Maven 3.x (or the Maven bundled with your IDE)

Check your setup with `mvn -v`: the `Java version` line must show 25 or later.

## Build

```bash
mvn clean package
```

The compiled classes are generated in `target/classes` and the jar in `target/`.

## Run

```bash
java -cp target/classes fr.univtours.dreamtrap.Main
```

## Open in an IDE

Import the project as a **Maven project**. The IDE configuration is generated from `pom.xml`.

## Project structure

```
src/main/java/   Java sources (base package fr.univtours.dreamtrap)
pom.xml          Build configuration (Java version, dependencies)
```