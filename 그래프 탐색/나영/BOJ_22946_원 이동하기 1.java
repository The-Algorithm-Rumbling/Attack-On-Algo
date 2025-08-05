import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, ans;
    static List<Integer> list[];
    static List<int[]> map = new ArrayList<>();
    static boolean visited[];
    
    static void connect(int a) {
        int [] p1 = map.get(a);
        boolean isS = false;
        for (int i = a+1; i < map.size(); i++) {
            int [] p2 = map.get(i);
            if (find(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2])) {
                // if (!list[i].isEmpty()) isS = true;
                list[a].add(i);
                list[i].add(a);
                // p1 = map.get(i);
                return;
            }
            // if (isS) return;
        }
    }

    static boolean find(int x1, int y1, int r1, int x2, int y2, int r2) {
        long dx = x1 - x2;
        long dy = y1 - y2;
        long d_squared = dx * dx + dy * dy;
    
        long r_diff = (long)r1 - (long)r2;
        long r_diff_squared = r_diff * r_diff;
    
        // r_diff_squared > d_squared는 한 원이 다른 원을 포함하지만, 
        // 중심이 다를 때를 처리.
        // d_squared == 0은 두 원의 중심이 같을 때를 처리.
        // 이 두 조건 중 하나만 만족하면 포함 관계로 간주.
        return r_diff_squared > d_squared || d_squared == 0;
    }
    
    /*
    static boolean find(int x1, int y1, int r1, int x2, int y2, int r2) {
        double p1 = (x1 - x2) * (x1 - x2);
        double p2 = (y1 - y2) * (y1 - y2);
        double d = Math.sqrt(p1 + p2);

        return Math.abs(r1 - r2) > d || d == 0;
    }
    */
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            map.add(new int[] {x, y, r});
        }

        
        list[n] = new ArrayList<>();
        map.add(new int[] {0, 0, 10000000});

        map.sort((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < n+1; i++) {
            connect(i);
        }

        for (int i = 0; i < n+1; i++) {
            ans = Math.max(dfs(i, 0), ans);
        }
        
        System.out.println(ans);
    }

    static int dfs(int v, int cnt) {
        if (visited[v]) return 0;
        visited[v] = true;
        int depth = cnt;

        for (int i : list[v]) {
            depth = Math.max(depth, dfs(i, cnt+1));
        }
        
        visited[v] = false;
        return depth;
    }
}