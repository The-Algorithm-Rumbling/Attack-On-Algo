import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] map = new int [9][9];
    static boolean isS;
    static boolean [][] R, C;
    static boolean [][][] rec;
    static List<int []> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        R = new boolean [9][10];
        C = new boolean [9][10];
        rec = new boolean [3][3][10];

        for (int r = 0; r < 9; r++) {
            char [] charn = br.readLine().toCharArray();
            for (int c = 0; c < 9; c++) {
                map[r][c] = charn[c] - '0';
                if (map[r][c] == 0) list.add(new int [] {r, c});
                else {
                    R[r][map[r][c]] = true;
                    C[c][map[r][c]] = true;
                    rec[r/3][c/3][map[r][c]] = true;
                }
            }
        }

        dfs(0);
        System.out.print(sb.toString());
    }

    static void dfs (int tmp) {
        if (isS) return;
        if (tmp == list.size()) {
            isS = true;
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    System.out.print(map[r][c]);
                }
                System.out.println();
            }

            return;
        }
        
        int r = list.get(tmp)[0];
        int c = list.get(tmp)[1];

        for (int i = 1; i < 10; i++) {
            if (R[r][i] || C[c][i] || rec[r/3][c/3][i]) continue;
            R[r][i] = true;
            C[c][i] = true;
            rec[r/3][c/3][i] = true;
            map[r][c] = i;

            dfs (tmp + 1);
            
            R[r][i] = false;
            C[c][i] = false;
            rec[r/3][c/3][i] = false;
            map[r][c] = 0;
        }
    }
}