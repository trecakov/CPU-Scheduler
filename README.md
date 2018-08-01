# CPU-Scheduler

CPU-Scheduler is designed to help students learn different scheduling algorithms as well as how to draw Gantt Chart and calculate 
waiting, turnaround and completion times. Scheduling alorithms that are implemented are:<br>
First Come First Serve (FCFS) <br>
Round Robin Algorithm (RR) <br>
Shortest Job First (SJF) <br>
Priority Algorithm (Prio) <br>
Preemptive Priority Algorithm (P-Prio) <br>
Shortest Remaining Time First Algorithm (SRTF) <br> 
Deadline Scheduling Algorithm <br>

### Installing

Download the github CPU-Scheduluer repository.

```
git clone https://github.com/trecakov/CPU-Scheduler.git
```

## How to compile

```
javac *java
```

## How to run

Modify Scheduling_Main.java with the scenario you need to run.

```
java Scheduling_Main
```

## Example
```
    Arrival_Time  Burst_Time  Priority
P1  0             15          3
P2  1             5           3
P3  3             3           4
P4  4             11          1
P5  5             5           1
P6  5             2           2
P7  6             3           5

Quantum = 3
```
The ouputs are:<br>
```
FCFS

0.0 |P1| 15.0 |P2| 20.0 |P3| 23.0 |P4| 34.0 |P5| 39.0 |P6| 41.0 |P7| 44.0

Turnaround Time (tt)
finish time - arrival time

tt1   15.0 - 0.0 = 15.0
tt2   20.0 - 1.0 = 19.0
tt3   23.0 - 3.0 = 20.0
tt4   34.0 - 4.0 = 30.0
tt5   39.0 - 5.0 = 34.0
tt6   41.0 - 5.0 = 36.0
tt7   44.0 - 6.0 = 38.0
Turnaround time Average: 27.428571428571427


Waiting Time (wt)
finish time - arrival time - burst time

wt1   15.0 - 0.0 - 15.0 = 0.0
wt2   20.0 - 1.0 - 5.0 = 14.0
wt3   23.0 - 3.0 - 3.0 = 17.0
wt4   34.0 - 4.0 - 11.0 = 19.0
wt5   39.0 - 5.0 - 5.0 = 29.0
wt6   41.0 - 5.0 - 2.0 = 34.0
wt7   44.0 - 6.0 - 3.0 = 35.0
Waiting time Average: 21.142857142857142


Completion Time (ct)
finish time

ct1   15.0
ct2   20.0
ct3   23.0
ct4   34.0
ct5   39.0
ct6   41.0
ct7   44.0
Completion time Average: 30.857142857142858
```
