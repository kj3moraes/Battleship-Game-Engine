# Hunt and Target Method Implementation
This describes the hunt and target method of firing as implemented in `IntermediateAdversary.java`.
The `Boogeyman.java` class also borrows some strategies from this method.

This Hunt-and-Target implementation is borrowed from steveec9's repository for Battleship (see References). Like
that implementation, the method revolves around using 2 lists and a stack: `targets` for mapping out the targets, 
`hunted` for finding the hunting shots and `priming` for Target mode. The implementations differ in our encodings of the
Battlefield as well as sp

## Intermediate-Adversary 
The Hunt-and-Target method for this class is an implementation oft= the 'Parity Hunt-and-Target' from the datagenetics
webpage on the subject. There are 2 major functions for this implementation (`huntSquares` and `targetShips`) and 3 
minor functions (`createTargetList`, `encode` and `decode`). 

### `huntSqaures`
This takes place in 3 steps :
1. If the hunting list (`hunts`) is empty (i.e no more squares left for hunt) then go with a. Else go with b.  
    a.  
    b.   
2.   
3.

### `targetShips`
Tho

## Boogeyman
## References
[1] [BattleShip-ClientSim repository by steveec9](https://github.com/steveec9/BattleShip-ClientSim)  
[2] [Battleship Playing Strategies](https://datagenetics.com/blog/december32011/index.html)  