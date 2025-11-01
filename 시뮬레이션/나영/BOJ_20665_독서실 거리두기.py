def change(n) : 
    a = n // 100
    b = n % 100

    return a * 60 + b

def find() : 
    global seat, n
    maxDist = maxId = -1

    for i in range(n) : 
        if seat[i] == 0 : 
            left = right = i
            dist = 100
            
            while left >= 0 : 
                if seat[left] == 1 : 
                    dist = min(dist, i - left)
                    break
                left-=1
                
            while right < n : 
                if seat[right] == 1 : 
                    dist = min(dist, right - i)
                    break
                right+=1

            if dist > maxDist or (dist == maxDist and i < maxId) :
                maxDist = dist
                maxId = i

    return maxId
            

n, t, p = map(int, input().split())
p-=1
library = [[0, 0] for _ in range(t)]
seat = [0] * (n)
people = []
avaliable = change(2100) - change(900)

for i in range(t) : 
    a, b = map(int, input().split())
    library[i][0] = change(a)
    library[i][1] = change(b)

library.sort(key = lambda x : (x[0], x[1]))

for come, out in library : 
    for s, leave in people : 
        if leave <= come :
            seat[s] = 0;

    people = [(s, leave) for s, leave in people if leave > come]

    if (len(people) == n) : continue

    sit = find()
    seat[sit] = 1
    people.append((sit, out))

    if sit == p : 
        avaliable -= out - come

print (avaliable)