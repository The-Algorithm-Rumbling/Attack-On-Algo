import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Set<Integer> [] set;
    static int [][] map;
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        set = new HashSet [n*n+1];
        map = new int [n][n];

        for (int i = 0; i < n*n+1; i++) {
            set[i] = new HashSet<>();
        }
        
        for (int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                set[a].add(Integer.parseInt(st.nextToken()));
            }
            
            find(a);
        }
        
        System.out.println(answer());

    }

    static void find (int a) {
        int max = 0;
        int [][][] map2 = new int [n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    int cnt = 0;
                    int empty = 0;
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            if (set[a].contains(map[nr][nc])) cnt++;
                            else if (map[nr][nc] == 0) empty++;
                        }
                    }
                    
                    map2[i][j][0] = cnt;
                    map2[i][j][1] = empty;

                    max = Math.max(max, cnt);
                }
            }
        }

        List<int []> like = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && map2[i][j][0] == max) like.add(new int [] {i, j});
            }
        }

        List<int []> isEmpty = new ArrayList<>();
        
        if (like.size() > 1) {
            int maxEmpty = -1;
            for (int [] arr : like) {
                int emptyCnt = map2[arr[0]][arr[1]][1];
                if (maxEmpty < emptyCnt) {
                    isEmpty.clear();
                    isEmpty.add(arr);
                    maxEmpty = emptyCnt;
                } else if (maxEmpty == emptyCnt) isEmpty.add(arr);
            }
        } else {
            int [] arr = like.get(0);
            map[arr[0]][arr[1]] = a;
            return;
        }

        isEmpty.sort((x, y) -> {
            if (x[0] == y[0]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });
        
        int [] arr = isEmpty.get(0);
        map[arr[0]][arr[1]] = a;
        return;
        
    }

    static int answer () {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = map[i][j];
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        if (set[a].contains(map[nr][nc])) cnt++;
                    }
                }

                if (cnt == 1) ans++;
                else if (cnt == 2) ans += 10;
                else if (cnt == 3) ans += 100;
                else if (cnt == 4) ans += 1000;
            }
        }

        return ans;
    }

}