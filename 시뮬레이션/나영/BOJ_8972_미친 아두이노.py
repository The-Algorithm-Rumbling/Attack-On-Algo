import sys
input = sys.stdin.readline

def check(r, c) : 
    return 0 <= r < R and 0 <= c < C

def reMap() : 
    global grid, robots, x, y
    new_robots = set()
    new_grid = [['.'] * C for _ in range(R)]
    ex_grid = [[0] * C for _ in range(R)]
    new_grid[x][y] = 'I'

    for r, c in robots : 
        min = 200
        r1 = c1 = 0
        
        for d in range(9) : 
            nr, nc = r + dr[d], c + dc[d]

            ## 8방향 중 더 가까운 방향 선택 후
            ## r1, c1에 해당 좌표 추가
            if check(nr, nc) and min > abs(x - nr) + abs(y - nc) : 
                min = abs(x - nr) + abs(y - nc)
                r1, c1 = nr, nc

        ## 가장 가까운 좌표로 이동했을 때 종두이노가 있는지 확인
        if new_grid[r1][c1] == 'I' : 
            return 0


        ## 없으면 해당 좌표 추가
        new_robots.add((r1, c1))
        ex_grid[r1][c1]+=1

    ## new_list에 담긴 좌표 중 미두이노가 2개 이상인 경우
    ## 해당 좌표 삭제 후 미두이노 삭제
    l = len(new_robots)

    for i in range(l) : 
        r, c = new_robots.pop()
        if ex_grid[r][c] >= 2 : continue
        new_robots.add((r, c))
        new_grid[r][c] = 'R'

    ## robots, grid 갱신
    robots = new_robots
    grid = new_grid
    return 1


dr = [1, 1, 1, 0, 0, 0, -1, -1, -1]
dc = [-1, 0, 1, -1, 0, 1, -1, 0, 1]

R, C = map(int, input().split())
grid = [[] for _ in range(R)]
robots = set()
x = y = 0

for i in range(R) : 
    grid[i] = list(input().strip())
    for j in range(C) : 
        if grid[i][j] == 'I' : 
            x, y = i, j
        elif grid[i][j] == 'R' : 
            robots.add((i, j))

move = list(map(int, list(input().strip())))

for i in range(len(move)) : 
    d = move[i] - 1
    nx, ny = x + dr[d], y + dc[d]
    if grid[nx][ny] == 'R' : 
        print(f'kraj {i+1}')
        exit(0)

    grid[x][y] = '.'
    grid[nx][ny] = 'I'
    x, y = nx, ny
        
    if not reMap() : 
        print(f'kraj {i+1}')
        exit(0)

for row in grid : 
    print(''.join(row))