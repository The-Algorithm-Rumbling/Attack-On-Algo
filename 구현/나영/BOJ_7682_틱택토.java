import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean isS;
    static char [] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        while (true) {
            arr = br.readLine().toCharArray();
            isS = true;

            if (arr[0] == 'e') break;

            int cntO = 0; int cntX = 0;
            for (int i = 0; i < 9; i++) {
                if (arr[i] == 'O') cntO++;
                else if (arr[i] == 'X') cntX++;
            }

            if (cntO > cntX || cntX - cntO > 1) {
                sb.append("invalid\n");
                continue;
            }

            int lineO = 0; int lineX = 0;

            if ((arr[0] == arr[4] && arr[4] == arr[8]) || (arr[2] == arr[4] && arr[4] == arr[6])) {
                if (arr[4] == 'O') lineO++;
                else if (arr[4] == 'X') lineX++;
            }

            for (int i = 0; i < 9; i+=3) {
                if (arr[i] == 'X') {
                    if (arr[i] == arr[i+1] && arr[i+1] == arr[i+2]) lineX++;
                } else if (arr[i] == 'O') {
                    if (arr[i] == arr[i+1] && arr[i+1] == arr[i+2]) lineO++;
                }
            }

            for (int i = 0; i < 3; i++) {
                if (arr[i] == 'X') {
                    if (arr[i] == arr[i+3] && arr[i+3] == arr[i+6]) lineX++;
                } else if (arr[i] == 'O') {
                    if (arr[i] == arr[i+3] && arr[i+3] == arr[i+6]) lineO++;
                }
            }

            if (lineO > 0) {
                if (cntO == cntX && lineX == 0) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            } else if (lineX > 0) {
                if (cntO < cntX && lineO == 0) sb.append("valid\n");
                else sb.append("invalid\n");
                continue;
            }

            if (cntO + cntX == 9) {
                sb.append("valid\n");
                continue;
            }

            sb.append("invalid\n");

        }
        System.out.println(sb.toString());
    }
}