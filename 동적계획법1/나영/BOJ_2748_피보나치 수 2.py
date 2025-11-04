import sys
input = sys.stdin.readline

def find(n) : 
    if n == 0 : 
        return 0
    elif n == 1 : 
        dp[1] = 1
        return 1
    elif dp[n] == 0 : 
        dp[n] = find(n-1) + find(n-2)
    return dp[n]

n = int(input())
dp = [0] * 91

find(n)

print(dp[n])