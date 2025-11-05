import sys
input = sys.stdin.readline

def find(x) : 
    if x == 0 : 
        return stair[0]
    elif x == 1 : 
        return stair[0] + stair[1]
    elif x == 2 : 
        return stair[2] + max(stair[0], stair[1])
    elif dp[x] == 0 : 
        dp[x] = stair[x] + max(stair[x-1] + find(x-3), find(x-2))

    return dp[x]

n = int(input())
stair = [int(input()) for _ in range(n)]
dp = [0] * (n)

print(find(n-1))