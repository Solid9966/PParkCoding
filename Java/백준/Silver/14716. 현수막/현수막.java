import java.util.*;
import java.io.*;

public class Main {
    static int M,N;
    static int[][] graph;
    static boolean[][] visited;
    static int answer =0 ;

    static int[] dx = {-1,1,0,0,-1,1,-1,1};
    static int[] dy = {0,0,-1,1,-1,-1,1,1};

    static class Point {
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(int sx,int sy) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx,sy));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x; int y = p.y;

            for(int i = 0; i < 8; i++) {
                int nx = x + dx[i]; int ny = y + dy[i];

                //현수막 바깥,
                if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
                // 카운트한 현수막,
                if(visited[ny][nx]) continue;
                // 현수막 x,
                if(graph[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[M][N];
        visited = new boolean[M][N];

        // 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs logic
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j] == false && graph[i][j] == 1){
                    answer += bfs(j,i);
                }
            }
        }

        System.out.println(answer);

    }
}
