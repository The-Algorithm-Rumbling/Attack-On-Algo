#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;
int ka, kb, k;
int answer;
vector<vector<int>> maps(100, vector<int>(100,0));
int lenR; int lenC;

void C() {
    int tmpLenR = 0;
    for(int c=0; c<lenC; c++) {
        map<int,int> m;
        
        for(int r=0; r<lenR; r++) {
            int a = maps[r][c];
            if(a!=0) m[a]++;
        }

        vector<pair<int,int>> tmp;
        for(auto t : m) {
            auto[num, cnt] = t;
            tmp.push_back({cnt, num});
        }

        sort(tmp.begin(), tmp.end());

        vector<int> nxt;
        for(auto t : tmp) {
            auto[cnt, num] = t;
            nxt.push_back(num);
            nxt.push_back(cnt);
        }
        tmpLenR = max(tmpLenR, (int)nxt.size());
        if(nxt.size() > 100) tmpLenR = 100;
        
        nxt.resize(100,0);

        for(int r=0; r<100; r++) {
            maps[r][c] = nxt[r];
        }
        
    }

    lenR = tmpLenR;
}

void R() {
    int tmpLenC = 0;
    for(int r=0; r<lenR; r++) {
        map<int,int> m;
        
        for(int c=0; c<lenC; c++) {
            int a = maps[r][c];
            if(a!=0) m[a]++;
        }

        vector<pair<int,int>> tmp;
        for(auto t : m) {
            auto[num, cnt] = t;
            tmp.push_back({cnt, num});
        }
        sort(tmp.begin(), tmp.end());

        vector<int> nxt;
        for(auto t : tmp) {
            auto[cnt, num] = t;
            nxt.push_back(num);
            nxt.push_back(cnt);
        }
        tmpLenC = max(tmpLenC, (int)nxt.size());
        if(nxt.size() > 100) tmpLenC = 100;
        
        nxt.resize(100,0);
        maps[r] = nxt;
    }

    lenC = tmpLenC;
}

int main() {
    cin >> ka >> kb >> k;
    ka--; kb--;
    lenR = 3;
    lenC = 3;

    for(int r=0; r<3; r++) {
        for(int c=0; c<3; c++) {
            cin >> maps[r][c];
        }
    }

    while(1) {
        
        if(maps[ka][kb] == k) break;
        if(lenR >= lenC) {
            R();
        }
        else {
            C();
        }
        
        answer++;
        if(answer > 100) {
            answer = -1;
            break;
        }
    }

    cout << answer;
    
    return 0;
}