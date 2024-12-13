package vn.eshopvn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.eshopvn.entity.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TopSellingProduct {
	
	Product product;
	long ordernumber;
}
