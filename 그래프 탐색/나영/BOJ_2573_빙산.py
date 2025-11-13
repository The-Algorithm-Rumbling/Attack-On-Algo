from collections import deque
import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < n and 0 <= c < m

def countIce():
    vis = [[0]*m for _ in range(n)]
    ice = 0
    for r in range(n):
        for c in range(m):
            if sea[r][c] != 0 and not vis[r][c]:
                ice += 1
                q = deque()
                q.append((r, c))
                vis[r][c] = 1
                while q:
                    x, y = q.popleft()
                    for d in range(4):
                        nx, ny = x + dr[d], y + dc[d]
                        if check(nx, ny) and sea[nx][ny] != 0 and not vis[nx][ny]:
                            vis[nx][ny] = 1
                            q.append((nx, ny))
    return ice

def bfs() : 
    global sea
    days = 0
    size = len(dq)
    sea_cnt = [[0] * m for _ in range(n)]
    while dq : 
        r, c = dq.popleft()
        size-=1
        cnt = 0
        
        for d in range(4) : 
            nr, nc = r + dr[d], c + dc[d]

            if check(nr, nc) and sea[nr][nc] == 0 : 
                cnt += 1
        sea_cnt[r][c] = cnt
            
        if size == 0 : 
            for r in range(n) : 
                for c in range(m) : 
                    if sea[r][c] > 0 : 
                        num = sea[r][c] - sea_cnt[r][c]
                        sea[r][c] = num if num >= 0 else 0
                    if sea[r][c] > 0 : 
                        dq.append((r, c))
                    
            size = len(dq)
            days+=1

            if countIce() >= 2 : return days
             
        
    else : return 0

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
n, m = map(int, input().split())
sea = [list(map(int, input().split())) for _ in range(n)]

if countIce() >= 2 : 
    print(0)
    exit(0)

dq = deque()

for r in range(n) : 
    for c in range(m) : 
        if sea[r][c] != 0 : 
            dq.append((r, c))
            
print(bfs())