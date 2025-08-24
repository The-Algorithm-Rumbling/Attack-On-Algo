#include <iostream>
#include <vector>
#include <unordered_set>
#include <cmath>

using namespace std;

int N, S1, S2, K;
vector<int> Ns, Ks;
unordered_set<char> Kset;
long long ans = 0;
vector<long long> powers;

bool isK(long long num) {
    string s_num = to_string(num);
    for(char c : s_num) {
        if(Kset.find(c) == Kset.end()) return false;
    }
    return true;
}

void secondNum(long long num1, int depth, long long sum) {
    if(depth == S2) {
        string s_sum = to_string(sum);
        if(isK(sum) && s_sum.length() == Ns[N-1]) ans++;
        return;
    }

    for(int i=0; i<Ks.size(); i++) {
        long long add = num1 * Ks[i];

        string s_add = to_string(add);
        if(s_add.length() != Ns[2+depth]) continue;
        if(!isK(add)) continue;

        secondNum(num1, depth+1, sum + add * powers[depth]);
    }
    
}

void firstNum(int depth, long long num) {
    if(depth == S1) {
        secondNum(num, 0,0);
        return;
    }

    for(int i=0; i<Ks.size(); i++) {
        firstNum(depth+1, num*10 + Ks[i]);
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    Ns.resize(N, 0);
    for(int i=0; i<N; i++) {
        cin >> Ns[i];
    }
    S1 = Ns[0]; S2 = Ns[1];
    
    cin >> K;
    Ks.resize(K,0);
    for(int i=0; i<K; i++) {
        cin >> Ks[i];
        Kset.insert(Ks[i] + '0');
    }

    powers.push_back(1);
    for(int i=1; i<S2; i++) {
        powers.push_back(powers.back() * 10);
    }

    firstNum(0,0);
    
    cout << ans;
    
    return 0;
}