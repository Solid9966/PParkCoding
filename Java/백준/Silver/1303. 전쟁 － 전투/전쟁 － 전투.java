import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static char[][] graph;
    static boolean[][] visited;
    static int Wcnt= 0, Bcnt = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void Wbfs(int sx,int sy) {
        Queue<Point> q = new ArrayDeque<>();
        int cnt = 1;
        q.add(new Point(sx,sy));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 전쟁터밖,
                if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
                // 셋던 병사,
                if(visited[ny][nx]) continue;
                // 다른 팀 병사,
                if(graph[ny][nx] == 'B') continue;

                cnt++;
                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
        Wcnt+= cnt*cnt;
    }

    static void Bbfs(int sx,int sy) {
        Queue<Point> q = new ArrayDeque<>();
        int cnt = 1;
        q.add(new Point(sx,sy));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 전쟁터밖,
                if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
                // 셋던 병사,
                if(visited[ny][nx]) continue;
                // 다른 팀 병사,
                if(graph[ny][nx] == 'W') continue;

                cnt++;
                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
        Bcnt+= cnt*cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[M][N];
        visited = new boolean[M][N];

        // 초기화
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        // logic
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j] == false && graph[i][j] == 'W') {
                    Wbfs(j, i);
                }
                if(visited[i][j] == false && graph[i][j] == 'B') {
                    Bbfs(j, i);
                }
            }
        }

        System.out.println(Wcnt+" "+Bcnt);
    }
}
