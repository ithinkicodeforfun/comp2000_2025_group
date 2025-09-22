# Pixel Crossy Road – COMP2000 Assignment 1 by Isaac Hazell

## How to Compile & Run
1. Unzip the repository
2. Open in your IDE (Java 11 or Java 21)
3. Compile and run `Main.java`  
4. Use the arrow keys to move the player and collect coins

---

## Inheritance
I used inheritance in Tile, Vehicle, and Actor to extend, reuse, and avoid repetition

- Tile is a superclass for Grass and Road. It contains the shared drawing logic, and each subclass only sets its colour. This avoids repetitive code

- Vehicle is a base class that defines movement and sets up the structure for future vehicle types

- Actor is a superclass for dynamic objects (Player, Coin), so they can reuse the same location and drawing logic

This makes the code easier to extend and maintain because common behaviour is shared from the parent and reused in sub classes

---

## Generics
I used generics in the `Car<T>` class for vehicle types

Right now, I only pass a String type (`Car`, `Truck`, `Bus` or `Mini`), which avoids repeating the properties of each vehicle type when spawning cars

In the future, I could make better use of generics by passing a class to include more properties and data types such as colour, speed, and size

Using generics makes the class more flexible and reusable because the Car class is not tied to one data type

---