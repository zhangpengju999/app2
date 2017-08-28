package app.entity;

import lombok.Data;

public class ValueItemStatistic {

	Integer valueItemId;
	Integer taskId;
	double totalIncome;
	
	public Integer getValueItemId() {
		return valueItemId;
	}
	public void setValueItemId(Integer valueItemId) {
		this.valueItemId = valueItemId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	
	
}
