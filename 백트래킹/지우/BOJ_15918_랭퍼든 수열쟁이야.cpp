#include <iostream>
#include <vector>

using namespace std;
int ans;
int n, x, y;
vector<int> nums;
vector<bool> used;

void dfs(int pos, int cnt) {  
    if(cnt == 2*n) {
        ans++;
        return;
    }

    if(nums[pos] == 0) {
        for(int i=1; i<=n; i++) {
            int npos = pos+i+1;
            if(!used[i] && npos<2*n && nums[npos] ==0) {
                used[i] = true;
                nums[pos] = i; nums[npos] = i;
                dfs(pos+1, cnt+2);
    
                
                used[i] = false;
                nums[pos] = 0; nums[pos+i+1] = 0;
            }
        }
    } else {
        dfs(pos+1, cnt);
    }
}

int main() {
    cin >> n >> x >> y;
    nums.resize(2*n, 0);
    used.resize(n+1, false);

    x--; y--;
    int i = y-x-1;
    used[i] = true;
    nums[x] = i; nums[y] = i;
    
    dfs(0,2);
    cout << ans;
    return 0;
}