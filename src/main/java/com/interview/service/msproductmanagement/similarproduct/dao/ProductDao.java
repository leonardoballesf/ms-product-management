package com.interview.service.msproductmanagement.similarproduct.dao;


import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDao {
  Mono<ProductDetailDao> searchProductById(String productId);

  Flux<String> listSimilarProductIdUsingGet(String productId);

}

