package src.domain.classes.kenken;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import src.domain.classes.board.Board;
import src.domain.classes.board.Cell;
import src.domain.classes.board.Group;

public class KenkenSolver {

    /**
     * Solve the given Kenken board by recursively trying all possible values for
     * each cell.
     *
     * @param board     the current state of the Kenken board
     * @param x         the current row index
     * @param y         the current column index
     * @param boardSize the size of the Kenken board
     * @param groups    the map of groups and their constraints
     * @return the solved Kenken board, or null if no solution is found
     */
    public Cell[][] solveBoard(Cell[][] board, int x, int y, int boardSize, Map<Integer, Group> groups) {
        // base case: if both x and y are at or beyond the last cell
        if (x >= boardSize) {
            return board; // return the solved board
        }

        // move to the next cell
        int nextX = x;
        int nextY = y + 1;
        if (nextY >= boardSize) {
            nextX++;
            nextY = 0;
        }

        // if the current cell is already filled, move to the next cell
        if (board[x][y].getValue() != 0) {
            return solveBoard(board, nextX, nextY, boardSize, groups);
        }

        // create a list of possible values to fit in the cell
        ArrayList<Integer> listOfPossibleValues = new ArrayList<>();
        for (int i = 1; i <= boardSize; ++i) {
            listOfPossibleValues.add(i);
        }

        // discard repeated values in the same row and column
        for (int i = 0; i < boardSize; ++i) {
            int rowValue = board[x][i].getValue();
            int colValue = board[i][y].getValue();
            if (rowValue != 0) {
                listOfPossibleValues.remove(Integer.valueOf(rowValue));
            }
            if (colValue != 0) {
                listOfPossibleValues.remove(Integer.valueOf(colValue));
            }
        }

        // discard values that are not valid in the group
        Group group = groups.get(board[x][y].getGroupId());
        for (Integer value : listOfPossibleValues) {
            if (!group.validValue(board[x][y], value, board))
                listOfPossibleValues.remove(value);
        }

        // recursively try all the possible values for this cell
        for (Integer value : listOfPossibleValues) {
            board[x][y].setValue(value);
            Cell[][] result = solveBoard(board, nextX, nextY, boardSize, groups);
            if (result != null) {
                return result; // return the solved board if a solution is found
            }
            // if no solution is found with this value, backtrack
            board[x][y].setValue(0);
        }

        // return null if no solution is found
        return null;
    }

    /**
     * Returns the solution of the kenken if it exists
     * 
     * @param k kenken to solve
     * @return solved kenken if the solution exists, or a kenken with a null board
     *         if no solution exists.
     */
    public Kenken solveKenken(Kenken k) {
        Board board = k.getBoard();
        int gridSize = board.getGridSize();
        board.setGrid(solveBoard(board.getGrid(), 0, 0, gridSize, board.getGroups()));
        k.setBoard(board);
        return k;
    }
}
