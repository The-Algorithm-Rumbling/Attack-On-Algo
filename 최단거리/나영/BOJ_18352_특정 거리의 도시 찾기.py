import sys
from collections import deque
input = sys.stdin.readline

def find() : 
    global n, x, k, city
    ans = []
    que = deque([(x, 0)])
    vis = [0] * (n+1)
    vis[x] = 1

    while que : 
        now, d = que.popleft()

        if d == k : 
            ans.append(now)
            continue

        for i in city[now] : 
            if vis[i] : continue
            vis[i] = 1
            que.append((i, d+1))
            
    return ans

n, m, k, x = map(int, input().split())
city = [[] for _ in range(n+1)]

for i in range(m) : 
    a, b = map(int, input().split())
    city[a].append(b)
    
ans = sorted(find())
if ans : 
    print("\n".join(map(str, ans)))
else : print(-1)