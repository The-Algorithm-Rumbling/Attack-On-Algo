import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < n and 0 <= c < m

def dfs(r, c) : 
    vis[r][c] = 1
    cnt = 1
    zero = 0

    for d in range(4) : 
        nr = r + dr[d]
        nc = c + dc[d]

        if check(nr, nc) and not vis[nr][nc] : 
            if grid[nr][nc] == 0 : 
                zero = 1
            elif grid[nr][nc] == 2 : 
                sub, sub_zero = dfs(nr, nc)
                cnt += sub
                if sub_zero : 
                    zero = 1

    return cnt, zero

def find(idx, depth) : 
    global ans, vis
        
    if depth == 2 : 
        total = 0
        for i in range(n):
            for j in range(m):
                vis[i][j] = 0
        for i in range(n) : 
            for j in range(m) : 
                if not vis[i][j] and grid[i][j] == 2 : 
                    cnt, zero = dfs(i, j)

                    if not zero : 
                        total += cnt

        ans = max(ans, total)
        return
        
    if idx == len(blank): return

    a, b = blank[idx]
    grid[a][b] = 1
    find(idx+1, depth+1)
    grid[a][b] = 0
    find(idx+1, depth)
    

dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]

n, m = map(int, input().split())
grid = []
blank = []
vis = [[0] * m for _ in range(n)]
for i in range(n):
    grid.append(list(map(int, input().split())))
    for j in range(m):
        if grid[i][j] == 0:
            blank.append((i, j))

loc = []
ans = 0

find(0, 0)

print(ans)