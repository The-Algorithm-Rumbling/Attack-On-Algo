import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, n, k, id, m, ans;
    static int [] sumScore, count, time;
    static int [][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            id = Integer.parseInt(st.nextToken())-1;
            m = Integer.parseInt(st.nextToken());

            map = new int [n][k];
            count = new int [n];
            time = new int [n];
            sumScore = new int [n];
            ans = 1;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int team = Integer.parseInt(st.nextToken())-1;
                int q = Integer.parseInt(st.nextToken())-1;
                int score = Integer.parseInt(st.nextToken());

                // 해당 문제의 최고 점수 저장
                map[team][q] = Math.max(map[team][q], score);

                // 제출 횟수 저장
                count[team]++;
                
                // 마지막 제출 시간 저장
                time[team] = i;
            }

            // 최고 점수를 모두 더해 최종 점수를 구한 뒤 비교
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    sumScore[i] += map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                if (i == id) continue;
                if (sumScore[i] > sumScore[id]) ans++;
                else if (sumScore[i] == sumScore[id]) {
                    // 최종 점수가 같다면 제출 횟수로 비교
                    if (count[i] < count[id]) ans++;
                    else if (count[i] == count[id]) {
                        // 제출 횟수도 같다면 마지막 제출 시간으로 비교
                        if (time[i] < time[id]) ans++;
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}