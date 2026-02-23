// logic
// 1. 상하-> if (ny >= N) ny & N; 좌우-> if (nx >= M) nx % M;

import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] graph;
    static boolean[][] visited;
    static int answer = 0;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point{
        int x,y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(int sx,int sy){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx, sy));
        visited[sy][sx] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                // 지구 도넛썰 반영,
                // 1. 상하
                if (ny >= N || ny < 0) ny = (ny+N) % N;
                // 2. 좌우
                if (nx >= M || nx < 0) nx = (nx+M) % M;

                // 왔던길,
                if(visited[ny][nx] == true) continue;
                // 숲,
                if(graph[ny][nx] == 1) continue;

                visited[ny][nx] = true;
                q.add(new Point(nx, ny));

            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];


        // 초기화
        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs 로직
        for(int i =0; i<N; i++){
            for(int j =0; j< M; j++){
                if(graph[i][j] == 0 && visited[i][j] == false){
                    answer += bfs(j, i);
                }
            }
        }

        System.out.println(answer);

    }
}
