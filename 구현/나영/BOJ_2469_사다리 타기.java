import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int k, n, m;
    static char [] player, top, bottom;
    static char [][] map;
    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        player = br.readLine().toCharArray();
        map = new char [n][];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            if (map[i][0] == '?') m = i;
        }

        top = new char [k];
        for (int i = 0; i < k; i++) top[i] = (char)('A' + i);
        for (int i = 0; i < m; i++) find(top, i);

        bottom = player.clone();
        
        for (int i = n-1; i > m; i--) find(bottom, i);

        if (dfs(0)) {
            for (char c : map[m]) System.out.print(c);
        } else for (int i = 0; i < k-1; i++) System.out.print("x");
    }
    
    static boolean dfs (int idx) {
        if (idx == k-1) return true;

        if (top[idx] == bottom[idx]) {
            map[m][idx] = '*';
            return dfs(idx+1);
        } else if (top[idx] == bottom[idx+1] && top[idx+1] == bottom[idx]) {
            map[m][idx] = '-';
            char tmp = top[idx+1];
            top[idx+1] = top[idx];
            top[idx] = tmp;
            return dfs(idx+1);
        }

        return false;
    }

    static void find(char [] arr, int r) {
        for (int i = 0; i < k-1; i++) {
            if (map[r][i] == '-') {
                char tmp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tmp;
            }
        }
    }

}