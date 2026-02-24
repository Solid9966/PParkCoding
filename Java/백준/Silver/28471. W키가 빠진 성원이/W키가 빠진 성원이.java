import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static char[][] graph;
    static boolean[][] visited;
    static int answer = 0;

    static int[] dx = {1,-1,1,-1,1,0,-1};
    static int[] dy = {1,1,0,0,-1,-1,-1};

    static class Point{
        int x,y;
        public Point(int x,int y){
            this.x=x; this.y=y;
        }
    }

    static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        visited = new boolean[N][N];

        // multu source logic
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(graph[i][j] == 'F') {
                    q.add(new Point(j, i));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x; int y = p.y;

            for(int i = 0; i<7; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                // 게임판 바깥,
                if(nx >= N || nx < 0 || ny >= N || ny < 0) continue;
                // 왔던 길,
                if(visited[ny][nx] == true) continue;
                // 벽,
                if(graph[ny][nx] == '#') continue;

                visited[ny][nx] = true;
                q.add(new Point(nx, ny));
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(graph[i][j] == '.' && visited[i][j] == true){
                    cnt++;
                }
            }
        }
        answer = cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new char[N][N];

        // 초기화
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        bfs();
        System.out.println(answer);

    }
}
