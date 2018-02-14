# Conway's Game of Life

_Please note this is just a wikipedia extract_


https://en.wikipedia.org/wiki/Conway's_Game_of_Life


The Game of Life, also known simply as Life, is a cellular automaton devised by the British
 mathematician John Horton Conway in 1970.

The "game" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further 
input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves, or, for 
advanced "players", by creating patterns with particular properties. The Game has been reprogrammed multiple times in
various coding languages.

**_Rules_**

* Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
* Any live cell with two or three live neighbours lives on to the next generation.
* Any live cell with more than three live neighbours dies, as if by overpopulation.
* Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying 
the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously,
and the discrete moment at which this happens is sometimes called a tick (in other words, each
generation is a pure function of the preceding one). The rules continue to be applied repeatedly 
to create further generations.

**_Instruction on how to execute_**

mvn exec:java -Dexec.args="file///c:/filename.txt"

**_For example if the file would be located on the file system (Windows)_**

mvn exec:java -Dexec.args="file:///e:/Home/input1-4x8.txt"