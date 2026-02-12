import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] dist;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int sx, int sy) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx,sy));
        visited[sy][sx] = true;
        dist[sy][sx] = 0;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x; int y = p.y;

            for(int i =0;i<4;i++) {
                int nx = x + dx[i]; int ny = y + dy[i];

                // 지도 밖,
                if(nx >= m || nx < 0 || ny >= n || ny < 0) continue;
                // 갔던 길,
                if(visited[ny][nx] == true) continue;
                // 벽,
                if(graph[ny][nx] == 0) {
                    continue;
                }

                dist[ny][nx] = dist[y][x] + 1;
                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int gx = 0;
        int gy = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];
        dist = new int[n][m];

        // 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 2) {
                    gx = i; gy = j;
                }
                dist[i][j] = -1;
            }
        }

        bfs(gy,gx);
        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) dist[i][j] = 0;
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

    }
}
