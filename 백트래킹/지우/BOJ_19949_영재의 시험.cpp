#include <iostream>
#include <vector>

using namespace std;

int ans;
vector<int> answers;

using namespace std;

void dfs(int pre, int cnt, int depth, int score) {
    if(depth == 10) {
        if(score >= 5) ans++;
        return;
    }
    
    for(int i=1; i<=5; i++) {
        int tmpScore = score;
        if(answers[depth] == i) tmpScore++;
        
        if(pre!= i) {
            dfs(i, 1, depth+1, tmpScore);
        }
        else if(pre == i && cnt < 2) {
            dfs(i, 2, depth+1, tmpScore);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    answers.resize(10, 0);
    for(int i=0; i<10; i++) {
        cin >> answers[i];
    }
    
    dfs(0,0,0,0);
    cout << ans;
    return 0;
}