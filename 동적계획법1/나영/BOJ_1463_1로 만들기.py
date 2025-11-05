import sys
input = sys.stdin.readline

def find(x) : 
    dp[x] = dp[x-1] + 1
    if (x % 3 == 0) : 
        dp[x] = min(dp[x], dp[x//3] + 1)
    if (x % 2 == 0) : 
        dp[x] = min(dp[x], dp[x//2] + 1)

n = int(input())
dp = [0] * (n+1)

for i in range(2, n+1) : 
    find(i)
print(dp[n])