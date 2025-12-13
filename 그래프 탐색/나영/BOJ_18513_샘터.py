from collections import deque
import sys
input = sys.stdin.readline

def bfs() : 
    global k
    ans = 0
    while dq and k > 0 : 
        loc, cnt = dq.popleft()

        for d in dr : 
            new_loc = loc + d
            if new_loc not in vis and k > 0 : 
                k-=1
                ans += cnt
                vis.add(new_loc)
                dq.append((new_loc, cnt+1))

    return ans

n, k = map(int, input().split())
arr = list(map(int, input().split()))

dq = deque()
dr = [-1, 1]
vis = set()

for loc in arr : 
    dq.append((loc, 1))
    vis.add(loc)

print(bfs())