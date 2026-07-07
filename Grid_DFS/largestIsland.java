import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[][] grid = new int[n][n];
		for (int i = 0; i < n; i++){
		    for (int j = 0; j < n; j++){
		        grid[i][j] = s.nextInt(); 
		    }
		}
        System.out.println("ans is " + largestIsland(grid));
	}
    public static int largestIsland(int[][] grid) {
        System.out.println("grid before");  
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 2;
        System.out.println("id is " + id);
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    System.out.println("new island is found");
                    int area = dfs(grid, i, j, id);
                    System.out.println("area is " + area);
                    map.put(id, area);
                    System.out.println("Added to map: id=" + id + ", area=" + area);
                    id++;
                    ans = Math.max(ans, area);
                    System.out.println("current ans " + ans);
                }
            }
        }
        System.out.println("grid after"); 
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 0){
                    System.out.println("zero found at " + i + ", " + j);
                    HashSet<Integer> set = new HashSet<>();
                    int nr = 0;
                    int nc = 0;
                    for (int k = 0; k < 4; k++){
                        nr = i + dr[k];
                        nc = j + dc[k];
                        if(valid(grid,nr,nc)){
                            System.out.println("id is valid");
                           set.add(grid[nr][nc]); 
                           System.out.println(grid[nr][nc] + " added to set");
                        } 
                    }
                    int area = 1;
                    for(int islandId : set){
                    area += map.get(islandId);
                    }
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;

    }
    public static boolean valid(int[][] grid, int r, int c){
        return r>=0 &&
       c>=0 &&
       r<grid.length &&
       c<grid[0].length &&
       grid[r][c] > 1;
    }
    public static int dfs(int[][] grid, int r, int c, int id){
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return 0;
        if (grid[r][c] != 1) return 0;
        grid[r][c] = id;
        int area = 1;
        for (int k = 0; k < 4; k++){
            area += dfs(grid, r + dr[k], c + dc[k], id);
        }
        return area;
    }
}
