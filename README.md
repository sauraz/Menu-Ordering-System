## Menu Ordering System

Create a system that takes orders for breakfast, lunch, and dinner.

## Installation
1. Clone repository from the given github [Repository Link](https://github.com/sauraz/Menu-Ordering-System.git
   ) or extract zip file.
2. Change directory to the path of project "cd Menu Ordering System/src/main/java/org/menu".
3. Generate the Class Files of all java files using the command "javac Main.java".
4. Execute the program using the command "java Main".


### Rules:
1. An order consists of a meal and collection of comma separated item Ids.
2. The system should return the name of the items ordered
3. The system should always return items in the following order: meal, side, drink
4. If multiple items are ordered, the number of items should be indicated
5. Each order must contain a main and a side
6. If no drink is ordered, water should be returned
7. At breakfast, multiple cups of coffee can be ordered
8. At lunch, multiple sides can be ordered
9. At dinner, dessert must be ordered
10. At dinner, water is always provided


## Design Patterns Used
- Facade Pattern
- Factory Pattern
- Iterator Pattern
- Bridge Pattern

## Simple UML Diagram :
![Alt text](https://github.com/sauraz/Menu-Ordering-System/blob/master/src/main/resources/UML_Simple.png)


## Detailed UML Diagram :
![Alt text](https://github.com/sauraz/Menu-Ordering-System/blob/master/src/main/resources/UML_Detailed.png)
