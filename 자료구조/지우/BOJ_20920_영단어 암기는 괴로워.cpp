#include <iostream>
#include <vector>
#include <map>
#include <tuple>
#include <algorithm>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N, M; cin >> N >> M;

    map<string, int> maps;
    for(int i=0; i<N; i++) {
        string s; cin >> s;
        if(s.length() < M) continue;

        maps[s]++;
    }

    vector<tuple<int, int, string>> arr;
    for(pair<string,int> m : maps) {
        string s = m.first;
        int cnt = m.second;
        arr.push_back({cnt, s.length(), s});
    }

    sort(arr.begin(), arr.end(), [](auto &a, auto &b) {
        if(get<0>(a) != get<0>(b)) {
            return get<0>(a) > get<0>(b);
        }

        if(get<1>(a) != get<1>(b)) {
            return get<1>(a) > get<1>(b);
        }

        return get<2>(a) < get<2>(b);
    });

    for(auto ans : arr) {
        auto[cnt, len, s] = ans;
        cout << s << "\n";
    }
    
    return 0;
}