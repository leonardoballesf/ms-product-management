package com.interview.service.msproductmanagement.similarproduct.dao;

import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Validated
@ReactiveFeignClient(value = "mock-service", url = "${application.http-client.mock-service.url}")
public interface ProductProxy {

  /**
   * GET /product/{productId} : Gets a product detail Returns the product detail for a given
   * productId
   *
   * @param productId (required)
   * @return OK (status code 200) or Product Not found (status code 404)
   */
  @GetMapping("/product/{productId}")
  Mono<ProductDetailDao> getProductProductId(@PathVariable("productId") String productId);


  /**
   * GET /product/{productId}/similarids : Gets the ids of the similar products Returns the similar
   * products to a given one ordered by similarity
   *
   * @param productId (required)
   * @return OK (status code 200)
   */

  @GetMapping("/product/{productId}/similarids")
  Flux<String> getProductSimilarids(@PathVariable("productId") String productId);

}

