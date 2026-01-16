import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static char [] ch;
    static Stack<Character> stack1 = new Stack<>();
    static Stack<Character> stack2 = new Stack<>();
    public static void main(String[] args) throws IOException {
        ch = br.readLine().toCharArray();
        n = Integer.parseInt(br.readLine());

        for (char c : ch) {
            stack1.add(c);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String tmp = st.nextToken();

            if (tmp.charAt(0) == 'L') {
                if (stack1.size() == 0) continue;
                else stack2.add(stack1.pop());
            } else if (tmp.charAt(0) == 'D') {
                if (stack2.size() == 0) continue;
                else stack1.add(stack2.pop());
            } else if (tmp.charAt(0) == 'B') {
                if (stack1.size() != 0) stack1.pop();
            } else {
                stack1.add(st.nextToken().charAt(0));
            }
        }

        for (char c : stack1) {
            sb.append(c);
        }

        while (!stack2.isEmpty()) {
            sb.append(stack2.pop());
        }
        
        System.out.println(sb.toString());
    }
}