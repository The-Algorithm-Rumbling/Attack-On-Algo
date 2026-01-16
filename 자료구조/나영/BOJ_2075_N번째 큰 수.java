import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, max, maxI, maxJ;
    static int [][] map;
    static int [] idx;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int [n][n];
        idx = new int [n];

        Arrays.fill(idx, n-1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        while (m < n) {
            // 각 열 당 마지막 행의 숫자를 idx 배열에 저장
            // idx의 행의 번호를 찾아가면서 숫자 찾기
            max = Integer.MIN_VALUE;
            for (int j  = 0; j < n; j++) {
                if (idx[j] < 0) continue;
                if (max < map[idx[j]][j]) {
                    max = map[idx[j]][j];
                    maxI = idx[j];
                    maxJ = j;
                }
            }

            m++;
            idx[maxJ] = maxI-1;
        }
        
        System.out.println(max);
    }
}