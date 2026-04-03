import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] dist;
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static class Point {
        int x,y;
         public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static void bfs (int sx, int sy, int n, int m, int[][] maps) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx,sy));
        visited[sy][sx] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            
            for (int i = 0; i<4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                
                // 맵 바깐
                if(nx >= n || nx < 0 || ny >= m || ny < 0) continue;
                // 왔던 길
                if(visited[ny][nx] == true) continue;
                // 벽
                if(maps[ny][nx] == 0) continue;
                
                dist[ny][nx] = dist[y][x] + 1;
                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps[0].length; // x
        int m = maps.length; // y
        
        visited = new boolean[m][n];
        dist = new int[m][n];
        
        bfs(0,0,n,m,maps);
        System.out.println(Arrays.deepToString(dist));
        System.out.println(Arrays.deepToString(visited));
        
        if (dist[m-1][n-1] == 0) answer = -1;
        else answer = dist[m-1][n-1] + 1;
            
        return answer;
    }
}