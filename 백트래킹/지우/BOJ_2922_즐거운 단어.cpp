#include <iostream>
#include <vector>
#include <set>

using namespace std;
char alphs[] = {'A', 'B', 'L'};
string s; long long ans;
vector<char> happy;
vector<int> blanks;
set<char> cons;
set<char> vow;

bool check(char a, int pos) {
    if(cons.count(a)) {
        if(pos-2 >= 0) {
            if(cons.count(happy[pos-2]) && cons.count(happy[pos-1])) return false;
        }
        if(pos-1 >= 0 && pos+1 <s.size()) {
            if(cons.count(happy[pos-1]) && cons.count(happy[pos+1])) return false;
        }
        if(pos+2 < s.size()) {
            if(cons.count(happy[pos+1]) && cons.count(happy[pos+2])) return false;
        }
    }
    else if(vow.count(a)) {
        
        if(pos-2 >= 0) {
            if(vow.count(happy[pos-2]) && vow.count(happy[pos-1])) return false;
        }
        if(pos-1 >= 0 && pos+1 <s.size()) {
            if(vow.count(happy[pos-1]) && vow.count(happy[pos+1])) return false;
        }
        if(pos+2 < s.size()) {
            if(vow.count(happy[pos+1]) && vow.count(happy[pos+2])) return false;
        }
    }
    return true;
}

void dfs(int idx, int Lcnt, long long cnt) {
    if(idx == blanks.size()) {
        if(Lcnt >= 1) {
            ans += cnt;
        }
        return;
    }
    
    int pos = blanks[idx];
    for(auto a : alphs) {
        if(!check(a,pos)) continue;
        happy[pos] = a;
        if(a=='A') dfs(idx+1, Lcnt, cnt*5);
        if(a=='B') dfs(idx+1, Lcnt, cnt*20);
        if(a=='L') dfs(idx+1, Lcnt+1, cnt*1);
        happy[pos] = '\0';
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> s; 
    happy.resize(s.size(), '\0');

    for(int i=0; i<26; i++) {
        char a = 'A' + i;
        if(a == 'A' || a == 'E' || a == 'I' || a == 'O' || a == 'U') {
            vow.insert(a);
        } else {
            cons.insert(a);
        }
    }

    int Lcnt = 0;
    for(int i=0; i<s.length(); i++) {
        if(s[i] == 'L') Lcnt++;
        
        if(s[i] == '_') {
            blanks.push_back(i);
        } else {
            happy[i] = s[i];
            if(!check(s[i], i)) {
                cout << 0;
                exit(0);
            }
        }
    }

    dfs(0, Lcnt, 1);
    cout << ans;
    return 0;
}