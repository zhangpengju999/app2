package app.entity;

import app.entity.User;
import lombok.Data;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 计费名表
 */
@Entity
@Data
public class ValueItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_task_id", referencedColumnName = "id")
    private SubTask subTask;

    @ManyToOne(optional = false)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private User channel;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "value_way_id", referencedColumnName = "id")
    private ValueWay valueWay;

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
    
    @ManyToOne(optional=false)
    @JoinColumn(name="site_id",referencedColumnName="id")
    private WebSite webSite;
    
    private String status;
    
    @Transient
    private Seller seller;
    
    @Transient
    private Task task;
    
    public String getDescStr() {
		return descStr;
	}
	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}
	public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
	
	public SubTask getSubTask() {
		return subTask;
	}
	public void setSubTask(SubTask subTask) {
		this.subTask = subTask;
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
	public WebSite getWebSite() {
		return webSite;
	}
	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ValueWay getValueWay() {
		return valueWay;
	}
	public void setValueWay(ValueWay valueWay) {
		this.valueWay = valueWay;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
    
    
}
