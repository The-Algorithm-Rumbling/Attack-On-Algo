import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k;
    static int[] robots; // 내구도
    static boolean[] hasRobot; // 로봇 위치
    static int zeroCount = 0; // 내구도 0인 칸 개수

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        robots = new int[2 * n];
        hasRobot = new boolean[2 * n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            robots[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(simulate());
    }

    static int simulate() {
        int step = 0;

        while (zeroCount < k) {
            step++;

            // 1. 벨트 회전
            rotate();

            // 2. 로봇 이동
            moveRobots();

            // 3. 0번 칸에 로봇 올리기
            if (!hasRobot[0] && robots[0] > 0) {
                hasRobot[0] = true;
                robots[0]--;
                if (robots[0] == 0) zeroCount++;
            }
        }
        return step;
    }

    static void rotate() {
        // 내구도 회전
        int last = robots[2*n - 1];
        for (int i = 2*n - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = last;

        // 로봇 회전
        for (int i = 2*n - 1; i > 0; i--) {
            hasRobot[i] = hasRobot[i-1];
        }

        hasRobot[0] = false;
        hasRobot[n-1] = false;
    }

    static void moveRobots() {
        for (int i = n - 2; i >= 0; i--) {
            if (hasRobot[i] && !hasRobot[i+1] && robots[i+1] > 0) {
                hasRobot[i] = false;
                hasRobot[i+1] = true;
                robots[i+1]--;
                if (robots[i+1] == 0) zeroCount++;
            }
        }
        
        hasRobot[n - 1] = false;
    }
}
