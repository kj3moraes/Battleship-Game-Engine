# Hunt and Target Method Implementation
This describes the hunt and target method of firing as implemented in `IntermediateAdversary.java`.
The `Boogeyman.java` class also borrows some strategies from this method.

This Hunt-and-Target implementation is borrowed from steveec9's repository for Battleship (see References). Like
that implementation, the method revolves around using 2 lists and a stack: `targets` for mapping out the targets, 
`hunted` for finding the hunting shots and `priming` for Target mode. The implementations differ in our encodings of the
Battlefield as well as sp


## References
[1] [BattleShip-ClientSim repository by steveec9](https://github.com/steveec9/BattleShip-ClientSim)  
[2] [Battleship Playing Strategies](https://datagenetics.com/blog/december32011/index.html)  