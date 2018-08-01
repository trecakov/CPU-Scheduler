import java.util.*;

public class PriorityQueue {
	
	List<Process> queue;
	Algorithm algorithm;
	
	public PriorityQueue(Algorithm algo){
		
		queue = new ArrayList<Process>();
		algorithm = algo;
	
	}//PriorityQueue
	
	public void enqueue(Process process){
		
		boolean done = false;
		
		for(int i = 0; i < queue.size(); i++){
			if(process == queue.get(i))
				done = true;
		}//for
		
		if(done == false){
			queue.add(process);
			sort();
		}//if
		
	}//enqueue
	
	public Process dequeue(){
		
		Process temp = new Process();
		temp = queue.get(0);
		queue.remove(0);
		return temp;
		
	}//dequeue
	
	public boolean isEmpty(){
		
		return queue.isEmpty(); 
	
	}//isEmpty
	
	public void printQueue(){
		
		for(Process process : queue)
			System.out.println("P" + process.getProcessNumber());
	
	}//printQueue
	
	public void sort(){
		
		int i = 0, j = 0; 
		if(algorithm == Algorithm.FCFS || algorithm == Algorithm.RR){
			Process temp = new Process();
			for(i = 1; i <queue.size(); i++){
				temp = queue.get(i);
				j = i;
				while((j>0) && (queue.get(j-1).getArrivalTime() > temp.getArrivalTime())){
					
					queue.remove(j);
					queue.add(j, queue.get(j-1));
					j -= 1;
				}//while
				
				queue.remove(j);
				queue.add(j, temp);
			}//for
			
		}//if
		
		else if(algorithm == Algorithm.SJF || algorithm == Algorithm.SRTF){
			Process temp = new Process();
			for(i = 1; i < queue.size(); i++){
				temp = queue.get(i);
				j = i;
				while((j>0) && (queue.get(j-1).getBurstTime() > temp.getBurstTime())){
					
					queue.remove(j);
					queue.add(j, queue.get(j-1));
					j -= 1;
				}//while
				
				queue.remove(j);
				queue.add(j, temp);
			}//for
			
		}//if
		
		else if(algorithm == Algorithm.Prio || algorithm == Algorithm.PPrio){
			Process temp = new Process();
			for(i = 1; i < queue.size(); i++){
				temp = queue.get(i);
				j = i;
				while((j>0) && (queue.get(j-1).getPriority() < temp.getPriority())){
					
					queue.remove(j);
					queue.add(j, queue.get(j-1));
					j -= 1;
				}//while
				
				queue.remove(j);
				queue.add(j, temp);
			}//for
		
		}//if
		
		else if(algorithm == Algorithm.Deadline){
			Process temp = new Process();
			for(i = 1; i < queue.size(); i++){
				temp = queue.get(i);
				j = i;
				while((j>0) && (queue.get(j-1).getDeadline() > temp.getDeadline())){
					
					queue.remove(j);
					queue.add(j, queue.get(j-1));
					j -= 1;
				}//while
				
				queue.remove(j);
				queue.add(j, temp);
			}//for
		
		}//if
	
	}//sort	
	
}//PriorityQueue
