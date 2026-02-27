import java.util.*;
import java.io.*;

public class Main {
    static int C,R,K;
    static int[][] graph;
    static int Xanswer = 0, Yanswer = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static void batch(int sx, int sy) {
        int idx = 0;

        for(int i =1; i<=K; i++) {
            graph[sy][sx] = i;

            if(i == K) {Xanswer = sx+1; Yanswer = R-sy; return;}
            int nx = sx + dx[idx]; int ny = sy + dy[idx];

            // 공연장 벽,
            while(nx >= C || nx < 0 || ny >= R || ny < 0 || graph[ny][nx] != 0){
                idx = (idx+1) % 4;
                nx = sx + dx[idx]; ny = sy + dy[idx];
            }

            sx = nx;
            sy = ny;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[R][C];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        if(K > R*C) {System.out.println(0); return;}

        batch(0, R-1);

        System.out.println(Xanswer+" "+Yanswer);

    }
}
