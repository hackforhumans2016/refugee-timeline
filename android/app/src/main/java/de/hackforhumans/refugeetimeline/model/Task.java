package de.hackforhumans.refugeetimeline.model;

import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Task {
	private int id;
	private String name;
	private String description;
	private int predecessor;
	
	private int duration;
	private SortedSet<Date> fixed;
	
	private Date date;
	
	public Task(int p_id, String p_name, String p_description, int p_conditions, int p_duration, SortedSet<Date> p_fixed){
		id = p_id;
		name = p_name;
		description = p_description;
		predecessor = p_conditions;
		duration = p_duration;
		
		if(p_fixed == null){
			fixed = new TreeSet<Date>();
		}else{
			fixed = p_fixed;
		}
		
	}
	
	public Boolean calcDate(Date startDate){
		Boolean result = false;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
			
		if(fixed.size()!=0){
			//for(int j=0; j<100; j++){
				for(Date i :fixed){
					Calendar tmpC = Calendar.getInstance();
					//tmpC.setTime(addYears(i,j));
					
					if(i.after(startDate)){
					  date = i;//addYears(i,j);
					  return true;
					}
				}
			//}
		} else{
			c.add(Calendar.DATE, TaskTools.loadFromDB(predecessor).duration);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				c.add(Calendar.DATE,2);
			}else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				c.add(Calendar.DATE,1);
			}
		}
		
		date = c.getTime();
		result = true;
		return result;
	}
	
	private Date addYears(Date startDate, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

    public int getID() {
        return id;
    }
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPredecessorID() {
		return predecessor;
	}

	public int getDuration() {
		return duration;
	}

	public SortedSet<Date> getFixed() {
		return fixed;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date p_date) {
		date = p_date;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", predecessor=" + predecessor
				+ ", duration=" + duration + ", fixed=" + fixed + ", date=" + date + "]";
	}
		
}
