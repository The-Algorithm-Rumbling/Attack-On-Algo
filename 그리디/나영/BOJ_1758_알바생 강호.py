import sys
input = sys.stdin.readline

n = int(input())
tip = sorted([int(input()) for _ in range(n)], reverse=True)
ans = 0

for i in range(n) : 
    num = tip[i] - i
    if num > 0 : ans += num

print(ans)