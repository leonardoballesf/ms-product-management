package com.interview.service.msproductmanagement.similarproduct.dao.impl;

import com.interview.service.msproductmanagement.similarproduct.dao.ProductDao;
import com.interview.service.msproductmanagement.similarproduct.dao.ProductProxy;
import com.interview.service.msproductmanagement.similarproduct.exception.ProductNotFoundException;
import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import com.interview.service.msproductmanagement.similarproduct.util.constants.BusinessConstants;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@AllArgsConstructor
public class ProductDaoImpl implements ProductDao {
  private ProductProxy productProxy;
  private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

  @Override
  public Mono<ProductDetailDao> searchProductById(String productId) {
    return reactiveCircuitBreakerFactory.create(BusinessConstants.RESILIENCE_LABEL)
        .run(productProxy
            .getProductProductId(productId)
            .onErrorResume(ProductNotFoundException.class, throwable -> Mono.error(new ProductNotFoundException()))
            .subscribeOn(Schedulers.elastic()), ex -> Mono.error(new ProductNotFoundException()));
  }

  @Override
  public Flux<String> listSimilarProductIdUsingGet(String productId) {
    return reactiveCircuitBreakerFactory.create(BusinessConstants.RESILIENCE_LABEL)
        .run(productProxy.getProductSimilarids(productId)
            .onErrorResume(throwable -> Mono.error(new ProductNotFoundException()))
            .subscribeOn(Schedulers.elastic()), ex -> Flux.error(new ProductNotFoundException()));
  }
}
