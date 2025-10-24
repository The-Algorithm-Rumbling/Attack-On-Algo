def binary (l, r, num) : 
    while l <= r : 
        mid = (l + r) // 2
        if cards[mid] > num : 
            r = mid - 1 
        elif cards[mid] < num : 
            l = mid + 1 
        else : 
            return 1 
    return 0
    
n = int(input())
cards = sorted(list(map(int, input().split())))
m = int(input())
arr = list(map(int, input().split()))

for ex in arr : 
    print(binary(0, n-1, ex), end=' ') 