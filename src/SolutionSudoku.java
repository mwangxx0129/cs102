public class SolutionSudoku {
    public static void main(String[] args) {
        SolutionSudoku test = new SolutionSudoku();
        char[][] board = {
                {'.','8','7','6','5','4','3','2','1'},
                {'2','.','3','.','.','.','.','.','.'},
                {'3','.','.','.','.','.','.','.','.'},
                {'4','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','.','.'},
                {'6','.','.','.','.','.','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'8','.','.','.','.','.','.','.','.'},
                {'9','.','.','.','.','.','.','.','.'}
        };
        boolean res = test.isValidSudoku(board);
        System.out.println(res);
    }
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i<9; i++) {
            boolean[] rows = new boolean[9];
            boolean[] cols = new boolean[9];
            boolean[] cube = new boolean[9];
            for (int j = 0; j < 9;j++) {
                if(board[i][j]!='.' && seen(rows, board[i][j]))
                    return false;
                if(board[j][i]!='.' && seen(cols, board[j][i]))
                    return false;
                int cubeRow = 3*(i/3) + j/3;
                int cubeCol = 3*(i%3) + j%3;
                if(board[cubeRow][cubeCol]!='.' && seen(cube, board[cubeRow][cubeCol]))
                    return false;
            }
        }
        return true;
    }
    private boolean seen(boolean[] input, char index) {
        index -= '1';
        boolean res = input[index];
        input[index] = true;
        return res;
    }
}
