#include <iostream>
#include <vector>

using namespace std;

int M = 'Z'-'A'+1;
vector<int> cnts(M, 0);
vector<int> cnts2(M, 0);

int main() {
    int N; string a; cin >> N >> a;
    for(int i=0; i<a.size(); i++) {
        cnts[a[i]-'A']++;
    }

    int ans = 0;
    for(int t=1; t<N; t++) {
        cnts2.assign(M, 0);
        string b; cin >> b;
        
        if(abs((int)a.size() - (int)b.size()) > 1) continue;
        
        for(int i=0; i<b.size(); i++) {
            cnts2[b[i]-'A']++;
        }

        int aMore = 0; int bMore = 0; 
        for(int i=0; i<M; i++) {
            if(cnts[i] > cnts2[i]) {
                aMore += (cnts[i] - cnts2[i]);
            } else if(cnts[i] < cnts2[i]) {
                bMore += (cnts2[i] - cnts[i]); 
            }
        }

        if(aMore <= 1 && bMore <= 1) ans++;
    }
    cout << ans;
    
    return 0;
}