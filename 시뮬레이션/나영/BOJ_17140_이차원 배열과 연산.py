from collections import defaultdict
import sys
input = sys.stdin.readline
    
def align() : 
    global A
    a = len(A)
    b = len(A[0])
    
    if a >= b : 
        limit = 0
        for i in range(a) : 
            counter = defaultdict(lambda : 0)
            for j in range(b) : 
                if A[i][j] == 0 : continue
                counter[A[i][j]] += 1
    
            sorted_map = sorted(counter.items(), key=lambda x : (x[1], x[0]))
            new_arr = []
    
            for key, value in sorted_map : 
                new_arr.append(key) 
                new_arr.append(value)
            
            A[i] = new_arr
            limit = max(limit, len(new_arr))

        limit = min(limit, 100)
        for i in range(a) : 
            while len(A[i]) < limit : 
                A[i].append(0)
    
    else : 
        A = list(map(list, zip(*A)))
        align()
        A = list(map(list, zip(*A)))
                
        
r, c, k = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(3)]
sec = 0

while sec <= 100 : 
    if r <= len(A) and c <= len(A[0]) : 
        if A[r-1][c-1] == k : 
            print(sec)
            break
    align()
    sec+=1
else : print(-1)