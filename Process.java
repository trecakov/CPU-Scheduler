
public class Process {
	
	private int process_number;
	private double arrival_time;
	private double burst_time;
	private double finish_time;
	private double start_time;
	private double priority;
	private double deadline;

	//constructors
	public Process(int process_number, double arrival_time, double burst_time,
			double priority, double deadline){
		
		this.process_number = process_number;
		this.arrival_time = arrival_time;
		this.burst_time = burst_time;
		this.finish_time = 0.0;
		this.start_time = 0.0;
		this.priority = priority;
		this.deadline = deadline;
	
	}//Process
	
	public Process(int process_number, double arrival_time, double burst_time,
			double finish_time, double priority, double deadline, double start_time){
		
		this.process_number = process_number;
		this.arrival_time = arrival_time;
		this.burst_time = burst_time;
		this.finish_time = finish_time;
		this.start_time = start_time;
		this.priority = priority;
		this.deadline = deadline;
		
	}//Process
	
	
	public Process(){
	
	}//Process
	
	//setters
	public void setProcessNumber(int n){
		
		process_number = n;
	
	}//Process
	
	public void setArrivalTime(double d){
		
		arrival_time = d;
	
	}//setArrivalTime
	
	public void setBurstTime(double d){
		
		burst_time = d;
		
	}//setBurstTime
	
	public void setProcessFinish(double d){
		
		finish_time = d;
		
	}//setProcessFinish
	
	public void setProcessStart(double d){
		
		start_time = d;
		
	}//setProcessStart
	
	public void setPriority(double d){
		
		priority = d;
	
	}//setPriority
	
	public void setDeadline(double d){
		
		deadline = d;
		
	}//setDeadline
	
	//getters
	public int getProcessNumber(){
		
		return process_number;	
	
	}//getProcessNumber
	
	public double getArrivalTime(){
		
		return arrival_time;
		
	}//getArrivalTime
	
	public double getBurstTime(){
		
		return burst_time;
		
	}//getBurstTime
	
	public double getProcessFinish(){
		
		return finish_time;
		
	}//getProcessFinish
	
	public double getProcessStart(){
		
		return start_time;
	
	}//getProcessStart
	
	public double getPriority(){
		
		return priority;
	
	}//getPriority
	
	public double getDeadline(){
		
		return deadline;
	
	}//getDeadline

}//Process
