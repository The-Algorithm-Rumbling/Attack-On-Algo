def find() : 
    global fire, n
    arr = [[[] for _ in range(n)] for _ in range(n)]

    for r, c, m, s, d in fire : 
        nr = (r - 1 + dr[d] * s) % n
        nc = (c - 1 + dc[d] * s) % n
        arr[nr][nc].append((m, s, d))

    new_fire = []
    
    for r in range(n) : 
        for c in range(n) : 
            if len(arr[r][c]) == 1 : 
                m, s, d = arr[r][c][0]
                new_fire.append([r+1, c+1, m, s, d])
            elif len(arr[r][c]) >= 2 : 
                sumM = sum(x[0] for x in arr[r][c])
                sumS = sum(x[1] for x in arr[r][c])
                cnt = len(arr[r][c])
                dirs = [x[2] for x in arr[r][c]]

                nm = sumM // 5
                ns = sumS // cnt

                if nm == 0 : 
                    continue

                if all(d % 2 == 0 for d in dirs) or all(d % 2 != 0 for d in dirs) : 
                    ndirs = [0, 2, 4, 6]
                else : 
                    ndirs = [1, 3, 5, 7]

                for nd in ndirs : 
                    new_fire.append([r+1, c+1, nm, ns, nd])
    
    fire = new_fire

n, m, k = map(int, input().split())
fire = [list(map(int, input().split())) for _ in range(m)]

dr = [-1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(k) : 
    find()

print(sum(x[2] for x in fire))