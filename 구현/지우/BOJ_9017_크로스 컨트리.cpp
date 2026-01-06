#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int T, N;
vector<int> arr;
vector<int> cnts;
vector<int> scores;
vector<int> fifth;
unordered_set<int> sets;

int main() {
    cin >> T;

    while(T--) {
        cin >> N;
        arr.assign(N+1, 0);
        cnts.assign(201, 0); scores.assign(201, 0); fifth.assign(201, 0);
        sets.clear();

        for(int i=1; i<=N; i++) {
            int team; cin >> team;
            arr[i] = team;
            cnts[team]++;
        }

        for(int i=1; i<=N; i++) {
            int team = arr[i];
            if(cnts[team] < 6) sets.insert(team);
        }

        cnts.assign(201, 0);
        int score = 1;
        for(int i=1; i<=N; i++) {
            int team = arr[i];
            if(sets.find(team) != sets.end()) continue;
            cnts[team]++; 
            if(cnts[team] <= 4) {
                scores[team] += score;
            }
            else if(cnts[team] == 5) {
                fifth[team] = score;
            }
            score++;
        }

        int win = 0;
        int minScore = 98765432;
        int min5 = 98765432;
        for(int i=1; i<=200; i++) {
            if(cnts[i] < 6) continue;
            if(minScore > scores[i]) {
                minScore = scores[i];
                min5 = fifth[i];
                win = i;
            }

            else if(minScore == scores[i]) {
                if(min5 > fifth[i]) {
                    minScore = scores[i];
                    min5 = fifth[i];
                    win = i;
                }
            }
            
        }
        cout << win << "\n";
    }
    return 0;
}