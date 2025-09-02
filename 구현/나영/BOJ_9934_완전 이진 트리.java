import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int k, n, cnt;
    static int [] arr;
    static List<Integer> tree [];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        k = sc.nextInt();
        tree = new ArrayList[k];
        int tmp = 1;
        for (int i = 0; i < k; i++) {
            tree[i] = new ArrayList<>();
            n += tmp;
            tmp *= 2;
        }
        
        arr = new int [n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        dfs(0, n-1, 0);

        for (int i = 0; i < k; i++) {
            for (int val : tree[i]) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int start, int end, int depth) {
        if (start > end) return;
        int mid = (start + end) / 2;

        tree[depth].add(arr[mid]);

        // 왼쪽 서브트리
        dfs(start, mid-1, depth+1);
        // 오른쪽 서브트리
        dfs(mid+1, end, depth+1);
    }
}