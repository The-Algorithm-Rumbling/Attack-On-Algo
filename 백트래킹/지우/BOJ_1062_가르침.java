import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static ArrayList<String> words = new ArrayList<>();
    static HashSet<Character> sets = new HashSet<>();
    static ArrayList<Character> candidates = new ArrayList<>();
    static int max = 0;

    static boolean check(String s) {
        for(int c=0; c<s.length(); c++) {
            char ch = s.charAt(c);
            if(!sets.contains(ch)) {
                return false;
            }
        }
        return true;
    }
    
    static Integer Count() {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            String s = words.get(i);
            if(check(s)) cnt++;
        }
        return cnt;
    }
    
    static void dfs(int cnt, int start) {
        if(cnt == K-5 || start == candidates.size()) {
            max = Math.max(max, Count());
            return;
        }
    
        for(int i=start; i<candidates.size(); i++) {
            char ch = candidates.get(i);
            sets.add(ch);
            dfs(cnt+1, i+1);
            sets.remove(ch);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sets.add('a'); sets.add('n'); sets.add('t'); sets.add('i'); sets.add('c');
        
        Set<Character> tmps = new HashSet<>();
        
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            words.add(s);

            for(int j=0; j<s.length(); j++) {
                char ch = s.charAt(j);
                if(!sets.contains(ch)) {
                    tmps.add(ch);
                }
            }
        }
        
        for(Character t : tmps) {
            candidates.add(t);
        }

        if(K-5 < 0) {
            System.out.println(0);
            return;
        }
        
        dfs(0,0);
        System.out.println(max);
    }
}