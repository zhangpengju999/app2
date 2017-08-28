package app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statistic")
public class Statistic {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "value_item_id")
	Long valueItemId;
	
	@Column(name = "show_count")
	Long showCount;
	
	@Column(name = "click_count")
	Long clickCount;
	
	@Column(name = "click_rate")
	double clickRate;
	
	@Column(name = "CPM")
	double CPM;
	
	@Column(name = "income")
	double income;
	
	@Column(name = "date")
	Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValueItemId() {
		return valueItemId;
	}

	public void setValueItemId(Long valueItemId) {
		this.valueItemId = valueItemId;
	}

	public Long getShowCount() {
		return showCount;
	}

	public void setShowCount(Long showCount) {
		this.showCount = showCount;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	public double getClickRate() {
		return clickRate;
	}

	public void setClickRate(double clickRate) {
		this.clickRate = clickRate;
	}

	public double getCPM() {
		return CPM;
	}

	public void setCPM(double cPM) {
		CPM = cPM;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
