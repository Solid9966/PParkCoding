// logic
// 1. v = 늑대, o = 양 : if(oCnt <= vCnt) oCnt = 0;

import java.util.*;
import java.io.*;

public class Main {
    static int R,C;
    static char[][] graph;
    static boolean[][] visited;
    static int[] answer = new int[2];

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point{
        int x,y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int sx,int sy){
        Queue<Point> q = new ArrayDeque<>();
        int oCnt = 0; int vCnt = 0;

        // 양 or 늑대부터 시작시
        if(graph[sy][sx] == 'o') oCnt++;
        else if(graph[sy][sx] == 'v') vCnt++;

        visited[sy][sx] = true;
        q.add(new Point(sx,sy));

        while(!q.isEmpty()){
            Point p = q.poll();
            int x = p.x; int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = dx[i] + x; int ny = dy[i] + y;

                // 마당 바깥,
                if(nx >= C || nx < 0 || ny >= R || ny < 0) continue;
                // 왔던 길,
                if(visited[ny][nx] == true) continue;
                // 울타리,
                if(graph[ny][nx] == '#') continue;
                else if (graph[ny][nx] == 'v') vCnt++;
                else if (graph[ny][nx] == 'o') oCnt++;

                visited[ny][nx] = true;
                q.add(new Point(nx,ny));
            }
        }
        // 늑대 양 합산
        if(oCnt <= vCnt) oCnt = 0;
        else vCnt = 0;

        answer[0] += oCnt;
        answer[1] += vCnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        visited = new boolean[R][C];

        // 초기화
        for(int i =0; i<R; i++){
            String line = br.readLine();
            for(int j =0; j < C; j++){
                graph[i][j] = line.charAt(j);
            }
        }

        // bfs 로직
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(visited[i][j] == false && graph[i][j] != '#') bfs(j, i);
            }
        }

        System.out.println(answer[0] +" "+answer[1]);


    }
}
