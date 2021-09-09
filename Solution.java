public class Solution {

    Point[][] matrix;
    int matrixSide;

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        if (mines.length == n * n) {
            return 0;
        }

        this.matrixSide = n;
        initializeMatrix(mines);
        findCulumativeOnes_upAndLeft();
        findCulumativeOnes_downAndRight();

        return search_orderOLargestPlusSign();
    }

    public void initializeMatrix(int[][] mines) {

        matrix = new Point[matrixSide][matrixSide];
        for (int r = 0; r < matrixSide; r++) {
            for (int c = 0; c < matrixSide; c++) {
                matrix[r][c] = new Point(1);
            }
        }

        for (int i = 0; i < mines.length; i++) {
            int r = mines[i][0];
            int c = mines[i][1];
            matrix[r][c].pointValue = 0;
        }
    }

    public void findCulumativeOnes_upAndLeft() {
        for (int r = 0; r < matrixSide; r++) {
            for (int c = 0; c < matrixSide; c++) {
                if (matrix[r][c].pointValue == 1) {
                    if (r - 1 >= 0) {
                        matrix[r][c].up = matrix[r - 1][c].up + matrix[r - 1][c].pointValue;
                    }
                    if (c - 1 >= 0) {
                        matrix[r][c].left = matrix[r][c - 1].left + matrix[r][c - 1].pointValue;
                    }
                }
            }
        }
    }

    public void findCulumativeOnes_downAndRight() {
        for (int r = matrixSide - 1; r >= 0; r--) {
            for (int c = matrixSide - 1; c >= 0; c--) {
                if (matrix[r][c].pointValue == 1) {
                    if (r + 1 < matrixSide) {
                        matrix[r][c].down = matrix[r + 1][c].down + matrix[r + 1][c].pointValue;
                    }
                    if (c + 1 < matrixSide) {
                        matrix[r][c].right = matrix[r][c + 1].right + matrix[r][c + 1].pointValue;
                    }
                }
            }
        }
    }

    public int getOrderOfPlusSign(Point p) {
        return Math.min(Math.min(p.left, p.right), Math.min(p.up, p.down)) + 1;
    }

    public int search_orderOLargestPlusSign() {
        int orderOLargestPlusSign = 0;
        for (int r = 0; r < matrixSide; r++) {
            for (int c = 0; c < matrixSide; c++) {
                if (matrix[r][c].pointValue == 1) {
                    orderOLargestPlusSign = Math.max(orderOLargestPlusSign, getOrderOfPlusSign(matrix[r][c]));
                }
            }
        }
        return orderOLargestPlusSign;
    }
}

class Point {

    int pointValue;
    int left;
    int right;
    int down;
    int up;

    public Point(int pointValue) {
        this.pointValue = pointValue;
    }
}
