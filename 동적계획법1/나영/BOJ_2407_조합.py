import sys
input = sys.stdin.readline

def find (n, m) : 
    if dp[n][m] == 0 : 
        dp[n][m] = find(n-1, m-1) + find(n-1, m)

    return dp[n][m]

n, m = map(int, input().split())

dp = [[0] * (n+1) for _ in range(n+1)]

for i in range(n+1) : 
    dp[i][i] = dp[i][0] = 1

print(find(n, m))