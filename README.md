[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/kj3moraes/Battleship-Game-Engine/blob/main/LICENSE)

# Battleship-Game-Engine
This is a text-based PvE game where the player can decide the engine that they would like to go up against. The choices are :
1. `Naive_Solver` - places ships randomly, attacks randomly and does not learn about ships positions
2. `Intermediate_Adversary` - places ships randomly, attacks randomly but remembers ships positions and increases attacks accordingly
3. `Boogeyman` - plays perfectly via the battleship playing algorithm. Shows no mercy


The **Battleship Playing Guide** details how you should interact with the text-based game interface to play it properly.
The documentation for the various parts of the code is found within the markdown files in the respective folders and 
comments in the code. A final PDF will be attached detailing the algorithm implemented for Boogeyman and some aspects
of the code.

## How to Play this Text-based Game
To play this game you will need to download the source code to any folder you like. Open an IDE or a code editor that
can compile and run Java code and compile all the Java files. Then run `Main.java`. This will bring up the console and prompt you with the _ACTION MENU_. 
From then on, follow the Battleship Playing Guide.

**Class Descriptions**
* `Main.java` - houses all the interacting piece. Is the executable file to play the game
* `Ship.java` - class containing the specifications of the Ships used and its respective position on the Battlefield
* `Battlefield.java`- class that provides the playable arena
* `Player.java` - abstract class outlining how a player should play
    * `Human.java` - a human player 
    * `BattleshipEngine.java` - a machine player abstract class
        * `Naive_Solver.java` - the easy engine
        * `Intermediate_Adversary,java` - the medium engine   
        * `Boogeyman.java` - the hard engine

This repository is licensed under the MIT License. Click on the license bade or go to the LICENSE file for terms and conditions.
