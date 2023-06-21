package com.interview.service.msproductmanagement.similarproduct.business;


import com.interview.service.msproductmanagement.similarproduct.model.api.ProductRequest;
import com.interview.service.msproductmanagement.similarproduct.model.domain.ProductDetailDto;
import reactor.core.publisher.Flux;

public interface ProductService {
  Flux<ProductDetailDto> getProductSimilar(ProductRequest productRequest);
}
