package app.entity;

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
    private Long unitPrice;
    private int isPublic;
    private String seller;
    private double divideRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "settle", referencedColumnName = "id")
    private Settle settle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creater", referencedColumnName = "id")
    private User creater;

    private String descStr;

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
	public Long getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
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
}
