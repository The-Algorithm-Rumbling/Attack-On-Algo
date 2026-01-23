import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, height, cnt, pos, size;
    static int [] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            cnt = 0;
            size = 0;
            arr = new int [20];
            st = new StringTokenizer(br.readLine());
            sb.append(Integer.parseInt(st.nextToken())).append(" ");

            for (int j = 0; j < 20; j++) {
                height = Integer.parseInt(st.nextToken());
                pos = size;
                while (pos > 0 && arr[pos-1] > height) {
                    arr[pos] = arr[pos-1];
                    pos--;
                    cnt++;
                }
                arr[pos] = height;
                size++;
            }

            sb.append(cnt).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}