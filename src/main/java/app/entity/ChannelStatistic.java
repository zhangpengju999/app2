package app.entity;

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
@Table(name = "channel_statistic")
public class ChannelStatistic {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "value_item_id", referencedColumnName = "id")
	ValueItem valueItem;
	
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
	
	@Column(name="deduct_rate")
	double deductRate=0.2;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDeductRate() {
		return deductRate;
	}

	public void setDeductRate(double deductRate) {
		this.deductRate = deductRate;
	}

	public ValueItem getValueItem() {
		return valueItem;
	}

	public void setValueItem(ValueItem valueItem) {
		this.valueItem = valueItem;
	}
	
	

}
