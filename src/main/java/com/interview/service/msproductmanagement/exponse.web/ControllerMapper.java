package com.interview.service.msproductmanagement.exponse.web;

import com.interview.service.msproductmanagement.similarproduct.model.api.ProductRequest;
import com.interview.service.msproductmanagement.similarproduct.model.api.ProductResponse;
import com.interview.service.msproductmanagement.similarproduct.model.domain.ProductDetailDto;
import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ControllerMapper {
  @Mapping(target = "productId", source = "id")
  ProductRequest parseToDomain(String id);

  ProductResponse parseToResponse(ProductDetailDto productDetailDto);

  ProductResponse parseDaoToDomain(ProductDetailDao productDetailDao);
}
