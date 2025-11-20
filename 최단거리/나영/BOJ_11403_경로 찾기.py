import sys
input = sys.stdin.readline

def dfs(start, i) : 
    global n
    for j in range(n) : 
        if graph[i][j] == 1 and not vis[j]: 
            ans[start][j] = vis[j] = 1
            dfs(start, j)

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
ans = [[0] * n for _ in range(n)]

for i in range(n) : 
    vis = [0] * n
    dfs(i, i)

for row in ans : 
    print(*row)