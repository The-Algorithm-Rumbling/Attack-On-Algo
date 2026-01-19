import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, max, maxIdx;
    static int [] arr;
    static Stack<int []> st1 = new Stack<>();
    static Stack<int []> st2 = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int [n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            st1.add(new int [] {Integer.parseInt(st.nextToken()), i});
        }

        while(st1.size() != 1) {
            st2.add(st1.pop());

            while (!st2.isEmpty() && st2.peek()[0] <= st1.peek()[0]) {
                arr[st2.pop()[1]] = st1.peek()[1] + 1;
            }
        }

        for (int i = 0; i < n; i++) sb.append(arr[i]).append(" ");
        
        System.out.println(sb.toString());
    }
}