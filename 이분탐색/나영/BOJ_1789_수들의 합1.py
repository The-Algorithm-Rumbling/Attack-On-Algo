n = int(input())
cnt = 0
i = 0

while n > i :
    i += 1
    n = n - i
    cnt+=1
print(cnt)