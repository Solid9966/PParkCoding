import java.util.*;
import java.io.*;

public class Main {
    static int N = 5;
    static int r,c;
    static int answer = 0, cnt = 0;

    static int[][] graph = new int[N][N];
    static boolean[][] visited = new boolean[N][N];

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int dfs(int x, int y, int eat, int dept) {

        // 성공 조건
        if(eat >= 2) return 1;
        // 실패 조건
        if (dept == 3) return 0;


        for(int i = 0;i < 4; i++) {
            int nx = x + dx[i]; int ny = y + dy[i];

            // 보드 바깥,
            if (nx >= N || nx < 0 || ny >= N || ny < 0) continue;
            // 왔던 길,
            if(visited[ny][nx]) continue;
            // 막힌 길,
            if(graph[ny][nx] == -1) continue;

            visited[ny][nx] = true;

            // 사과 먹었을때,
            int neat = eat;
            if(graph[ny][nx] == 1) neat++;

            int tmp = dfs(nx, ny, neat,dept+1);
            visited[ny][nx] = false;

            // 성공조건2
            if(tmp == 1) return 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 초기화
        for (int i =0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 초기 위치
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // dfs logic
        if (graph[r][c] == 1) cnt++;
        visited[r][c] = true;
        answer = dfs(c,r,cnt,0);

        System.out.println(answer);

    }
}
