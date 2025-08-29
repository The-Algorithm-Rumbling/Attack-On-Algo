#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> makeInt(string s) {
    vector<int> result(3,0);
    int idx = 0;
    for(int i=0; i<3; i++) {
        string tmp = s.substr(idx,2);
        result[i] = stoi(tmp);
        idx += 3;
    }
    return result;
}

int main() {
    vector<int> ans(3,0);

    string curTime;
    getline(cin, curTime);
    vector<int> curr = makeInt(curTime);
    
    string bombTime;
    getline(cin, bombTime);
    vector<int> bomb = makeInt(bombTime);

    if(curTime == bombTime) {
        cout << "24:00:00";
        return 0;
    }

    for(int i=2; i>=0; i--) {
        if(bomb[i] - curr[i] < 0) {
            if(i>0) {
                bomb[i-1]--;
                ans[i] = bomb[i]+60-curr[i];   
            } else {
                ans[i] = bomb[i]+24-curr[i];
            }
        } else {
            ans[i] = bomb[i]-curr[i];
        }
    }

    for(int i=0; i<3; i++) {
        if(ans[i] <=9 ) cout << "0";
        cout << ans[i];
        if(i!=2) cout << ":";
    }
    return 0;
}