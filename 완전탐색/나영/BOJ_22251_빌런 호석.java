import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, k, p, x, ans;
    static int [][] count = new int [10][10];
    static boolean [][] mark = {
        {true, true, true, false, true, true, true}, 
        {false, false, true, false, false, true, false}, 
        {true, false, true, true, true, false, true}, 
        {true, false, true, true, false, true, true}, 
        {false, true, true, true, false, true, false}, 
        {true, true, false, true, false, true, true}, 
        {true, true, false, true, true, true, true}, 
        {true, false, true, false, false, true, false}, 
        {true, true, true, true, true, true, true}, 
        {true, true, true, true, false, true, true}
    };
    static int [] arr, cur;
    public static void main(String[] args) {

        // 숫자 별 다른 숫자로 변경 시 반전 개수 저장
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int l = 0; l < 7; l++) {
                    if (mark[i][l] != mark[j][l]) count[i][j]++;
                }
            }
        }
        
        n = sc.nextInt();
        k = sc.nextInt();
        p = sc.nextInt();
        x = sc.nextInt();

        arr = new int [k];

        int tmp = k-1;
        while (x > 0) {
            arr[tmp] = x % 10;
            tmp--;
            x /= 10;
        }
        while (tmp >= 0) arr[tmp--] = 0;

        cur = new int [k];

        dfs(0, 0);
        
        System.out.println(ans);
    }

    static void dfs(int tmp, int cnt) {
        if (tmp == k) {
            if (cnt > 0 && cnt <= p) {
                int num = 0;
                boolean isS = true;
                for (int i = 0; i < k; i++) {
                    num = num * 10 + cur[i];
                }

                if (num > 0 && num <= n && num != x) ans++;
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            int need = count[arr[tmp]][i];
            if (cnt + need <= p) {
                cur[tmp] = i;
                dfs(tmp+1, cnt + need);
            }
        }
    }
}