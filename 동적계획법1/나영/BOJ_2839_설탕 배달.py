import sys
input = sys.stdin.readline

n = int(input())
inf = float('inf')
dp = [inf] * (n+1)

if n >= 3 : dp[3] = 1
if n >= 5 : dp[5] = 1

for i in range(6, n+1) : 
    dp[i] = min(dp[i-3], dp[i-5]) + 1

print(dp[n] if dp[n] != inf else -1)