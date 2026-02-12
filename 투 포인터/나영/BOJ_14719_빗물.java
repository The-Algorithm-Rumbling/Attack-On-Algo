import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int w, h, l, r, leftMax, rightMax, ans;
    static int [] block;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        block = new int [w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        l = 0;
        r = w-1;

        while (l < r) {
            if (block[l] <= block[r]) {
                if (block[l] >= leftMax) {
                    leftMax = block[l];
                } else {
                    ans += leftMax - block[l];
                }
                l++;
            } else {
                if (block[r] >= rightMax) {
                    rightMax = block[r];
                } else {
                    ans += rightMax - block[r];
                }
                r--;
            }
        }
        
        System.out.println(ans);
    }
}