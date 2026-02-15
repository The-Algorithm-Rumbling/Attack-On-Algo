#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N, K;
vector<int> d;
vector<int> belt;

int checkBlank() {
    int cnt = 0;
    for(int i=0; i<2*N; i++) {
        if(d[i] == 0) cnt++;
    }
    return cnt;
}

void moveBelt() {
    int tmp = d[2*N-1];
    for(int i=2*N-2; i>=0; i--) {
        d[i+1] = d[i];
    }
    d[0] = tmp;

    for(int i=N-2; i>=0; i--) {
        belt[i+1] = belt[i];
    }
    belt[0] = 0; belt[N-1] = 0;
}

void moveRobot() {
    for(int i=N-2; i>=0; i--) {
        if(belt[i] == 0) continue;
        
        if(belt[i+1] == 0 && d[i+1] >= 1) {
            belt[i] = 0;
            belt[i+1] = 1;
            d[i+1]--;
        }
    }
    belt[N-1] = 0;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> K;

    d.resize(2*N, 0); belt.resize(N, 0);
    for(int i=0; i<2*N; i++) {
        cin >> d[i];
    }
    
    int ans = 0;
    while(1) {
        ans++;
        
        moveBelt();
        moveRobot();
        if(d[0] > 0) {
            belt[0] = 1;
            d[0]--;
        }

        if(checkBlank() >= K) break;
    }     
    cout << ans;
    
    return 0;
}