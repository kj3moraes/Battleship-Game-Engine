# Battleship Playing Guide
This is a PvE style Battleship game that allows the user to choose which Engine they want to go up against. The interface
is text-based, so you will need an IDE or code editor to run this in. 

---
## How-to-Play
This guide instructs the reader on how to use the Battleship text-based game engine. The rules of the game are the same as the official 
version given by [Hasbro](https://www.hasbro.com/common/instruct/Battleship.pdf). At the start of the program, you will need to 
give some details for the game to take place.

* Menu 1 _[ACTION MENU]_
  * `[s]tart` - starts the **Human vs. Machine** match
  * `e[x]it` - terminates the program
  
The user should type in the appropriate letter - `s` or `x` to perform the respective action.
Following the ACTION MENU, you will be prompted to type your name. This will be followed by 

* Menu 2 (after `start`) _[ENGINE SELECTION MENU]_
  * `[N]aive-Solver` - easy adversary
  * `[I]ntermediate-Adversary` - challenging but not invincible. (equivalent to another human)
  * `[B]oogeyman` - plays perfectly with no mercy
  
Type any one of the above letters (uppercase or lowercase is finem) to access that engine to play with. If any other letter is typed then the 
appropriate error message is displayed and the menu is shown again. 

### Game Starts
Once again this consists of two parts - Setup and Wartime. The Human will always go as Player 1. However, if the user
wants they are free to modify the source code in the `Battleship.java` file to switch up the Players or add a menu.
Player 2's actions are completely automated (obviously). 

#### Setup
During setup, Player 1 is prompted first to place his/her ships on the battlefield. When prompted to do so a picture of the current 
battlefield arrangement will be shown and the user must type the first coordinate on the first line and the second coordinate on the second line.
* The Row letter must be followed by the Column number with no gaps between them
* Type the first coordinates and **press enter** and then type the second coordinates.
  (Remember to calculate the position distance. The battlefield is printed to assist with this.)
* Only horizontal and vertical placements are legal

Remember to examine the length of the ship and the corresponding coordinates. _(You can't fit a Battleship (4 cells) in a space of 3 cells).
For example:

```
 1 2 3 4 5 6 7 8 9 10 
A O O O O O ~ ~ ~ ~ ~ 
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 


Enter the coordinates of the Battleship (4 cells): 
D9
G9

  1 2 3 4 5 6 7 8 9 10 
A O O O O O ~ ~ ~ ~ ~ 
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
D ~ ~ ~ ~ ~ ~ ~ ~ O ~ 
E ~ ~ ~ ~ ~ ~ ~ ~ O ~ 
F ~ ~ ~ ~ ~ ~ ~ ~ O ~ 
G ~ ~ ~ ~ ~ ~ ~ ~ O ~ 
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 


Enter the coordinates of the Submarine (3 cells): 

```

An appropriate error message will be displayed if there is something wrong with the input. This occurs when :
* The specified coordinates are too small or too large for the ship
* The coordinates are invalid (eg. letters > J or numbers outside the 1 to 10 range)
* The ships are touching each other (only touching diagonally is legal).
* The ships are crossing (i.e the coordinates of the placement overlap with the position of a pre-existing ship) 

Once Player 1 has completed setup, **double press the Enter key** and Player 2 (Engine) will automatically place its
ships. A message will be displayed indicating that Player 2 has completed the Setup and that War is about to begin

#### Wartime
For Wartime, Player 1 wil go first. Player 1 will be shown Player 2's "Fog of War" (FOW) screen at the top and 
they have to guess where they want to fire. Under P2 FOW is a divider and P1's own ship layout. 
P1 will guess where to fire and if they hit a ship they will see that part marked with the HIT character whether 
that be X or (any character that is designated by the programmer). 

This is then followed by an Enter prompt which generated as large amount of space and then Player 2 will play. 
The following example denotes a possible scenario:


```
  1 2 3 4 5 6 7 8 9 10 
A X ~ ~ ~ ~ ~ ~ ~ ~ ~ 
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 

--------------------
  1 2 3 4 5 6 7 8 9 10 
A X O O O O ~ ~ ~ ~ O 
B ~ M ~ ~ ~ ~ ~ O ~ O 
C ~ ~ ~ ~ ~ ~ ~ O ~ O 
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
G O O O ~ ~ ~ ~ ~ ~ ~ 
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
I O O O O ~ ~ ~ ~ ~ ~ 
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 

Player 1, it's your turn:
a2

  1 2 3 4 5 6 7 8 9 10 
A X X ~ ~ ~ ~ ~ ~ ~ ~ 
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 

You hit a ship! 
Press Enter and pass the move to another player
```
Keep in mind that there will be a message displayed at the end of P2's play to indicate whehter the engine hit a ship or
missed. This will be clearly displayed for P1 to view as well. The game goes on until one players sinks all the ships
of the other player. The appropriate celebratory message with the winning player is displayed and then the game stops.

---

## References 
[1] [What is the best strategy to win in the famous board game Battleship?](https://www.quora.com/What-is-the-best-strategy-to-win-in-the-famous-board-game-Battleship)  
[2] Crombez, L., da Fonseca, G., & Gerard, Y. (2012). Efficient Algorithms for Battleship. _ACM_. Published. https://arxiv.org/pdf/2004.07354.pdf  
[3] [Algorithm for positioning ships in Battleship game](https://stackoverflow.com/questions/10842571/algorithm-for-positioning-ships-in-battleship-game)  
[4] [Battleship Playing Strategies](https://datagenetics.com/blog/december32011/index.html)  
[5] Audinot, M., Bonnet, F., & Viennot, S. (2014). Optimal Strategies against a Random Opponent in Battleship. The 19th Game Programming Workshop 2014. Published. [https://ipsj.ixsq.nii.ac.jp/ej/](https://ipsj.ixsq.nii.ac.jp/ej/?action=repository_action_common_download&item_id=106500&item_no=1&attribute_id=1&file_no=1)
