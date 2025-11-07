from collections import deque
from itertools import permutations

def check(r, c, z) : 
    return 0 <= r < 5 and 0 <= c < 5 and 0 <= z < 5

def find(cube) : 
    dq = deque([(0, 0, 0, 0)])
    vis = [[[0] * 5 for _ in range(5)] for _ in range(5)]
    vis[0][0][0] = 1

    while dq : 
        r, c, z, cnt = dq.popleft()

        if cnt >= ans : return float('inf')

        if (r, c, z) == (4, 4, 4) : 
            return cnt

        for d in range(6) : 
            nr, nc, nz = r + dr[d], c + dc[d], z + dz[d]
            if check(nr, nc, nz) and cube[nz][nr][nc] and not vis[nz][nr][nc] : 
                vis[nz][nr][nc] = 1
                dq.append((nr, nc, nz, cnt+1))
    
    return float('inf')

def roll(arr) : 
    n = len(arr)
    new_arr = [[0] * n for _ in range(n)]

    for r in range(n) : 
        for c in range(n) : 
            new_arr[c][n - 1 - r] = arr[r][c]

    return new_arr

def dfs(idx, order, cube) : 
    global ans
    if ans == 12 : return
        
    if idx == 5 : 
        if cube[0][0][0] == 1 and cube[4][4][4] == 1 : 
            ans = min(ans, find(cube))
        return
    
    original = grid[order[idx]]
    rotated = [row[:] for row in original]
    for _ in range(4):
        cube.append([row[:] for row in rotated])
        dfs(idx + 1, order, cube)
        cube.pop()
        rotated = roll(rotated)
    

grid = [[list(map(int, input().split())) for _ in range(5)] for _ in range(5)]
dr = [-1, 0, 1, 0, 0, 0]
dc = [0, 1, 0, -1, 0, 0]
dz = [0, 0, 0, 0, -1, 1]
ans = float('inf')

for order in permutations(range(5)) : 
    dfs(0, order, [])

print(ans if ans != float('inf') else -1)