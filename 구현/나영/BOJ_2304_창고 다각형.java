import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max, maxIdx, curH, curW, ans;
    static int [][] storage;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        storage = new int [n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            storage[i][0] = Integer.parseInt(st.nextToken());
            storage[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(storage, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < n; i++) {
            if (storage[i][1] > max) {
                max = storage[i][1];
                maxIdx = i;
            }
        }

        ans += storage[maxIdx][1];

        curW = storage[0][0];
        curH = storage[0][1];
        
        for (int i = 1; i <= maxIdx; i++) {
            if (storage[i][1] > curH) {
                ans += (storage[i][0] - curW) * curH;
                curW = storage[i][0];
                curH = storage[i][1];
            }
        }
        
        ans += (storage[maxIdx][0] - curW) * curH;
        
        curW = storage[n-1][0];
        curH = storage[n-1][1];

        for (int i = n-2; i >= maxIdx; i--) {
            if (storage[i][1] > curH) {
                ans += (curW - storage[i][0]) * curH;
                curW = storage[i][0];
                curH = storage[i][1];
            }
        }
        
        ans += (curW - storage[maxIdx][0]) * curH;
        
        System.out.println(ans);
    }
}