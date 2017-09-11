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
public class WebSite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="parent_id")
    private int parentId;
    
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
}
