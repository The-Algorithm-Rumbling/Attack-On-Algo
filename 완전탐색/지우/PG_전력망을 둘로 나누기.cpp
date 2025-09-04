#include <iostream>
#include <string>
#include <vector>

using namespace std;
vector<int> p;

int find(int a) {
    if(p[a] == a) {
        return a;
    } else {
        return p[a] = find(p[a]);
    }
}

void Union(int a, int b) {
    a = find(a); 
    b = find(b);
    p[b] = a;
}

int solution(int n, vector<vector<int>> wires) {
    int answer = n+1;
    int w = wires.size();
    
    for(int i=0; i<w; i++) {
        p.assign(n+1, 0);
        for(int a=1; a<=n; a++) {
            p[a] = a;
        }
        
        for(int k=0; k<w; k++) {
            if(k==i) continue;
            int a = wires[k][0]; int b = wires[k][1];
            Union(a,b);
        }
        
        int p1 = find(p[1]); int cnt1 = 0; int cnt2 =0;
        for(int a=1; a<=n; a++) {
            if(find(p[a]) == p1) cnt1++;
            else cnt2++;
        }
        
        answer = min(answer, abs(cnt1 - cnt2));
    }
    return answer;
}