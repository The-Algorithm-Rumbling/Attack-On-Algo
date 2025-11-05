import sys
input = sys.stdin.readline

n = int(input())
if n == 1:
    print("SK")
elif n == 2:
    print("CY")
else : 
    dp = [0] * (n+1)
    
    dp[1] = dp[3] = 1
    
    for i in range(4, n+1) : 
        dp[i] = (not dp[i-1]) or (not dp[i-3])
    
    print('SK' if dp[n] else 'CY')