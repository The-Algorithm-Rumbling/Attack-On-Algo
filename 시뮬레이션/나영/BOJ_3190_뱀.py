from collections import deque
import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < n and 0 <= c < n
    
def move() : 
    global sec, r, c, dir, isS
    sec+=1
    nr = r + dr[dir]
    nc = c + dc[dir]

    if check(nr, nc) and snake[nr][nc] < 2 : 
        # 사과(1)를 먹지 않은 경우 꼬리 이동
        # 먹은 경우 꼬리는 놔두고 snake 값만 갱신
        if snake[nr][nc] == 0 : 
            tr, tc = path.pop(0)
            snake[tr][tc] = 0

        snake[nr][nc] = 2
        path.append((nr, nc))
        r, c = nr, nc
    else : 
        isS = 1
        return

def find(t, d) : 
    global dir
    while sec < t and not isS : 
        move()

    # 방향 변경
    dir = (dir - 1) % 4 if d == 'L' else (dir+1) % 4

dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

n = int(input())
k = int(input())
snake = [[0] * n for _ in range(n)]
path = [(0, 0)]

for _ in range(k) : 
    nr, nc = map(int, input().split())
    snake[nr-1][nc-1] = 1

l = int(input())

sec = r = c = 0
dir = 1
snake[0][0] = 2
isS = 0

for _ in range(l) : 
    t, d = input().split()
    find(int(t), d)

while not isS : 
    move()
    
print(sec)