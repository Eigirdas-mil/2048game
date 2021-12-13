package unitTests;
import com.example.kursinis2048.Board;
import org.junit.Test;
import org.junit.Assert;

public class unitTest {

    @Test
    public void playerCanMove() {
        //Arrange
        var board = new Board();
        int[][] boardNumbers = new int[board.getBoardSize()][board.getBoardSize()];
        //Act

        assignValuesToTiles(board, boardNumbers, 2);
        board.setBoard(boardNumbers);
        var result = board.canMove();
        //Assert
        Assert.assertTrue(result);
    }

    private void assignValuesToTiles(Board board, int[][] boardNumbers, int i2) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                boardNumbers[i][j] = i2;
            }
        }
    }

    @Test
    public void isWinner() {
        //Arrange
        var board = new Board();
        int[][] boardNumbers = new int[board.getBoardSize()][board.getBoardSize()];
        //Act
        assignValuesToTiles(board, boardNumbers, 2048);
        board.setBoard(boardNumbers);
        var result = board.isWinner();
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void boardIsFull() {
        //Arrange
        var board = new Board();
        //Act
        int[][] boardNumbers = new int[board.getBoardSize()][board.getBoardSize()];
        assignValuesToTiles(board, boardNumbers, 24);
        board.setBoard(boardNumbers);
        var result = board.isFull();
        //Assert
        Assert.assertTrue(result);
    }

}
