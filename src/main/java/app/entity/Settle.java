package app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settle")
public class Settle {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String settleName;
    private int settleDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSettleName() {
		return settleName;
	}
	public void setSettleName(String settleName) {
		this.settleName = settleName;
	}
	public int getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(int settleDate) {
		this.settleDate = settleDate;
	}
}
