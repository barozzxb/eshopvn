package vn.eshopvn.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orders")
@NamedQuery(name = "order.findAll", query = "select o from Order o")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "oid", columnDefinition = "nvarchar(255)")
	private String oid;
	
	@Column(name = "odate", columnDefinition = "timestamp")
	private Timestamp odate;
	
	@Column(name = "ostatus", columnDefinition = "nvarchar(255)")
	private String ostatus;
		
	@Column(name = "payment", columnDefinition = "nvarchar(255)")
	private String payment;
	
	@Column(name = "note", columnDefinition = "nvarchar(5000)")
	private String note;
	
	@Column(name = "cost", columnDefinition = "int")
	private int cost;
	
	@Column(name = "deliveryinfo", columnDefinition = "nvarchar(255)")
	private String deliveryinfo;
	
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails;
}
