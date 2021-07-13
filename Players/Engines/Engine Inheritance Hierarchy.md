# Battleship Engine Hierarchy

                                                     ---------------------
                                                    (      (PLAYER)       )
                                                     ---------------------
                                                              | 
                                                              |
                                                              |
                                   ------------------------------------------------------------
                                   |                                                          |
                                   |                                                          |          
                        ----------------------                                        ---------------
                       (  BATTLESHIP ENGINE  )                                        |    HUMAN    |
                        ----------------------                                        --------------- 
                                   |
                                   |
          --------------------------------------------
          |                                          |
          |                                          |
     ---------                                   -------------
     |  NAIVE |                                  | BOOGEYMAN |
     | SOLVER |                                  |           |
     ---------                                   -------------
         |
         |
         --------------------
                            |
                            |
                    -------------------
                    |   INTERMEDIATE  |
                    |    ADVERSARY    |
                    -------------------


Classes with `(...)` are abstract classes
