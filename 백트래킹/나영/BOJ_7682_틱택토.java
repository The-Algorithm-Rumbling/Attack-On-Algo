import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int n = 0;
        r: while(true) {
            arr = br.readLine().toCharArray();
            if (arr[0] == 'e') break;
            n++;

            // 1. O와 X의 개수 세서 O가 더 많으면 fail.
            //    O와 X의 개수가 같거나 O가 한 개 더 적어야 함
            int sum = 0;
            int sumX = 0;
            int sumO = 0;
            for (int i = 0; i < 9; i++) {
                if (arr[i] != '.') {
                    sum++;
                    if (arr[i] == 'X') sumX++;
                    else sumO++;
                }
            }
            

            if (sumX != sumO && sumX - 1 != sumO) {
                sb.append("invalid\n");
                continue;
            } 
                

            // 2. 빙고 됐으면 허용. 
            // 이 때 빙고는 행, 열에 하나만 있어야 하고, => 이건 갯수땜에 어차피 걸러질 듯
            // X나 O에서 이미 빙고가 만들어졌는데 말을 더 놓아선 안 된다.
            // 대각선은 노상관
            int sumX2 = 0;
            int sumO2 = 0;
            
            if (arr[4] == 'X') {
                if ((arr[0] == arr[4] && arr[4] == arr[8]) || (arr[2] == arr[4] && arr[4] == arr[6])) sumX2++;
            } else if (arr[4] == 'O') {
                if ((arr[0] == arr[4] && arr[4] == arr[8]) || (arr[2] == arr[4] && arr[4] == arr[6])) sumO2++;
            }
            
            // 만약 빙고가 있다면 sumX가 sumO보다 큰지, X와 O 둘 다 빙고가 있는지 확인
            if (sumX2 >= 1) {
                if (sumX > sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            } else if (sumO2 >= 1) {
                if (sumX == sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            }
            

            sumX2 = 0;
            sumO2 = 0;

            // 좌우
            for (int i = 0; i < 9; i+=3) {
                if (arr[i] == 'X') {
                    if (arr[i] == arr[i+1] && arr[i] == arr[i+2]) sumX2++;
                } else if (arr[i] == 'O') {
                    if (arr[i] == arr[i+1] && arr[i] == arr[i+2]) sumO2++;
                }
            }

            // 만약 빙고가 있다면 sumX가 sumO보다 큰지, X와 O 둘 다 빙고가 있는지 확인
            if (sumX2 >= 1) {
                if (sumX > sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            } else if (sumO2 >= 1) {
                if (sumX == sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            }
            
            sumX2 = 0;
            sumO2 = 0;

            // 상하
            for (int i = 0; i < 3; i++) {
                if (arr[i] == 'X') {
                    if (arr[i] == arr[i+3] && arr[i] == arr[i+6]) sumX2++;
                } else if (arr[i] == 'O') {
                    if (arr[i] == arr[i+3] && arr[i] == arr[i+6]) sumO2++;
                }
            }

            // 만약 빙고가 있다면 sumX가 sumO보다 큰지, X와 O 둘 다 빙고가 있는지 확인
            if (sumX2 >= 1) {
                if (sumX > sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            } else if (sumO2 >= 1) {
                if (sumX == sumO && sumX2 != sumO2) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            }

            // 3. 모든 칸이 채워져 있으면 허용
            if (sum == arr.length) {
                sb.append("valid\n");
                continue;
            }

            sb.append("invalid\n");

        }
        System.out.println(sb.toString());
    }
}