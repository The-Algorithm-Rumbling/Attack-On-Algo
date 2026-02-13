#include <iostream>
#include <vector>

using namespace std;

int N, M; 
vector<int> limits;
vector<string> names;

bool isOk(int num, int idx) {
    return num <= limits[idx];
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    limits.resize(N);
    names.resize(N);
    
    for(int i=0; i<N; i++) {
        string s; cin >> s;
        names[i] = s;
        int limit; cin >> limit;
        limits[i] = limit;
    }

    for(int i=0; i<M; i++) {
        int num; cin >> num;

        int left = 0; int right = N;
        string ans = "";
        while(left <= right) {
            int mid = (left + right)/2;

            if(isOk(num, mid)) {
                right = mid-1;
                ans = names[mid];
            } else {
                left = mid+1;
            }
        }
        cout << ans << "\n";
    }
    return 0;
}