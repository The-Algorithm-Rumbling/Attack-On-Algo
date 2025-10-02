#include <iostream>
#include <unordered_map>
#include <vector>
#include <sstream>
#include <string>
#include <unordered_set>

using namespace std;
int N, M;
unordered_map<string, vector<pair<int, string>>> m;
unordered_set<string> sets;
unordered_set<string> visFolder;
int cnt = 0;

void dfs(string node) {
    for(auto nxt : m[node]) {
        auto[type, f] = nxt;
        if(type == 1) {
            if(!visFolder.count(f)) {
                visFolder.insert(f);
                dfs(f);
            }
        } else {
            cnt++;
            sets.insert(f);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    int T = N+M;
    for(int i=0; i<T; i++) {
        string s; string f; int type;
        cin >> s >> f >> type;

        m[s].push_back({type, f});
    }

    int K; cin >> K;
    for(int i=0; i<K; i++) {
        vector<string> arr1; vector<string> arr2;
        string s1, s2; cin >> s1 >> s2;
        
        stringstream ss1(s1); string token = "";
        while(getline(ss1, token, '/')) {
            arr1.push_back(token);
        }
        stringstream ss2(s2); token = "";
        while(getline(ss2, token, '/')) {
            arr2.push_back(token);
        }

        unordered_set<string> tmpSets;
        for(auto origin : m[arr2[arr2.size()-1]]) {
            auto [type, f] = origin;
            tmpSets.insert(f);
        }
        
        for(auto move : m[arr1[arr1.size()-1]]) {
            auto [type, f] = move;
            if(!tmpSets.count(f)) {
                m[arr2[arr2.size()-1]].push_back(move);
                tmpSets.insert(f);
            }
        }
        m[arr1[arr1.size()-1]].clear();
    }

    int Q; cin >> Q;
    for(int i=0; i<Q; i++) {
        string s; cin >> s;

        vector<string> arr;
        stringstream ss(s); string token = "";
        while(getline(ss, token, '/')) {
            arr.push_back(token);
        }

        sets = unordered_set<string>();
        visFolder = unordered_set<string>(); 
        cnt = 0;
        
        visFolder.insert(arr[arr.size()-1]);
        dfs(arr[arr.size()-1]);
        cout << sets.size() << " " << cnt << "\n";
    }
    return 0;
}