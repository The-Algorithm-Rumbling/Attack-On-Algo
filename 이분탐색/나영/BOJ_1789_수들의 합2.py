def binary (l, r) : 
    global cnt
    if l > r : 
        return

    mid = (l + r) // 2

    if (mid * (mid + 1)) // 2 > s : 
        binary(l, mid-1)
    elif (mid * (mid + 1)) // 2 < s : 
        cnt = max(cnt, mid)
        binary(mid+1, r)
    else : 
        cnt = mid
        return

s = int(input())
cnt = 0
binary(0, s)
print(cnt)