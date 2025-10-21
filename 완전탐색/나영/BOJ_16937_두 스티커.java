import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int h, w, n, max;
    static int [][] sticker;
    static boolean [][] vis;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
        h = sc.nextInt();
        w = sc.nextInt();
        n = sc.nextInt();
        
        sticker = new int [n][2];
        vis = new boolean [h][w];

        for (int i = 0; i < n; i++) {
            sticker[i][0] = sc.nextInt();
            sticker[i][1] = sc.nextInt();
        }

        dfs(0, 0, new int [2]);
        
        System.out.println(max);
    }

    static void dfs (int depth, int start, int [] arr) {
        if (depth == 2) {
            max = Math.max(max, check(arr));
            return;
        }

        for (int i = start; i < n; i++) {
            arr[depth] = i;
            dfs(depth+1, i+1, arr);
        }
    }

    static int check(int [] arr) {
        int x1 = sticker[arr[0]][0];
        int y1 = sticker[arr[0]][1];
        int x2 = sticker[arr[1]][0];
        int y2 = sticker[arr[1]][1];

        int [][] s1 = {{x1, y1}, {y1, x1}};
        int [][] s2 = {{x2, y2}, {y2, x2}};

        for (int [] st1 : s1) {
            for (int [] st2 : s2) {
                int h1 = st1[0]; int w1 = st1[1];
                int h2 = st2[0]; int w2 = st2[1];
                
                if (Math.max(h1, h2) <= h && w1 + w2 <= w) {
                    return h1 * w1 + h2 * w2;
                }
                
                if (Math.max(w1, w2) <= w && h1 + h2 <= h) {
                    return h1 * w1 + h2 * w2;
                }
            }
        }
        
        return 0;
    }
}