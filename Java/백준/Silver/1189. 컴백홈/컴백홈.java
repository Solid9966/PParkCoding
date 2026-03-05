import java.util.*;
import java.io.*;

public class Main {
    static int R,C,K;
    static char[][] graph;
    static boolean[][] visited;
    static int answer = 0;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int dfs (int sx, int sy, int cnt) {
        int path = 0;

        // 집 방문시,
        if(sy == 0 && sx == C-1) {
            return (cnt == K) ? 1 : 0;
        }
        // K횟수 넘을ㄷ 경우,
        if (cnt >= K) return 0;

            for(int i = 0;i < 4;i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];

                // 지도 바깥,
                if(nx >= C || ny >= R || nx < 0 || ny < 0) continue;
                // 왔던 길,
                if(visited[ny][nx]) continue;
                // 막힌 길,
                if(graph[ny][nx] == 'T') continue;



                visited[ny][nx] = true;
                path += dfs(nx, ny,cnt+1);
                visited[ny][nx] = false; // 백트래킹 원상복구

            }

        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[R][C];

        // 초기화
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        // dfs logic
        visited[R-1][0] = true;
        answer += dfs(0,R-1,1);
        
        System.out.println(answer);
    }
}
