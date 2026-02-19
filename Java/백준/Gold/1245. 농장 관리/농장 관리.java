// logic
// 산봉우리?
// 1. h = (i,j) && visited = false 일때 Queue에 저장
// 2. nx > x 이면, isPeak = false
// 3. 순횐 끝일 경우, isPeak = true 이면, answer++

import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] graph;
    static boolean[][] visited;
    static int answer = 0;

    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1,-1,1};

    static class Point {
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean bfs(int sx, int sy, int h) {
        Queue<Point> q = new ArrayDeque<>();
        boolean isPeak = true;

        q.add(new Point(sx, sy));
        visited[sy][sx] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x; int y = p.y;

            for(int i =0; i<8; i++){
                int nx = dx[i] + x; int ny = dy[i] + y;

                // 농장 밖,
                if(nx >= M || nx < 0 || ny >= N || ny < 0) continue;
                // 왔던 길,
                if(graph[ny][nx] > h) isPeak = false;
                // 산봉우리 확장,
                if(graph[ny][nx] == h && visited[ny][nx] == false) {
                    visited[ny][nx] = true;
                    q.add(new Point(nx,ny));
                }
            }

        }
        return isPeak;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        // 초기화
        for (int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<M;j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs 로직,
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int h = graph[i][j];
                if (visited[i][j] == true) continue;

                if (bfs(j,i,h) == true) answer++;
            }
        }

        System.out.println(answer);

    }
}
