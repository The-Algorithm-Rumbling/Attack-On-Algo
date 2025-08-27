import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int [] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) {
        n = sc.nextInt();

        dfs(0, 0);

        Collections.sort(list);
        System.out.println(list.size() <= n ? -1 : list.get(n));
    }

    static void dfs(int start, long sum) {
        if (!list.contains(sum)) {
            list.add(sum);
        }

        for (int i = start; i < 10; i++) {
            dfs(i+1, sum*10 + arr[i]);
        }
    }
}