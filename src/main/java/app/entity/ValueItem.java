package app.entity;

import app.entity.User;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 计费名表
 */
@Entity
public class ValueItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private User channel;

    @Column(name="is_assigned")
    private int isAssigned;
    public int getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(int isAssigned) {
		this.isAssigned = isAssigned;
	}
	private String itemName;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
    private Date onLineTime;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date downLineTime;
	
    private double purchasePrice;
    private String descStr;
    public String getDescStr() {
		return descStr;
	}
	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}
	public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public User getChannel() {
		return channel;
	}
	public void setChannel(User channel) {
		this.channel = channel;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Date getOnLineTime() {
		return onLineTime;
	}
	public void setOnLineTime(Date onLineTime) {
		this.onLineTime = onLineTime;
	}
	public Date getDownLineTime() {
		return downLineTime;
	}
	public void setDownLineTime(Date downLineTime) {
		this.downLineTime = downLineTime;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
    
    
}
