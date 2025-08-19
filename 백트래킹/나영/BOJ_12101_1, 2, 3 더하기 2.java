import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    static Scanner sc = new Scanner(System.in);
    static int n, k, cnt;
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();

        dfs(0, new ArrayList<>());
        System.out.println(-1);
    }

    static void dfs (int sum, List<Integer> list) {
        if (sum > n) return;
        if (sum == n) {
            cnt++;
            if (cnt == k) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == list.size()-1) sb.append(list.get(i));
                    else sb.append(list.get(i)).append("+");
                }
                
                System.out.println(sb.toString());
                System.exit(0);
            }

            return;
        }

        for (int i = 1; i < 4; i++) {
            list.add(i);
            dfs(sum + i, list);
            
            list.remove(list.size()-1);
        }
    }
}