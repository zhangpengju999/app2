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
@Table(name="web_site")
public class WebSite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="parent_id")
    private Long parentId=(long) 0;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private User channel;

    @Column(name="web_name")
	private String webName;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
    private Date createTime;
	
	private String url;
	
	@Column(name="desc_str")
    private String descStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public User getChannel() {
		return channel;
	}

	public void setChannel(User channel) {
		this.channel = channel;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescStr() {
		return descStr;
	}

	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}    
	
	
	
}
