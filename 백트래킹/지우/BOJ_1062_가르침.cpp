#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int N, K;
int maxAns = 0;
vector<vector<char>> totalWords;
unordered_set<char> totalChars_set; vector<char> totalChars;
vector<bool> picks(26, false);

int check() {
    int result = 0;
    
    for(int i=0; i<N; i++) {
        bool canRead = true;
        for(auto c : totalWords[i]) {
            if(!picks[c - 'a']) {
                canRead = false;
                break;
            }
        }
        if(canRead) result++;
    }

    return result;
}

void dfs(int idx, int sIdx) {
    if(sIdx == K) {
        maxAns = max(maxAns, check());
        return;
    }

    for(int i=idx; i<totalChars.size(); i++) { // totalChars = { r c h e l o };
        picks[totalChars[i] - 'a'] = true;
        dfs(i+1, sIdx+1);
        picks[totalChars[i] - 'a'] = false;
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> K;
    totalWords.resize(N);
    totalChars.clear();
    totalChars_set.clear();

    // antic 5글자는 무~조건 배워야 해

    for(int i=0; i<N ;i++) {
        unordered_set<char> tmp;
        
        string s; cin >> s;
        for(int j=4; j<s.length()-4; j++) { 
            if(s[j] == 'a' || s[j] == 'n' || s[j] == 't' || s[j] == 'i' || s[j] == 'c') continue;
            totalChars_set.insert(s[j]); // rc // helo // 
            tmp.insert(s[j]);
        }

        for(auto c : tmp) {
            totalWords[i].push_back(c);
        }
    }

    for(auto c : totalChars_set) {
        totalChars.push_back(c);
    }

    if(K < 5) {
        cout << 0;
        return 0;
    }
    
    if(K >= totalChars.size()+5) { // 총 나온 글자수보다 더 많이 배우면 - 다 배울 수 있음!
        cout << N;
        return 0;
    }

    picks['a' - 'a'] = true;
    picks['n' - 'a'] = true;
    picks['t' - 'a'] = true;
    picks['i' - 'a'] = true;
    picks['c' - 'a'] = true;
    
    dfs(0,5); // 이미 5개는 고른 상태로 ㄱㄱ
    cout << maxAns;
                                              
    return 0;
}