package com.interview.service.msproductmanagement.similarproduct.dao.mapper;

import com.interview.service.msproductmanagement.similarproduct.model.domain.ProductDetailDto;
import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDaoMapper {
  ProductDetailDto parseDaoToDomain(ProductDetailDao productDetailDao);
}
