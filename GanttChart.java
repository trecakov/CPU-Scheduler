import java.util.*;

public class GanttChart {
	
	List<String> processlist;
	List<String> timelist;
	
	public GanttChart(){
		
		processlist = new ArrayList<String>();
		timelist = new ArrayList<String>();
	
	}//GanttChart
	
	public void addProcessList(String process){
		
		processlist.add(process);
	
	}//addProcessList
	
	public void addTimeList(double time){
		
		timelist.add("" + time);
	
	}//addTimeList
	
	public List<String> getProcessList(){
		
		return processlist;
	
	}//getProcessList
	
	public List<String> getTimeList(){
		
		return timelist;
	
	}//getTimeList
	
	static void drawGanttChart(GanttChart gantt_chart){
		
		String txt = "";
	
		for(int i = 0; i < gantt_chart.getProcessList().size(); i++)
			txt+=(" |"+ gantt_chart.getProcessList().get(i) + "| " 
					+ gantt_chart.getTimeList().get(i));

		System.out.print("0.0");
		System.out.print(txt + "\n\n");
		
	}//drawGanttChart
	
}//GanttChart
