import sys
input = sys.stdin.readline

n = int(input())
rope = sorted([int(input()) for _ in range(n)])
ans = 0

for i in range(n) : 
    ans = max(ans, rope[i] * (n-i))
    
print(ans)