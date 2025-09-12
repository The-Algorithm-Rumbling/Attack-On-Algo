import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    static boolean isS;
    static boolean [] vis;
    static String [] answer;
    static String [][] tickets;
    static List <String> list = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        vis = new boolean [tickets.length];
        this.tickets = tickets;
        
        /*
        Arrays.sort(tickets, new Comparator<String[]> () {
            @Override
            public int compare(String [] a, String [] b) {
                int first = a[0].compareTo(b[0]);
                
                if (first == 0) return a[1].compareTo(b[1]);
                return first;
            }
            return 
        });
        */
        
        /*
        Arrays.sort(tickets, Comparator
                    .comparing((String [] a) -> a[0])
                    .thenComparing(a -> a[1])
        );
        */
        
        Arrays.sort(tickets, (a, b) -> {
            int first = a[0].compareTo(b[0]);
            if (first == 0) return a[1].compareTo(b[1]);
            return first;
        });
        
        list.add("ICN");
        
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                vis[i] = true;
                dfs(i);
                vis[i] = false;
                if (isS) break;
            }
        }
        
        return answer;
    }
    
    static void dfs (int idx) {
        if (isS) return;
        if (list.size() == tickets.length) {
            isS = true;
            list.add(tickets[idx][1]);
            answer = list.toArray(new String[0]);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!vis[i] && tickets[idx][1].equals(tickets[i][0])) {
                vis[i] = true;
                list.add(tickets[idx][1]);
                
                dfs(i);
                
                vis[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}