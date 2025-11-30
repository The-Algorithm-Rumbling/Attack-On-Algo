import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, x, y;
    static boolean isS;
    static int [] arr = new int [5];
    static char [][] charn;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        charn = new char[n][];

        for (int i = 0; i < n; i++) {
            charn[i] = br.readLine().toCharArray();
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (charn[r][c] == '*') {
                    if (isS) {
                        // 왼쪽 팔, 심장, 오른쪽 팔 체크
                        if (arr[0] == 0) {
                            while (charn[r+1][c] != '*') {
                                arr[0]++;
                                c++;
                            }

                            x = r+1; y = c+1;
                            c++;

                            while (c < n && charn[r][c] != '_') {
                                arr[1]++;
                                c++;
                            }
                        // 허리 체크
                        } else if (arr[2] == 0) {
                            while (true) {
                                arr[2]++;

                                if (charn[r+1][c] == '_') break;
                                r++;
                            }
                        // 왼쪽 다리, 오른쪽 다리 체크
                        } else {
                            int nr = r;
                            while (nr < n && charn[nr][c] == '*') {
                                arr[3]++;
                                nr++;
                            }

                            c+=2;

                            while (r < n && charn[r][c] == '*') {
                                arr[4]++;
                                r++;
                            }
                            System.out.println(x + " " + y);
                            for (int a : arr) System.out.print(a + " ");
                            return;
                        }
                    } else {
                        isS = true;
                    }
                }
            }
        }
        
        
    }
}