import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, total, ans = 1;
    static int [][] map = new int [6][3];
    static int[][] match = new int[15][2];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        
        // 6팀 경기 조합(15개) 미리 계산
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }
        
        for (int t = 0; t < 4; t++) {
            total = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = sc.nextInt();
                    total+=map[i][j];
                }
            }

            if (total > 30) {
                sb.append(0 + " ");
                continue;
            }
            
            if (dfs(0)) sb.append(1 + " ");
            else sb.append(0 + " ");
        }
        
        System.out.println(sb.toString());
    }

    static boolean dfs (int tmp) {
        if (tmp == 15) return true;

        int t1 = match[tmp][0];
        int t2 = match[tmp][1];

        // t1 승, t2 패
        if (map[t1][0] > 0 && map[t2][2] > 0) {
            map[t1][0]--; map[t2][2]--;
            if (dfs(tmp + 1)) return true;
            map[t1][0]++; map[t2][2]++;
        }

        // 무승부
        if (map[t1][1] > 0 && map[t2][1] > 0) {
            map[t1][1]--; map[t2][1]--;
            if (dfs(tmp + 1)) return true;
            map[t1][1]++; map[t2][1]++;
        }

        // t1 패, t2 승
        if (map[t1][2] > 0 && map[t2][0] > 0) {
            map[t1][2]--; map[t2][0]--;
            if (dfs(tmp + 1)) return true;
            map[t1][2]++; map[t2][0]++;
        }

        return false;
    }
}