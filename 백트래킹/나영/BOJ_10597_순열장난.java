import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int arr[];
    static char charn[];
    static boolean [] visited = new boolean [51];
    public static void main(String[] args) throws IOException {
        charn = br.readLine().toCharArray();
        arr = new int [charn.length];

        for (int i = 0; i < charn.length; i++) {
            arr[i] = charn[i] - '0';
        }
        
        dfs(0, new ArrayList<>());
        
        System.out.println(sb.toString());
    }

    static void dfs(int start, List<Integer> list) {
        if (start == arr.length) {
            if (!check(list.size())) return;
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.exit(0);
        }

        int n = 0;

        for (int i = start; i < arr.length; i++) {
            n *= 10;
            n += arr[i];

            if (n > 50 || n == 0) return;
            if (visited[n]) continue;
            visited[n] = true;
            list.add(n);
            dfs(i+1, list);
            list.remove(list.size()-1);
            visited[n] = false;
        }
    }

    static boolean check(int size) {
        for (int i = 1; i <= size; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}