#include <iostream>
#include <vector>
#include <unordered_set>
#include <sstream>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N, M; cin >> N >> M;

    unordered_set<string> sets;
    for(int i=0; i<N; i++) {
        string s; cin >> s;
        sets.insert(s);
    }

    for(int i=0; i<M; i++) {
        string s; cin >> s;
        stringstream ss(s);
        string word;
        while(getline(ss, word, ',')) {
            if(sets.find(word) != sets.end()) {
                sets.erase(word);
            }
        }
        
        cout << sets.size() << "\n";
    }
    
    return 0;
}