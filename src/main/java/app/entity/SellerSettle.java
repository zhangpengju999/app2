package app.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seller_settle")
public class SellerSettle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "sub_task_id", referencedColumnName = "id")
	SubTask subTask;
	
	@Column(name="data_count")
	double dataCount;
	
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
	
	@Column(name = "year")
	Long year;
	
	@Column(name = "month")
	Long month;
	
	@Column(name="check_reduce_rate")
	double checkReduceRate=0.2;
	
	@Column(name="create_time")
	Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SubTask getSubTask() {
		return subTask;
	}

	public void setSubTask(SubTask subTask) {
		this.subTask = subTask;
	}

	public double getDataCount() {
		return dataCount;
	}

	public void setDataCount(double dataCount) {
		this.dataCount = dataCount;
	}

	public double getCheckReduceRate() {
		return checkReduceRate;
	}

	public void setCheckReduceRate(double checkReduceRate) {
		this.checkReduceRate = checkReduceRate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	
}
