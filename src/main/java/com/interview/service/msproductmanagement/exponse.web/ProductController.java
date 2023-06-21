package com.interview.service.msproductmanagement.exponse.web;

import com.interview.service.msproductmanagement.similarproduct.business.ProductService;
import com.interview.service.msproductmanagement.similarproduct.model.api.ProductResponse;
import com.interview.service.msproductmanagement.similarproduct.util.constants.BusinessConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/product")
@Slf4j
@AllArgsConstructor
public class ProductController {
  private ProductService productService;
  private ControllerMapper mapper;

  @GetMapping(value = "/{id}/similar")
  public Mono<ResponseEntity<Flux<ProductResponse>>> getProductSimilar(@PathVariable String id) {
    return Mono.just(ResponseEntity.ok(productService
        .getProductSimilar(mapper.parseToDomain(id))
        .subscribeOn(Schedulers.elastic())
        .map(mapper::parseToResponse)))
        .doOnSubscribe(disposable -> log.info(BusinessConstants.CONTROLLER_START,id))
        .doOnError(throwable -> log.error(BusinessConstants.CONTROLLER_ERROR, id))
        .doOnSuccess(disposable -> log.info(BusinessConstants.CONTROLLER_SUCCESS,id));
  }
}
