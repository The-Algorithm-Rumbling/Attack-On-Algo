import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static StringTokenizer st;
    static int n, ans;
    static List<Integer> a = new ArrayList<>();
    public static void main(String[] args) {
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        dfs(0, a);

        System.out.println(ans);
    }

    static void dfs(int sum, List<Integer> list) {
        if (list.size() == 2) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 1; i < list.size()-1; i++) {
            int b = list.get(i);
            int sum2 = sum + list.get(i-1) * list.get(i+1);
            list.remove(i);
            dfs(sum2, list);
            list.add(i, b);
        }
    }
}