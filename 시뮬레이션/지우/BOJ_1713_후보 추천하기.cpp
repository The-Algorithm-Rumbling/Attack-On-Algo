#include <iostream>
#include <vector>
#include <set>

using namespace std;

int INF = 98765432;
int N, R;
set<int> sets;
vector<int> recommends;
vector<int> times;

int main() {
    recommends.resize(101,  0);
    times.resize(101, INF);

    cin >> N >> R;
    int time = 1;
    for(int i=0; i<R; i++) {
        int a; cin >> a;
        
        if(sets.count(a)) {
            recommends[a]++;
        } else {
            if(sets.size() == N) {
                // 현재 들어있는 후보들 중 가장 추천수 적은 친구
                int minR = INF;
                for(auto s : sets) {
                    minR = min(minR, recommends[s]);
                }
                vector<int> tmps;
                for(auto s : sets) {
                    if(recommends[s] == minR) {
                        tmps.push_back(s);
                    }
                }

                // 이 중 제일 오래된 사진
                int minTime = INF; int old = 0;
                for(auto t:tmps) {
                    if(minTime > times[t]) {
                        minTime = times[t];
                        old = t;
                    }
                }

                recommends[old] = 0;
                times[old] = INF;
                sets.erase(old);
            }
            recommends[a]++;
            times[a] = time;
            sets.insert(a);
            
            time++;
        }
    }

    for(auto s : sets) {
        cout << s << " ";
    }
    return 0;
}