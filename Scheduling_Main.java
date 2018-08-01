import java.util.*;

enum Algorithm {
	FCFS, 
	SJF,
	Prio,
	Deadline,
	PPrio,
	SRTF,
	RR
}//Algorithm

public class Scheduling_Main {
	
	static List<Process> processes = new ArrayList<Process>();
	static Algorithm algorithm;
	static int quantum;

	public static void main(String args[]) {
		
		//add processes
		//processes.add(new Job(process#, arrival_time, burst_time, priority, deadline));
		//if no deadline, put Double.POSITIVE_INFINITY
		
		processes.add(new Process(1, 0, 15, 3, Double.POSITIVE_INFINITY));
		processes.add(new Process(2, 1, 5, 3, Double.POSITIVE_INFINITY));
		processes.add(new Process(3, 3, 3, 4, Double.POSITIVE_INFINITY));
		processes.add(new Process(4, 4, 11, 1, Double.POSITIVE_INFINITY));
		processes.add(new Process(5, 5, 5, 1, Double.POSITIVE_INFINITY));
		processes.add(new Process(6, 5, 2, 2, Double.POSITIVE_INFINITY));
		processes.add(new Process(7, 6, 3, 5, Double.POSITIVE_INFINITY));
		quantum = 3;
		
		algorithm = Algorithm.FCFS;
		
		//algorithm = Algorithm.SJF;
		
		//algorithm = Algorithm.Prio;
		
		//algorithm = Algorithm.Deadline;
		
		//algorithm = Algorithm.PPrio;
		
		//algorithm = Algorithm.SRTF;
		
		//algorithm = Algorithm.RR;
			
		for(int i = 1; i <= processes.size(); i++)
			processes.get(i-1).setProcessNumber(i);
			
		CPU_Scheduling scenario = new CPU_Scheduling(processes, algorithm);
			
		//if scenario is proper, print GanttChart with TT, WT, CT
		if(scenario.solve()){
				
            System.out.println("\n" + algorithm + "\n");
			GanttChart.drawGanttChart(scenario.getGanttChart());
			System.out.println(scenario.getResultTT());
			System.out.println(scenario.getResultWT());
			System.out.println(scenario.getResultCT());
					
		}//if
			
	}//main

}//Scheduling_Main
