import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean isS;
    static int T, v, e;
    static int [] arr;
    static List<Integer> list[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            isS = true;

            arr = new int [v+1];
            list = new ArrayList[v+1];
            for (int i = 1; i < v+1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            for (int i = 1; i < v+1; i++) {
                if (arr[i] != 0) continue;
                arr[i] = 1;
                if (!dfs(i)) {
                    isS = false;
                    break;
                }
            }

            if (isS) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static boolean dfs (int depth) {
        for (int i : list [depth]) {
            if (arr[i] != 0 && arr[i] == arr[depth]) {
                return false;
            } else if (arr[i] == 0) {
                arr[i] = arr[depth] % 2 + 1;
                if (!dfs(i)) return false;
            }
        }
        return true;
    }
}