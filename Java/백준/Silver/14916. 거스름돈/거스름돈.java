// logic
// 1. 5최대 치에서 하나씩 빼면서 2로 나누어지는지 브루트포스

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int answer =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int a = n/5;
        while(a>=0) {
            int cnt = n - (a * 5);
            if(cnt % 2 == 0) {
                answer = a + (cnt / 2);
                break;
            }
            a--;
        }
        if (a < 0) answer = -1;

        System.out.println(answer);

    }
}
