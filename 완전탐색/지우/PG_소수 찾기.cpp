#include <string>
#include <vector>
#include <unordered_set>
#include <cmath>
#include <iostream>

using namespace std;

string s;
vector<bool> vis;
unordered_set<int> sets;

bool isPrime(int num) {
    for(int i=2; i <= sqrt(num); i++) {
        if(num % i == 0) return false;
    }
    return true;
}

void dfs(int num) {
    if(num > 1) {
        if(isPrime(num)) {
            sets.insert(num);
        }
    }
    
    for(int i=0; i<s.size(); i++) {
        if(!vis[i]) {
            vis[i] = true;
            dfs(num*10 + s[i]-'0');
            vis[i] = false;
        }
    }
}

int solution(string numbers) {
    vis.resize(numbers.size(), false);
    s = numbers;
    
    dfs(0);
    int answer = sets.size();
    return answer;
}