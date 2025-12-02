def find(n) : 
    if n == 1 : return 1
    if n == 2 : return 2
    if dp[n] == 0 : dp[n] = (find(n-1) + find(n-2)) % 10007

    return dp[n]
    

n = int(input())
dp = [0] * (n+1)

print(find(n))