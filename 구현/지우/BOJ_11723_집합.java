package 구현.지우;

import java.util.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        Set<Integer> sets = new HashSet<>();
        
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();

            if(s.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                sets.add(num);
            }
            else if(s.equals("remove")) {
                int num = Integer.parseInt(st.nextToken());
                sets.remove(num);
            }
            else if(s.equals("check")) {
                int num = Integer.parseInt(st.nextToken());
                if(sets.contains(num)) sb.append("1\n");
                else sb.append("0\n");
            }
            else if(s.equals("toggle")) {
                int num = Integer.parseInt(st.nextToken());
                if(sets.contains(num)) sets.remove(num);
                else sets.add(num);
            }
            else if(s.equals("all")) {
                sets.clear();
                for(int i=1; i<=20; i++) {
                    sets.add(i);
                }
            }
            else if(s.equals("empty")) {
                sets.clear();
            }
        }
        System.out.println(sb);
    }
}