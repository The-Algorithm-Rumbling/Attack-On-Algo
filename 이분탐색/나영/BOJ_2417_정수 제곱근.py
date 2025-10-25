def binary (l, r, num) : 
    if l > r : 
        return l

    mid = (l + r) // 2

    if mid ** 2 >= num : 
        return binary (l, mid-1, num)
    else : 
        return binary (mid+1, r, num)

n = int(input())
print(binary(0, n, n))