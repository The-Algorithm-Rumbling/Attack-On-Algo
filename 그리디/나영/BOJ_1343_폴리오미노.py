import sys
input = sys.stdin.readline

s = input()
ans = ''
i = 0

while i < len(s) : 
    if s[i] == 'X' : 
        cnt = 0
        while i < len(s) and s[i] == 'X' : 
            cnt+=1
            i+=1
        
        if cnt % 2 != 0 : 
            print(-1)
            exit(0)

        ans += 'AAAA' * (cnt // 4)

        if cnt % 4 != 0 : 
            ans += 'BB'
    else : 
        ans += s[i]
        i+=1

print(ans)