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
import javax.persistence.Transient;

@Entity
@Table(name="sub_task")
public class SubTask {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Task parent;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "on_line_time")
	private Date onLineTime;
	
	@Column(name= "down_line_time")
	private Date downLineTime;
	
	@Column(name="url")
	private String url;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "operator", referencedColumnName = "id")
	private User operator;
	
	@Column(name="create_time")
	private Timestamp createTime; 
	
	@Transient
	private ValueItem currentValueItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public ValueItem getCurrentValueItem() {
		return currentValueItem;
	}

	public void setCurrentValueItem(ValueItem currentValueItem) {
		this.currentValueItem = currentValueItem;
	}
	
	
	
	
}
