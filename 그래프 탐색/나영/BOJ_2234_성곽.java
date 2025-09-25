import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, cnt, maxRoom1, maxRoom2;
    static int [][] map, rooms;
    static boolean [][] vis, visAnother;
                    //  서 북 동 남
    static int [] dr = {0, -1, 0, 1};
    static int [] dc = {-1, 0, 1, 0};
    static int [] wall = {1, 2, 4, 8};
    static List<Integer> roomCnt = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int [m][n];
        rooms = new int [m][n];
        vis = new boolean [m][n];
        roomCnt.add(0);

        for (int r = 0; r < m; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (rooms[r][c] != 0) continue;
                ++cnt;
                roomCnt.add(find(r, c, 1));

                maxRoom1 = Math.max(maxRoom1, roomCnt.get(roomCnt.size()-1));
            }
        }

        visAnother = new boolean [cnt+1][cnt+1];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                breakWall(r, c);
            }
        }
        
        System.out.println(cnt);
        System.out.println(maxRoom1);
        System.out.println(maxRoom2);
    }

    static int find(int r, int c, int sum) {
        if (vis[r][c]) return 0;
        vis[r][c] = true;
        rooms[r][c] = cnt;
        int roomSize = 1;
        
        int num = map[r][c];
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if ((num & wall[d]) == 0 && check(nr, nc)) {
                roomSize += find(nr, nc, sum+1);
            }
        }

        return roomSize;
    }

    static void breakWall (int r, int c) {
        int roomNum = rooms[r][c];
        int num = map[r][c];
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (check(nr, nc) && rooms[nr][nc] != roomNum && !visAnother[roomNum][rooms[nr][nc]]) {
                visAnother[roomNum][rooms[nr][nc]] = true;
                maxRoom2 = Math.max(maxRoom2, roomCnt.get(roomNum) + roomCnt.get(rooms[nr][nc]));
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

}