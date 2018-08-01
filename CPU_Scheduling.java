import java.util.*;

public class CPU_Scheduling {
	
	List<Process>  processes; 
	Algorithm  algorithm;
	
	List<Process> processlist;
	PriorityQueue queue;
	GanttChart gantt_chart;
	
	List<Double> TT, WT, CT;
	String result_tt, result_wt, result_ct;
	double TTAvg, WTAvg, CTAvg;
	
	public CPU_Scheduling(List<Process> processlist, Algorithm algorithm){
		
		this.processes = copyList(processlist);
		this.algorithm = algorithm;
		
		this.processlist = copyList(processlist);
		this.queue = new PriorityQueue(algorithm);
		this.gantt_chart = new GanttChart();
		
		this.TT = new ArrayList<>();
		this.WT = new ArrayList<>();
		this.CT = new ArrayList<>();
		
		this.TTAvg = 0.0;
		this.WTAvg = 0.0;
		this.CTAvg = 0.0;
		
		this.result_tt = new String();
		this.result_wt = new String();
		this.result_ct = new String();
	
	}//CPU_Scheduling
	
	GanttChart getGanttChart(){
		
		return gantt_chart;
	
	}//getGanttChart
	
	String getResultTT(){
		
		return result_tt;
		
	}//getResultTT
	
	String getResultWT(){
		
		return result_wt;
	
	}//getResultWT
	
	String getResultCT(){
		
		return result_ct;
	
	}//getResultCT
	
	boolean solve(){
		
		sortArrival();
		if(algorithm == Algorithm.FCFS ||
			algorithm == Algorithm.SJF ||
			algorithm == Algorithm.Prio)
			
			nonPreemptiveAlgo();
		
		
		else if(algorithm == Algorithm.PPrio ||
				 algorithm == Algorithm.SRTF)
			
			preemptiveAlgo();
		
		
		else if(algorithm == Algorithm.RR)
			
			RR(Scheduling_Main.quantum);
		
		
		else if(algorithm == Algorithm.Deadline){
			
			preemptiveAlgo();
			boolean success = true;
			String prompt = "ERROR in deadline input\n\n";
			
			for(int i = 0; i < processes.size(); i++)	{
				if(processes.get(i).getProcessFinish() > processes.get(i).getDeadline()){
					
					success = false;
					prompt += "P" + processes.get(i).getProcessNumber() + " was not able to finish.\n";
				
				}//if
			}//for
			
			if(!success){	
                
                System.out.println(prompt);
				return false;
				
			}//if
		
		}//else if
		
		
		calculateTimes();
		return true;
		
	}//solve
	
	void nonPreemptiveAlgo(){
		
		Process tempProcess = new Process();
		double time = 0.0;
		double idle = 0.0;
		
		while(!processlist.isEmpty()){
			
			for(int i = 0; i < processlist.size(); i++){		
				
				if(processlist.get(i).getArrivalTime() <= time)
					queue.enqueue(processlist.get(i));
				else	
					break;
				
			}//for
			
			if(!queue.isEmpty()){
				
				tempProcess = queue.dequeue();
				processes.get(tempProcess.getProcessNumber()-1).setProcessStart(time);
				time += tempProcess.getBurstTime();
				gantt_chart.addTimeList(time);
				tempProcess.setProcessFinish(time);
				processes.get(tempProcess.getProcessNumber()-1).setProcessFinish(time);
				gantt_chart.addProcessList("P" + tempProcess.getProcessNumber());
				delete(tempProcess);
			
			}//if
			
			else{
				
				tempProcess = dequeue();
				processes.get(tempProcess.getProcessNumber()-1).setProcessStart(time);;
				idle = tempProcess.getArrivalTime() - time;
				gantt_chart.addProcessList("IDLE" + "\n   (" + idle +")");
				gantt_chart.addTimeList(time += idle);
				enqueue(tempProcess);
			
			}//else
		
		}//while
		
	}//nonpreemptiveAlgo
	
	void preemptiveAlgo(){
		
		Process tempProcess = new Process();
		Process nextProcess = new Process();
		double time = 0.0;
		double idle = 0.0;
		double temp = 0.0;
		
		while(processlist.isEmpty() != true){
			for(int i = 0; i < processlist.size(); i++){
				
				if(processlist.get(i).getArrivalTime() <= time) 		
					queue.enqueue(processlist.get(i));
				else	
					break;
			
			}//for
			
			if(queue.isEmpty() != true){
				tempProcess = queue.dequeue();
				
				for(int i = 0; i < processlist.size(); i++){
					if(processlist.get(i).getArrivalTime() > time){
						
						nextProcess = processlist.get(i);
						break;
					
					}//if
				
				}//for
				
				if(((time + tempProcess.getBurstTime()) > nextProcess.getArrivalTime()) 
						&& time < processlist.get(processlist.size()-1).getArrivalTime()){
					
					temp = nextProcess.getArrivalTime() - time;
					time = nextProcess.getArrivalTime();
					tempProcess.setBurstTime(tempProcess.getBurstTime() - temp);
					gantt_chart.addProcessList("P" + tempProcess.getProcessNumber());
					gantt_chart.addTimeList(time);
				
				}//if
				
				else{
					
					tempProcess.setProcessStart(time);
					time += tempProcess.getBurstTime();
					gantt_chart.addTimeList(time);
					tempProcess.setProcessFinish(time);
					processes.get(tempProcess.getProcessNumber()-1).setProcessFinish(time);
					gantt_chart.addProcessList("P" + tempProcess.getProcessNumber());
					delete(tempProcess);
				
				}//else
			}//if
			
			else{
				
				tempProcess = dequeue();
				idle = tempProcess.getArrivalTime() - time;
				gantt_chart.addProcessList("IDLE" + "\n   (" + idle +")");
				gantt_chart.addTimeList(time += idle);
				enqueue(tempProcess);
			
			}//else
			
		}//while
		
	}//preemptiveAlgo
	
	void RR(double quantum){
		
		Process tempProcess = new Process();
		double time = 0.0;
		double idle = 0.0;
		
		while(processlist.isEmpty() != true){
			
			if(processlist.get(0).getArrivalTime() <= time){
				tempProcess = dequeue();
				
				if(tempProcess.getBurstTime() != 0){
					if(tempProcess.getBurstTime() > quantum){
						
						time += quantum;
						gantt_chart.addTimeList(time);
						gantt_chart.addProcessList("P" + tempProcess.getProcessNumber());
						tempProcess.setBurstTime(tempProcess.getBurstTime() - quantum);
						tempProcess.setArrivalTime(time);
						enqueue(tempProcess);
						
					}//if
					
					else{
						
						time += tempProcess.getBurstTime();
						gantt_chart.addTimeList(time);
						gantt_chart.addProcessList("P" + tempProcess.getProcessNumber());
						tempProcess.setBurstTime(0.0);
						tempProcess.setProcessFinish(time);
						processes.get(tempProcess.getProcessNumber()-1).setProcessFinish(time);
						
						
					}//else
					
				}//if
				
			}//if
			
			else{
				
				tempProcess = dequeue();
				idle = tempProcess.getArrivalTime() - time;
				gantt_chart.addProcessList("IDLE" + "\n   (" + idle +")");
				gantt_chart.addTimeList(time += idle);
				enqueue(tempProcess);
			
			}//else
			
		}//while
		
	}//RR
	
	void calculateTimes(){
		String r = new String();
		
        ////////WT
		r = "Waiting Time (wt)\n";
		r += "finish time - arrival time - burst time\n\n";
		
		for(int i = 0; i < processes.size(); i++){
			WT.add(processes.get(i).getProcessFinish() - processes.get(i).getArrivalTime() - processes.get(i).getBurstTime());
			r += "wt" + processes.get(i).getProcessNumber() + "   " + 
					processes.get(i).getProcessFinish() + " - " + processes.get(i).getArrivalTime() + " - " + processes.get(i).getBurstTime() +
					" = " + WT.get(i) + "\n";
			
			WTAvg += WT.get(i);
		
		}//for
		
		WTAvg /= processes.size();
		r += "Waiting time Average: " + WTAvg + "\n\n";
		
		result_wt = r;

		////////TT
		r = "Turnaround Time (tt)\n";
		r += "finish time - arrival time\n\n";
		
		for(int i = 0; i < processes.size(); i++){
			TT.add( processes.get(i).getProcessFinish() - processes.get(i).getArrivalTime());
			r += "tt" + processes.get(i).getProcessNumber() + "   " + 
					processes.get(i).getProcessFinish() + " - " + processes.get(i).getArrivalTime() +
					" = " + TT.get(i) + "\n";
				
			TTAvg += TT.get(i);
		
		}//for
		
		TTAvg /= processes.size();
		r += "Turnaround time Average: " + TTAvg + "\n\n";
		
		result_tt = r;
		
    
		
		////////CT
		r = "Completion Time (ct)\n";
		r += "finish time\n\n";
		
		for(int i = 0; i < processes.size(); i++){
			CT.add( processes.get(i).getProcessFinish());
			r += "ct" + processes.get(i).getProcessNumber() + "   " + 
					processes.get(i).getProcessFinish()  +"\n";
			
			CTAvg += CT.get(i);
		
		}//for
		
		CTAvg /= processes.size();
		r += "Completion time Average: " + CTAvg + "\n\n";
		
		result_ct = r;
		
	}//calculateTimes
	
	
	void enqueue(Process process){
		
		processlist.add(process);
		sortArrival();

	}//enqueue
	
	
	Process dequeue(){
		
		Process temp = new Process();
		temp = processlist.get(0);
		processlist.remove(0);
		return temp;
		
	}//dequeue
	
	
	void delete(Process process){
		
		for(int i = 0; i < processlist.size(); i++)	
			if(process == processlist.get(i)){
				
				processlist.remove(i);
				break;
			
			}//if
		
	}//delete
	
	
	boolean isEmpty(){
		
		return processlist.isEmpty(); 
		
	}//isEmpty
	
	
	void sortArrival(){
		
		int i = 0, j = 0; 
		Process temp = new Process();
		for(i = 1; i < processlist.size(); i++){
			temp = processlist.get(i);
			j = i;
			while((j>0) && (processlist.get(j-1).getArrivalTime() > temp.getArrivalTime())){
				
					processlist.remove(j);
					processlist.add(j, processlist.get(j-1));
					j -= 1;
				
			}//while
			
			processlist.remove(j);
			processlist.add(j, temp);
			
			
		}//for
		
	}//sortArrival
	
	ArrayList<Process> copyList(List<Process> processes){
		
		ArrayList<Process> temp = new ArrayList<Process>();
		for(Process j : processes){
			
			temp.add(
					new Process(j.getProcessNumber(), j.getArrivalTime(),
							j.getBurstTime(), j.getProcessFinish(),
							j.getPriority(), j.getDeadline(), j.getProcessStart())
					);
		
		}//for
		
		return temp;
		
	}//copyList
	
}//CPU_Scheduling
