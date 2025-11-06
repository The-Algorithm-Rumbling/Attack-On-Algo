from collections import deque
import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < h and 0 <= c < w

def bfs() : 
    wave = 0
    
    while dq : 
        wave += 1
        size = len(dq)
        for r, c in dq:  
            sand[r][c] = 0
        for _ in range(size) : 
            r, c = dq.popleft()
    
            for d in range(8) : 
                nr, nc = r + dr[d], c + dc[d]
    
                if sand[nr][nc] != 0 : 
                    sea_cnt[nr][nc] += 1
                    if sea_cnt[nr][nc] >= sand[nr][nc] and not vis[nr][nc] : 
                        vis[nr][nc] = 1
                        dq.append((nr, nc))
                        
    return wave

dr = [-1, 0, 1, 0, -1, 1, -1, 1]
dc = [0, -1, 0, 1, -1, 1, 1, -1]

h, w = map(int, input().split())
sand = []
vis =[[0] * w for _ in range(h)]
sea_cnt =[[0] * w for _ in range(h)]
dq = deque()

for i in range(h) :
    row = list(input().strip())
    for j in range(w) :
        if row[j] == '.' :
            row[j] = 0
        else :
            row[j] = int(row[j])
    sand.append(row)

for i in range(h) :
    for j in range(w) :
        if sand[i][j] != 0 :
            cnt = 0
            for d in range(8) :
                nr, nc = i + dr[d], j + dc[d]
                if sand[nr][nc] == 0 : cnt += 1
            sea_cnt[i][j] = cnt
            if cnt >= sand[i][j] : 
                vis[i][j] = 1
                dq.append((i, j))
                
print(bfs())