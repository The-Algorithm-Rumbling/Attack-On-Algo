#include <iostream>
#include <vector>

using namespace std;
int ans;
int N, K;
int N2;
vector<int> darr;
vector<int> robots;

int Solve() {
    int round = 0;
    while(1) {
        round++;
        
        // 1. 컨베이너벨트 이동
        int tmp = darr[N2-1];
        int tmp2 = robots[N2-1];
        for(int i=N2-2; i>=0; i--) {
            darr[i+1] = darr[i];
            robots[i+1] = robots[i];
        }
        darr[0] = tmp;
        robots[0] = tmp2;

        // 만약 N-1(내리는 위치)에 있다면 내린다. 
        if(robots[N-1] == 1) {
            robots[N-1] = 0;
        }
        
        // 2. 뒤에 있는 친구가 어차피 가장 올라간지 오래된 친구다.
        for(int i=N-2; i>=0; i--) {
            if(robots[i]==1 && darr[i+1] >=1 && robots[i+1] == 0) {
                robots[i] = 0;
                robots[i+1] = 1;
                darr[i+1]--;
            }
        }

        if(robots[N-1] == 1) {
            robots[N-1] = 0;
        }
    
        // 3. 올리기!
        if(darr[0] >= 1) {
            darr[0]--;    
            robots[0] = 1;
        }
    
        // 4. 내구도 개수 확인
        int zeroCnt = 0;
        for (int i = 0; i < N2; i++) {
            if (darr[i] == 0) {
                zeroCnt++;
            }
        }

        if(zeroCnt >= K) return round;
    }
}

int main() {
    cin >> N >> K;
    N2 = 2 * N;
    darr.resize(N2, 0);
    robots.resize(N2, 0);

    for(int i=0; i<N2; i++) {
        cin >> darr[i];
    }
    cout << Solve();
    return 0;
}