package vn.eshopvn.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="Products")
@NamedQuery(name="product.findAll", query="select p from Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pid", columnDefinition = "nvarchar(255)")
	private String pid;
	
	@Column(name = "pname", columnDefinition = "nvarchar(255)")
	private String pname;
	
	@Column(name = "pquantity")
	private int pquantity;
	
	@Column(name = "pprice")
	private int pprice;
	
	@Column(name = "pinfo", columnDefinition = "nvarchar(5000)")
	private String pinfo;
	
	@Column(name = "pmanufacturer", columnDefinition = "nvarchar(5000)")
	private String pmanufacturer;
	
	@Column(name = "ppicture", columnDefinition = "nvarchar(5000)")
	private String ppicture;
	
	@Column(name = "prating")
	private int prating;

	@Column(name = "createdat", columnDefinition = "timestamp")
	private Timestamp createdat;
	
	@ManyToOne
    @JoinColumn(name = "gid", nullable = false)
    private Genre genre;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}