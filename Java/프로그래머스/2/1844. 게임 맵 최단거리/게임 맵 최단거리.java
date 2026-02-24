import java.util.*;

class Solution {
    
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int bfs(int sx,int sy,int M, int N,int[][]maps) {
        Queue<Point> q = new ArrayDeque<>();
        visited[sy][sx] = true;
        q.add(new Point(sx,sy));
        
        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x; int y = p.y;
            
            for(int i = 0; i< 4; i++){
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                
                // 맵 바깥,
                if(nx >= M || nx < 0 || ny >= N || ny < 0) continue;
                // 왔던길,
                if(visited[ny][nx] == true) continue;
                // 막힌길
                if(maps[ny][nx] == 0) continue;
                
                dist[ny][nx] = dist[y][x] + 1;
                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
        return dist[N-1][M-1]+1;
    }
    
    public int solution(int[][] maps) {
        int M = maps[0].length; // x
        int N = maps.length; // y
        int answer = 0;
        
        visited = new boolean[N][M];
        dist = new int[N][M];
        
        answer = bfs(0,0,M,N,maps);
        if (answer == 1) answer = -1;
        
        return answer;
    }
}