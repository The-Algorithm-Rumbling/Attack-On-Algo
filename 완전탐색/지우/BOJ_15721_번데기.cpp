#include <iostream>
#include <vector>

using namespace std;
int A;
int T;
int Find;
int bd[] = {0,1,0,1,0,1};
int Round = 1; 
int pos = 0; 
int bidx = 0;

int Solve() {
    while(1) {
        if(bidx >= 6) {
            bidx = 0;
            Round++;
        }
        if(pos >= A) pos = 0;

        if(bidx < 4) {
            if(bd[bidx] == 0) {
                if(Find == 0) {
                    T--;
                    if(T==0) return pos;
                }
                pos++;
                bidx++;
            }
            else if(bd[bidx] == 1) {
                if(Find == 1) {
                    T--;
                    if(T==0) return pos;
                }
                pos++;
                bidx++;
            }
        }
        
        if(bidx == 4) {
            int n = Round + 1;
            
            while(n--) {
                if(Find == bd[bidx]) {
                    T--;
                    if(T==0) return pos;
                }
                pos++;
                if(pos >= A) pos = 0;
            }
            bidx++;
        }

        if(bidx == 5) {
            int n = Round + 1;
            
            while(n--) {
                if(Find == bd[bidx]) {
                    T--;
                    if(T==0) return pos;
                }
                pos++;
                if(pos >= A) pos = 0;
            }
            bidx++;
        }
    }
}

int main() {
    cin >> A >> T >> Find;
    cout << Solve();
    
    return 0;
}