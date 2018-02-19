## cpu scheduler
- Solution
	0. pre process
		- sort based on arrival time
	1. update based on queue
		!queue.isEmpty || i < len
	2. Calculate Turn Around and Waiting



- wiki: https://en.wikipedia.org/wiki/Round-robin_scheduling
- C++
	- define class
	- sort list of object
	- define queue
		- create, push, pop, peek, isEmpty

```
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

class Process
{
    char pid;
    int brust;
    int reminder;
    int arrival;
    int start;
    int end;
    int turnAround;
    int waiting;

public:

    Process(char p, int b, int a, int s, int e, int t, int w)
            :pid(p), brust(b),reminder(b), arrival(a), start(s), end(e), turnAround(t), waiting(w){};

    void print(Process * process)
    {
        cout << process->pid << endl;
    }

    static void printSummary(const vector<Process *> myVector)
    {
        cout << "PID\t\t" << "TT\t\t\t" << "WT" << endl;

        int TTsum = 0, WTsum = 0;
        for (auto& it : myVector) {
            TTsum += it->turnAround;
            WTsum += it->waiting;
            cout << it->pid << "\t\t" << it->turnAround << "\t\t\t" << it->waiting << endl;
        }
        cout << "Ave\t\t" << TTsum << "/" << myVector.size() << "=" << 1.0 * TTsum/myVector.size() << "\t"
                        << WTsum << "/" << myVector.size() << "=" << 1.0 * WTsum/myVector.size() << endl;
    }

    static bool compare_arrival(const Process * a, const Process * b)
    {
        return a->arrival - b->arrival < 0;
    }

    static bool compare_brust(const Process * a, const Process * b) {
        return a->brust - b->brust < 0;
    }

    struct Comparator
    {
        bool operator()(const Process* lhs, const Process* rhs)
        {
            return lhs->brust > rhs->brust;
        }
    };

    static void SRTFScheduler(const vector<Process *> myVector, int quantum)
    {
        priority_queue<Process *,vector<Process *>, Comparator> pq;
        int T = -1;
        int index = 0;
        int len = myVector.size();

        while (pq.size() != 0 || index < len)
        {
            if (pq.size() != 0)
            {
                Process *top = pq.top();
                if (top->start == -1)
                {
                    top->start = T;
                }
                -- top->reminder;

                pq.pop();
                if (top->reminder == 0)
                {
                    top->end = T;
                    top->turnAround = top->end - top->arrival;
                    top->waiting = top->start - top->arrival;
//                    pq.pop();
                }
                else {
                    pq.push(top);
                }
            }
            ++ T;
            // fill arrival processes to queue
            for (;index < len && (myVector[index]->arrival) <= T; ++ index)
            {
                pq.push(myVector[index]);
            }
        }
    }

    static void SJFScheduler(const vector<Process *> myVector, int quantum)
    {
        priority_queue<Process *,vector<Process *>, Comparator> pq;
        int T = -1;
        int index = 0;
        int len = myVector.size();

        while (pq.size() != 0 || index < len)
        {
            if (pq.size() != 0)
            {
                Process *top = pq.top();
                top->start = T;
                T += top->brust;
                top->end = T;
                top->turnAround = top->end - top->arrival;
                top->waiting = top->start - top->arrival;

                pq.pop();
            }
            else
            {
                ++ T;
            }

            // fill arrival processes to queue
            for (;index < len && (myVector[index]->arrival) <= T; ++ index)
            {
                pq.push(myVector[index]);
            }
        }
    }

    static void RRScheduler(const vector<Process *> myVector, int quantum)
    {
        queue<Process *> q;
        int T = -1;
        int index = 0;
        int len = myVector.size();
        while (q.size() != 0 || index < len)
        {
            Process *front = nullptr;
            if (q.size() != 0)
            {
                front = q.front();
                if (front->start == -1)
                {
                    front->start = T;
                }
                for (int i = 0; i < quantum && front->reminder > 0; ++ i)
                {
                    -- front->reminder;
                    ++ T;
                }
                if (front->reminder == 0)
                {
                    front->end = T;
                    front->turnAround = front->end - front->arrival;
                    front->waiting = front->start - front->arrival;
                }
                q.pop();
            }
            else
            {
                ++ T;
            }

            // fill arrival processes to queue
            for (;index < len && (myVector[index]->arrival) <= T; ++ index)
            {
                q.push(myVector[index]);
            }

            if (front != nullptr && front->reminder > 0)
            {
                q.push(front);
            }
        }

    }

};

int main() {
    cout<<"Hello World\n";

    const int SIZE = 5;
    char pid[SIZE] = {'A', 'B', 'C', 'D', 'E'};
    int brust[SIZE] = {10, 1, 2, 1, 5};
    int arrival[SIZE] = {0, 1, 3, 0, 1};

    vector<Process *> myVector;
    for(int i = 0; i < 5; ++ i) {
        Process *rr = new Process(pid[i], brust[i], arrival[i], -1, -1, -1, -1);
        myVector.push_back(rr);
    }

    sort(myVector.begin(), myVector.end(), Process::compare_arrival);
//    Process::RRScheduler(myVector, 3);
//    Process::printSummary(myVector);

//    Process::SJFScheduler(myVector, 3);
//    Process::printSummary(myVector);

    Process::SRTFScheduler(myVector, 3);
    Process::printSummary(myVector);

    return 0;
}

```