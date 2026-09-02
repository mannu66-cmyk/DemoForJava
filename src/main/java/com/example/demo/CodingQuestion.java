package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CodingQuestion {
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

    public static int[] productExceptself(int[] nums){
        int res[] = new int[nums.length];
        res[0]=1;int right=1;
        for(int i=1;i<nums.length;i++){
            res[i]=res[i-1]*nums[i-1];
        }

        for(int i=nums.length-1;i>=0;i--){
            res[i]*=right;
            right*=nums[i];
        }
        return res;
    }
    public static int[] productExceptselfNewVersion(int[] nums){
        int res1[] = new int[nums.length];
        int  res2[]  = new int[nums.length];
        int product =1;
        for(int i=0;i<nums.length;i++){
            product *= nums[i];
            res1[i]=product;
        }
        product =1;
        for(int i=nums.length-1;i>=0;i--){
            product *= nums[i];
            res2[i]=product;

        }

        for(int i=0;i<nums.length;i++){
            int pre=i-1>0?res1[i-1]:1;
            int post= i+1<nums.length?res2[i+1]:1;
            nums[i]=pre*post;

        }
        return nums;
    }
    public static List<String> fullJustify(String words[], int maxWidth){
            List<String> result = new ArrayList<>();

            int i = 0;
            while (i < words.length) {
                int j = i;
                int length = 0;
                // Find words that fit in one line
                while (j < words.length &&
                        length + words[j].length() + (j - i) <= maxWidth) {
                    length += words[j].length();
                    j++;
                }
                StringBuilder line = new StringBuilder();
                int wordCount = j - i;
                int spaces = maxWidth - length;
                // Last line OR single word
                if (j == words.length || wordCount == 1) {
                    for (int k = i; k < j; k++) {
                        line.append(words[k]);
                        if (k < j - 1) {
                            line.append(" ");
                        }
                    }
                    // Remaining spaces at end
                    while (line.length() < maxWidth) {
                        line.append(" ");
                    }
                } else {
                    int gaps = wordCount - 1;
                    int normalSpaces = spaces / gaps;
                    int extraSpaces = spaces % gaps;
                    for (int k = i; k < j; k++) {
                        line.append(words[k]);
                        if (k < j - 1) {
                            // Add normal spaces
                            for (int s = 0; s < normalSpaces; s++) {
                                line.append(" ");
                            }
                            // Add extra space to left gaps
                            if (extraSpaces > 0) {
                                line.append(" ");
                                extraSpaces--;
                            }
                        }
                    }
                }
                result.add(line.toString());
                i = j;
            }

            return result;
        }
    public static boolean isBalanced(String s) {

        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            }
            else if (c == ')' || c == '}' || c == ']') {

                // No opening bracket
                if (st.isEmpty()) return false;
                char top = st.peek();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }

                // Pop matching opening bracket
                st.pop();
            }
        }

        // Balanced if stack is empty
        return st.isEmpty();
    }

    public static void main(String[] args) {
        CodingQuestion solver = new CodingQuestion();

        // Test Case 1: 3 distinct islands
        char[][] grid1 = {
                {'1', '1', '1', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'1', '0', '0', '0', '0'},
                {'1', '1', '0', '0', '1'}
        };
        System.out.println("Test Case 1 Expected: 2, Actual: " + solver.numIslands(grid1));
        System.out.println(Arrays.toString(productExceptself(new int[]{1,2,3,4})));
        System.out.println(Arrays.toString(productExceptselfNewVersion(new int[]{1,2,3,4})));
        System.out.println(isBalanced("()[]{}"));
        System.out.println(fullJustify(new String[]{"This","is","an","example","of","text","justification"},16));
}

}
