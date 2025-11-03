#include <iostream>
#include <vector>
#include <tuple>

using namespace std;
int N; 
int ans;
vector<tuple<string,int,int>> answers;
vector<bool> vis(10, false);

bool isBall(string s, string num, int ball) {
    int cnt = 0;
    for(int i=0; i<3; i++) {
        for(int j=0; j<3; j++) {
            if(i!=j && s[i] == num[j]) cnt++;
        }
    }
    return cnt == ball;
}

bool isStrike(string s, string num, int strike) {
    int cnt = 0;
    for(int i=0; i<3; i++) {
        if(s[i] == num[i]) cnt++;
    }
    return cnt == strike;
}

bool check(string s) {
    for(int i=0; i<N; i++) {
        auto[num, strike, ball] = answers[i];
        if(!isStrike(s,num,strike) || !isBall(s,num,ball)) {
            return false;
        }
    }
    return true;
}

void dfs(string s) {
    if(s.size() == 3) {
        if(check(s)) ans++;
        return;
    }
    
    for(int i=1; i<=9; i++) {
        if(!vis[i]) {
            vis[i] = true;
            dfs(s+to_string(i));
            vis[i] = false;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    answers.resize(N, {"", 0, 0});

    for(int i=0; i<N; i++) {
        string num; int s; int b;
        cin >> num >> s >> b;
        answers[i] = {num, s, b};
    }

    dfs("");
    cout << ans;
    return 0;
}