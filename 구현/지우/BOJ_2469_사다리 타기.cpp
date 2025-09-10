#include <iostream>
#include <vector>

using namespace std;
int k; int n;
int R, C; int qr;
vector<vector<char>> maps;
string start = "";
string finish = "";
string ans = "";

void FromS() {
    int r=0; int c=0; 
    for(int r=0; r<R; r++) {
        if(r == qr) break;
        for(int c=0; c<C; c++) {
            if(maps[r][c] == '-') swap(start[c], start[c+1]);
        }
    }
}

void FromE() {
    int r=0; int c=0; 
    for(int r=R-1; r>=0; r--) {
        if(r == qr) break;
        for(int c=0; c<C; c++) {
            if(maps[r][c] == '-') swap(finish[c], finish[c+1]);
        }
    }
}

bool Find() {
    int idx = 0;
    for(int i=0; i<C; i++) {
        
        if(start[i] == finish[i]) ans[idx] = '*';
        else if(start[i] != finish[i] && start[i+1] == finish[i]) {
            ans[idx] = '-';
            swap(start[i], start[i+1]);
        } 
        else return false;
        idx++;
    }
    return true;
}

int main() {
    cin >> k >> n;
    R = n; C = k-1;
    maps.resize(R, vector<char>(C,'\0'));
    
    for(int i=0; i<k; i++) {
        start.push_back('A'+i);
    }
    cin >> finish;
    for(int i=0; i<C; i++) {
        ans.push_back('*');
    }
    
    for(int r=0; r<R; r++) {
        for(int c=0; c<C; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == '?') qr = r; 
        }
    }

    FromS();
    FromE();
    if(!Find()) {
        for(int i=0; i<C; i++) {
            ans[i] = 'x';
        }
    }

    cout << ans;
    return 0;
}