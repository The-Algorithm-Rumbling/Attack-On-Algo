import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [][] map = new char [12][6];
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, -1, 0, 1};
    static boolean isS;
    static int cnt;
    static List<int []> list;
    static boolean [][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {
            isS = false;
            
            for (int r = 0; r < 12; r++) {
                for (int c = 0; c < 6; c++) {
                    if (map[r][c] != '.') bfs(r, c, map[r][c]);
                }
            }

            if (isS) cnt++;
            else break;

            reMap();
        }
        
        System.out.println(cnt);
    }

    static void reMap() {
        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 6; c++) {
                if (map[r][c] == '.') {
                    int nr = r;
                    while (nr > 0 && map[nr-1][c] != '.') {
                        map[nr][c] = map[nr-1][c];
                        nr--;
                    }
                    
                    map[nr][c] = '.';
                }
            }
        }
    }

    static void bfs(int r, int c, char ch) {
        Queue<int[]> que = new LinkedList<>();
        visited = new boolean [12][6];
        visited[r][c] = true;
        list = new ArrayList<>();
        
        que.offer(new int [] {r, c});

        while (!que.isEmpty()) {
            int [] q = que.poll();
            list.add(new int [] {q[0], q[1]});

            for (int d = 0; d < 4; d++) {
                int nr = q[0] + dr[d];
                int nc = q[1] + dc[d];

                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == ch) {
                    visited[nr][nc] = true;
                    que.offer(new int [] {nr, nc});
                }
            }
        }

        if (list.size() >= 4) {
            isS = true;
            
            for (int [] arr : list) map[arr[0]][arr[1]] = '.';
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < 12 && c >= 0 && c < 6;
    }
}