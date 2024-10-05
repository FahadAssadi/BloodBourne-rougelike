# FIT2099 Assignment (Semester 2, 2023)

## Designborne

<a href="https://docs.google.com/spreadsheets/d/1zjXU3azMD6O6OAXELd0CsCh4tRDg_cME6b0z_gg6msk/edit#gid=846598609">Contribution Log</a>

## Table of Contents
- [Introduction](#introduction)
- [Game Engine Overview](#game-engine-overview)
- [OOP Design Patterns](#oop-design-patterns)
- [How to Run the Game](#how-to-run-the-game)

## Introduction
Designborne is a text-based adventure game developed as part of the FIT2099 assignment for Semester 2, 2023. The game features various actors, actions, and items that interact within a game world.

## Game Engine Overview
The game engine is designed to manage the game world, including the locations of all actors, the player, and the playing grid. Below are the key components of the game engine:

### World
The [`World`](src/edu/monash/fit2099/engine/positions/World.java) class represents the game world. It manages the game maps, actors, and the game loop.

### GameMap
The `GameMap` class represents a single map in the game world. It contains locations and manages the placement of actors and items.

### Actor
The `Actor` class represents any entity that can perform actions in the game world. This includes the player and various enemies.

### Item
The [`Item`](src/edu/monash/fit2099/engine/items/Item.java) class represents physical objects in the game world that actors can interact with.

### Actions
Actions are tasks that actors can perform. Examples include moving, attacking, and picking up items. The [`Action`](src/edu/monash/fit2099/engine/actions/Action.java) class is the base class for all actions.

## OOP Design Patterns
The game engine utilizes several Object-Oriented Programming (OOP) design patterns to ensure modularity, reusability, and maintainability.

### Factory Pattern
The `FancyGroundFactory` class is an example of the Factory Pattern. It is used to create instances of different types of ground in the game world.

### Strategy Pattern
The `Behaviour` interface and its implementations (e.g., `AttackBehaviour`, `FollowBehaviour`, `WanderBehaviour`) demonstrate the Strategy Pattern. Different behaviors can be assigned to actors to define their actions.

### Singleton Pattern
The `EntityManager` class follows the Singleton Pattern to ensure that there is only one instance managing the game entities.

### Observer Pattern
The game engine uses the Observer Pattern to manage the interaction between actors and items. For example, the `Item` class can notify actors of changes in its state.

### Command Pattern
The `Action` class and its subclasses (e.g., `MoveActorAction`, `AttackAction`) implement the Command Pattern. Each action represents a command that can be executed by an actor.

## How to Run the Game
To run the game, follow these steps:

1. Clone the repository:
    ```sh
    git clone <repository-url>
    ```

2. Navigate to the project directory:
    ```sh
    cd <project-directory>
    ```

3. Compile the project:
    ```sh
    javac -d bin src/**/*.java
    ```

4. Run the game:
    ```sh
    java -cp bin game.Application
    ```
