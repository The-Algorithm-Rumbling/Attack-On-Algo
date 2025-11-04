from collections import deque
import sys
input = sys.stdin.readline

def check(r, c) : 
    global n
    return r >= 0 and r < n and c >= 0 and c < n
    
def bfs(x, y) : 
    global isS
    dq = deque([(x, y)])
    arr = [(x, y)]
    vis[x][y] = 1
    total = city[x][y]
    
    while dq : 
        r, c = dq.popleft()
        for d in range(4) : 
            nr = r + dr[d]
            nc = c + dc[d]
    
            if check(nr, nc) and not vis[nr][nc] and abs(city[r][c] - city[nr][nc]) >= l and abs(city[r][c] - city[nr][nc]) <= R : 
                vis[nr][nc] = 1
                arr.append((nr, nc))
                total += city[nr][nc]
                dq.append((nr, nc))

    if len(arr) > 1 : 
        avg = total // len(arr)
        for r, c in arr : 
            city[r][c] = avg
            isS = 1
        
def find() : 
    global vis, isS
    vis = [[0] * n for _ in range(n)]
    isS = 0

    for r in range(n) : 
        for c in range(n) : 
            if not vis[r][c] : 
                bfs(r, c)

    return isS
    
n, l, R = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]
cnt = 0

while find() : 
    cnt+=1
    
print(cnt)