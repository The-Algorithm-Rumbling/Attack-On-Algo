import java.util.*;

class Solution {
    String[][] tickets;
    ArrayList<String> ans = new ArrayList<>();
    boolean[] vis;
    boolean isS = false;
    String[] answer;
    
    void dfs(String curr, int cnt) {
        if(cnt == tickets.length) {
            isS = true;
            answer = new String[ans.size()];
            for(int i=0; i<ans.size(); i++) {
                answer[i] = ans.get(i);
            }
            return;
        }
        if(isS) return;
        
        for(int i=0; i<tickets.length; i++) {
            if(vis[i]) continue;
            String a = tickets[i][0]; String b = tickets[i][1];
            if(curr.equals(a)) {
                vis[i] = true;
                ans.add(b);
                
                dfs(b, cnt+1);
                
                vis[i] = false;
                ans.remove(ans.size()-1);
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        vis = new boolean[tickets.length];
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                if(!a[0].equals(b[0])) return a[0].compareTo(b[0]);
                return a[1].compareTo(b[1]);
            }
        });
        
        ans.add("ICN");
        dfs("ICN", 0);
        
        return answer;
    }
}