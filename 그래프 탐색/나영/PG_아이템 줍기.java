import java.util.*;

class Solution {
    static class player
    {
        int x;
        int y;
        int move;
        
        public player(int y, int x, int move)
        {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    
    static int[][] map = new int[101][101];
    static boolean[][] visit = new boolean[101][101];
    static Queue<player> q;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        q = new LinkedList<>();
        q.add(new player(2*characterY, 2*characterX, 0)); //건들지마
        
        for(int i = 0 ; i < rectangle.length ; i++)
        {
            int lx = rectangle[i][0]*2;
            int ly = rectangle[i][1]*2;
            int rx = rectangle[i][2]*2;
            int ry = rectangle[i][3]*2;
            
            for(int j = ry ; j >= ly ; j--)
            {
                for(int k = rx ; k >= lx ; k--)
                {
                    if(map[j][k] == 2) continue;
                    
                    map[j][k] = 2;
                    
                    if(j == ly || j == ry || k == lx || k == rx)
                        map[j][k] = 1;
                }
                
            }
        }
                           
        bfs(2*itemY, 2*itemX);
        
        return answer/2;
    }
    public static void bfs(int iy, int ix)
    {
        while(!q.isEmpty())
        {
            player p = q.poll();
            
            if(p.y == iy && p.x == ix)
            {
                answer = Math.min(answer, p.move);
                return;
            }
            
            if(visit[p.y][p.x] || map[p.y][p.x] != 1) continue;
            visit[p.y][p.x] = true;
            
            for(int i = 0 ; i < 4 ; i++)
            {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;

                q.add(new player(ny, nx, p.move+1));
            }
        }
    }
    
}