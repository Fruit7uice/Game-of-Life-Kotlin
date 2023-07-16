import javax.swing.JButton
import javax.swing.JFrame


fun main(args: Array<String>) {

    val M = 10;
    val N = 10;

    var grid : Array<Array<Int>> = Array(M) { i -> Array(M){j -> 0}}

    displayGrid(grid)
    println()
    randomizeLife(grid, 50)
    displayGrid(grid)


    while(true){
        displayGrid(grid)
        nextGeneration(grid, M, N);
        println()
        println()
        Thread.sleep(500)

    }

     




}



/**
 * Randomizes a place in the given matrix and creates cells
 * Currently only works for mutable arrays!
 */
fun randomizeLife(grid: Array<Array<Int>>, amount : Int) {
    var i = 0;

    while(i < amount){
        var n : Int = Math.floor(Math.random() * (grid.size - 1)).toInt()
        var m : Int = Math.floor(Math.random() * (grid[0].size - 1)).toInt()
        grid[n][m] = 1;
        i++
    }

}


fun displayGrid(grid : Array<Array<Int>>){
    var str : String
    for (i in grid){
        for (j in i){
            str = if(j == 0) "." else "*";
            print(str + "  ")
        }
        println()
    }

}

fun nextGeneration(grid : Array<Array<Int>>, M : Int, N : Int){

    var neighbours : Int;
    //print(getNeighbours(grid, 1, 2))
    for (x in 0..M-1){
        //println("X: $x")
        for (y in 0..N-1){
            var cCell : Int = grid[x][y];
            neighbours = getNeighbours(grid, x, y);
            if (cCell == 1 && neighbours < 2) grid[x][y] = 0;
            else if (cCell == 1 && (neighbours == 2 || neighbours == 3)) grid[x][y] = 1
            else if (cCell == 1 && neighbours > 3) grid[x][y] = 0;
            else if (cCell == 0 && neighbours == 3) grid[x][y] = 1;
        }
    }
}

fun getNeighbours(grid : Array<Array<Int>>, x : Int, y: Int): Int {
    var count : Int = 0;
    for(i in -1..1){// X-Cords
        //println(i)

        for (j in -1..1){ // Y-Cords
            //println(j)
            if(i == 0 && j == 0) continue
            else {
                if ((x+i > 0 && x+i < grid[0].size) && (y+j > 0 && y+j < grid.size)){
                    if (grid[x+i][y+j] == 1) count++

                }
            }
        }
    }
    return count;
}