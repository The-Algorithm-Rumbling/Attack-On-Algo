def dfs (idx, sour, bitter) : 
    global ans
    if idx == n : 
        if bitter != 0 : 
            ans = min(ans, abs(sour - bitter))
        return

    dfs (idx+1, sour, bitter) 

    s = sour * cook[idx][0]
    b = bitter + cook[idx][1]
    
    dfs (idx+1, s, b)
    
n = int(input())
ans = float('inf')
cook = [list(map(int, input().split(' '))) for _ in range(n)]
dfs(0, 1, 0)

print(ans)