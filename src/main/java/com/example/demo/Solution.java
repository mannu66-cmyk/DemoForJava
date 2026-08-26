public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length; // Fixed: Corrected column length
        int islandCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    dfs(grid, r, c);
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length; // Fixed: Corrected column length

        // Base case: check bounds and if cell is water/visited
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0'; // Fixed: Correctly sinking the current cell [r][c]

        // Recurse for all 4 directions
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Test Case 1: 3 distinct islands
        char[][] grid1 = {
                {'1', '1', '1', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '0'},
                {'1', '1', '0', '0', '1'}
        };
        System.out.println("Test Case 1 Expected: 2, Actual: " + solver.numIslands(grid1));

        // Test Case 2: 1 single massive island
        char[][] grid2 = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        System.out.println("Test Case 2 Expected: 1, Actual: " + solver.numIslands(grid2));
    }
}
