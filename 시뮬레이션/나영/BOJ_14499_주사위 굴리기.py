def check(r, c) : 
    return 0 <= r < n and 0 <= c < m

def find(d) : 
    global x, y, dice
    nr, nc = x + dr[d], y + dc[d]

    if check(nr, nc) : 
        x, y = nr, nc
        new_dice = []

        if d == 0 : 
            new_dice = [dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]]
        elif d == 1 : 
            new_dice = [dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]]
        elif d == 2 : 
            new_dice = [dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]]
        else : 
            new_dice = [dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]]

        print(new_dice[0])

        if grid[nr][nc] == 0 : 
            grid[nr][nc] = new_dice[5]
        else : 
            new_dice[5] = grid[nr][nc]
            grid[nr][nc] = 0
        dice = new_dice

n, m, x, y, k = map(int, input().split())

grid = [list(map(int, input().split())) for _ in range(n)]
direction = list(map(int, input().split()))
dr = [0, 0, -1, 1]
dc = [1, -1, 0, 0]
dice = [0] * 6

for d in direction : 
    find(d-1)

print()