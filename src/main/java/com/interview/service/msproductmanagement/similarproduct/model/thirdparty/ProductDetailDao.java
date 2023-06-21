package com.interview.service.msproductmanagement.similarproduct.model.thirdparty;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDao {
  private String id;
  private String name;
  private BigDecimal price;
  private Boolean availability;
}
