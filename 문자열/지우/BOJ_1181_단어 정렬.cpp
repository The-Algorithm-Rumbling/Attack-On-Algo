#include <iostream>
#include <vector>
#include <set>

using namespace std;

set<pair<int,string>> sets;

int main() {
    int N; cin >> N;
    while(N--) {
        string s; cin >> s;
        int len = (int)s.length();
        sets.insert({len, s});
    }

    for(auto s : sets) {
        cout << s.second << "\n";
    }
    
    return 0;
}