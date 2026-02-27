package 그리디.지우;

import java.util.*;

class Node {
    int v; int w;
    
    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

class Solution {
    static int n;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static boolean[] vis;
    static PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.w - b.w);
    
    static int prim() {
        q.add(new Node(0,0));
        int total = 0;
        
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            if(vis[curr.v]) continue;
            vis[curr.v] = true;
            total += curr.w;
            
            for(Node nxt : list.get(curr.v)) {
                q.add(nxt);
            }
        }
        return total;
    }
    
    public int solution(int n, int[][] costs) {
        this.n = n;
        vis = new boolean[n];
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            list.add(new ArrayList<Node>());
        }
        
        for(int i=0; i<costs.length; i++) {
            int a = costs[i][0]; int b = costs[i][1];
            int w = costs[i][2];
            
            list.get(a).add(new Node(b, w));
            list.get(b).add(new Node(a, w));
        }
        
        return prim();
    }
}