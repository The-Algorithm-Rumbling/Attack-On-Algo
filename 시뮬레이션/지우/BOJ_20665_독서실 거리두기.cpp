#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <map>
#include <tuple>
using namespace std;

int N, T, P;
vector<pair<int,int>> arr;
vector<int> times;
vector<vector<int>> maps;
map<int,int> m;

bool compare(const tuple<int,int>&a, const tuple<int,int>& b) {
    int c = get<0>(a); int d = get<0>(b);
    if(c != d) {
        return c > d;
    }

    return get<1>(a) < get<1>(b);
}

int diff(int a, int b) {
    int h1 = a/100; int m1 = a - h1*100;
    int h2 = b/100; int m2 = b - h2*100;

    return (h2*60+m2) - (h1*60+m1);
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> T >> P;
    P--;
    arr.resize(T);
    set<int> sets;
    sets.insert(900);
    sets.insert(2100);
    
    for(int i=0; i<T; i++) {
        int a, b;
        cin >> a >> b;
        arr[i] = {a,b};
        sets.insert(a);
        sets.insert(b);
    }
    
    sort(arr.begin(), arr.end());
    
    for(auto s : sets) {
        times.push_back(s);
    }
    
    sort(times.begin(), times.end());
    
    for(int i=0; i<times.size(); i++) {
        m[times[i]] = i;
    }
    
    maps.resize(N, vector<int>(times.size(), 0));
    
    for(int t=0; t<T; t++) {
        auto[start, end] = arr[t];

        int cnt = 0;
        int sIdx = m[start];
        vector<int> curr(N, 0); // 시작 시간에 자리 차있는 형태 

        for(int s=0; s<N; s++) {
            if(maps[s][sIdx] == 1) {
                curr[s] = 1;
                cnt++;
            }
        }
        
        if(cnt == 0) {
            for(int i=m[start]; i<m[end]; i++) {
                maps[0][i] = 1;
            }
        }
        else {
            vector<tuple<int,int>> candidates; // (가장 가까운 사람과의 거리가)가장 먼 거리, (자리 번호가) 가장 작은 번호

            for(int seat=0; seat<N; seat++) {
                if(curr[seat] == 1) continue; 

                // 좌우 탐색해서 가장 가까운 사람 찾기 
                int dist_left = N+1; 
                for(int left = seat-1; left>=0; left--) {
                    if(curr[left] == 1) {
                        dist_left = seat - left;
                        break;
                    }
                }

                int dist_right = N+1; 
                for(int right = seat+1; right<N; right++) {
                    if(curr[right] == 1) {
                        dist_right = right - seat;
                        break;
                    }
                }

                int dist = min(dist_left, dist_right);
                candidates.push_back({dist, seat});
            }

            sort(candidates.begin(), candidates.end(), compare);
            auto[dis, best] = candidates[0];
            for(int r=m[start]; r<m[end]; r++) {
                maps[best][r] = 1;
            }
            
        }
    }

    int answer = 0;
    for(int c=0; c<times.size()-1; c++) {
        if(maps[P][c] == 0) {
            answer += diff(times[c], times[c+1]);
        }
    }
    cout << answer;
    
    return 0;
}