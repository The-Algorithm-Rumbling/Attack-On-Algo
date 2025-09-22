import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [] charn;
    static int n;
    static List<Character> list = Arrays.asList('A', 'F', 'C', 'B', 'D', 'E');
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            charn = br.readLine().toCharArray();

            if (!list.contains(charn[0])) {
                sb.append("Good\n");
                continue;
            }

            int tmp = charn[0] == 'A' ? 0 : 1;
            int cnt = 0;
            boolean isS = true;

            while (tmp < charn.length) {
                char c = list.get(cnt);

                if (cnt == 3) {
                    if (tmp < charn.length-1 || !list.contains(charn[tmp])) {
                        isS = false;
                        break;
                    }
                }

                if (charn[tmp] != c) {
                    isS = false;
                    break;
                }

                while (tmp < charn.length && charn[tmp] == c) {
                    tmp++;
                }

                cnt++;
            }
            
            if (!isS || cnt < 3) sb.append("Good\n");
            else sb.append("Infected!\n");
        }
        
        System.out.print(sb.toString());
    }
}