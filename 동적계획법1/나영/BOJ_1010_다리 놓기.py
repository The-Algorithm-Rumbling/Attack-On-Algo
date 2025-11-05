def find(n, r) : 
    if dp[n][r] == 0 : 
        dp[n][r] = find(n-1, r-1) + find(n-1, r)
    return dp[n][r]

n = int(input())

for _ in range(n) : 
    a, b = map(int, input().split())
    dp = [[0] * (b+1) for _ in range(b+1)]
    for i in range(b+1) : 
        dp[i][0] = dp[i][i] = 1
    print(find(b, a))