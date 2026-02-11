import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k, cnt, ans;
    static int[] belt;
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        belt = new int[2 * n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < 2 * n; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        
        while (cnt < k) {
            ans++;

            // 1. 벨트 및 로봇 한 칸 회전
            rotateBelt();

            // 2. 로봇 이동 (가장 먼저 올라간 로봇부터 = n-2부터 역순으로)
            moveRobots();

            // 3. 로봇 올리기
            if (belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
                if (belt[0] == 0) cnt++;
            }
        }
        
        System.out.println(ans);
    }

    static void rotateBelt() {
        int prev = belt[2*n - 1];
        for (int i = 2*n - 1;  i > 0; i--) {
            belt[i] = belt[i-1];
        }
        belt[0] = prev;
        
        for (int i = n-1; i > 0; i--) {
            robot[i] = robot[i-1];
        }
        robot[0] = robot[n-1] = false;
    }

    static void moveRobots() {
        for (int i = n-2; i > 0; i--) {
            if (robot[i] && !robot[i+1] && belt[i+1] > 0) {
                robot[i] = false;
                robot[i+1] = true;
                belt[i+1]--;

                if (belt[i+1] == 0) cnt++;
            }
        }
    }
}