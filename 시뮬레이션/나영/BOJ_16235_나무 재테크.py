from collections import deque
import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < n and 0 <= c < n

def winter () : 
    for r in range(n) : 
        for c in range(n) : 
            land[r][c] += foods[r][c]

def autumn (breed) : 
    for r, c in breed : 
        for d in range(8) : 
            nr, nc = r + dr[d], c + dc[d]
            if check(nr, nc) : 
                tree[nr][nc].appendleft(1)
                if (nr, nc) not in live_set : 
                    live.append((nr, nc))
                    live_set.add((nr, nc))

    winter()

def spring () : 
    global tree, live, live_set
    new_tree = [[deque() for _ in range(n)] for _ in range(n)]
    breed = []
    new_live = []
    
    for r, c in live : 
        dead_age = 0
        while tree[r][c] : 
            age = tree[r][c].popleft()
            if land[r][c] < age : 
                dead_age += age // 2
            else : 
                land[r][c] -= age
                new_tree[r][c].append(age+1)

                if (age+1) % 5 == 0 : breed.append((r, c))
                    
        land[r][c] += dead_age
        if new_tree[r][c] : 
            new_live.append((r, c))
            
    tree = new_tree
    live = new_live
    live_set = set(live)

    autumn(breed)


dr = [-1, 0, 1, 0, 1, -1, -1, 1]
dc = [0, -1, 0, 1, -1, 1, -1, 1]
n, m, k = map(int, input().split())

land = [[5] * n for _ in range(n)]
foods = [list(map(int, input().split())) for _ in range(n)]
tree = [[deque() for _ in range(n)] for _ in range(n)]
live = []
live_set = set()

for _ in range(m) : 
    r, c, age = map(int, input().split())
    tree[r-1][c-1].append(age)
    live.append((r-1, c-1))
    live_set.add((r-1, c-1))
    
while k : 
    spring()
    k-=1

ans = sum(len(tree[r][c]) for r in range(n) for c in range(n))

print(ans)