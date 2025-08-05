
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, ans, num=1;
    static Set<Integer> set;
    static int visited[][], map[][];
    static Map<Integer, Integer> hm = new HashMap<>();
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        visited = new int[n][m];
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 1 && visited[r][c] == 0) {
                    int size = dfs(r, c);
                    hm.put(num, size);
                    num++;
                }
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 0 && visited[r][c] == 0) {
                    set = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
            
                        if (check(nr, nc) && map[nr][nc] == 1) {
                            set.add(visited[nr][nc]);
                        }
                    }

                    int max = 0;
                    if (!hm.isEmpty()) {
                        for (int a : set) max += hm.get(a);
                    }

                    ans = Math.max(ans, max+1);
                }
            }
        }
        
        System.out.println(ans);
    }

    static int dfs(int r, int c) {
        visited[r][c] = num;
        int size = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == 0) {
                size += dfs(nr, nc);
            }
        }

        return size;
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}