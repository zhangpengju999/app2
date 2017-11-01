package app.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName;

	@ManyToOne(optional = false)
    @JoinColumn(name = "value_way_id", referencedColumnName = "id")
    private ValueWay valueWayId;

    private Long showCount;
    private Long clickCount;
    private double unitPrice;
    private int isPublic;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller", referencedColumnName = "id")
    private Seller seller;
    
    private double divideRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "settle", referencedColumnName = "id")
    private Settle settle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creater", referencedColumnName = "id")
    private User creater;

    private String descStr;
    
    private String system;
    
    @Column(name="create_time")
    private Timestamp createTime;

    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public ValueWay getValueWayId() {
		return valueWayId;
	}
	public void setValueWayId(ValueWay valueWayId) {
		this.valueWayId = valueWayId;
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

	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Settle getSettle() {
		return settle;
	}
	public void setSettle(Settle settle) {
		this.settle = settle;
	}
	public User getCreater() {
		return creater;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	public String getDescStr() {
		return descStr;
	}
	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}
	public double getDivideRate() {
		return divideRate;
	}
	public void setDivideRate(double divideRate) {
		this.divideRate = divideRate;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
