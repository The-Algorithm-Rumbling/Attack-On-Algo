import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < n and 0 <= c < m
    
def wind() : 
    xx, yy = x1, y1
    prev = 0

    for d in range(4) : 
        xx, yy = xx + dr1[d], yy + dc1[d]
        while True : 
            nx, ny = xx + dr1[d], yy + dc1[d]
            grid[xx][yy], prev = prev, grid[xx][yy]
                
            if not check(nx, ny) or (nx == x1 and ny == y1) : 
                break
    
            xx, yy = nx, ny

    xx, yy = x2, y2
    prev = 0
    
    for d in range(4) : 
        xx, yy = xx + dr2[d], yy + dc2[d]
        while True : 
            nx, ny = xx + dr2[d], yy + dc2[d]
            grid[xx][yy], prev = prev, grid[xx][yy]
            if not check(nx, ny) or (nx == x2 and ny == y2) : 
                break

            xx, yy = nx, ny

def find() : 
    global grid
    new_grid = [[0] * m for _ in range(n)]
    
    for r in range(n) : 
        for c in range(m) : 
            if grid[r][c] > 0 : 
                num = grid[r][c] // 5
                cnt = 0
                for d in range(4) : 
                    nr, nc = r + dr1[d], c + dc1[d]
                    if check(nr, nc) and grid[nr][nc] != -1 : 
                        new_grid[nr][nc] += num
                        cnt+=1

                new_grid[r][c] += grid[r][c] - cnt * num

    new_grid[x1][y1] = -1
    new_grid[x2][y2] = -1
    grid = new_grid
    
    wind()
                    
dr1 = [0, -1, 0, 1]
dc1 = [1, 0, -1, 0]
                    
dr2 = [0, 1, 0, -1]
dc2 = [1, 0, -1, 0]
n, m, t = map(int, input().split())

grid = [list(map(int, input().split())) for _ in range(n)]

for r in range(n) : 
    if grid[r][0] == -1 : 
        x1, y1, x2, y2 = r, 0, r+1, 0
        break

for _ in range(t) : 
    find()

ans = sum(grid[r][c] for r in range(n) for c in range(m) if grid[r][c] != -1)

print(ans)