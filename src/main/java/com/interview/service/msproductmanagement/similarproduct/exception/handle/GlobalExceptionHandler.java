package com.interview.service.msproductmanagement.similarproduct.exception.handle;

import com.interview.service.msproductmanagement.similarproduct.exception.ProductNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MarkerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler(ProductNotFoundException.class)
  public Mono<ResponseEntity<Map<String, Object>>> handlerException(ProductNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    log.warn(MarkerFactory.getMarker("VALID"), ex.getMessage());
    return Mono.just(ex).map(ProductNotFoundException::getMessage).flatMap(msg -> {
      response.put("message", "Product not found");
      return Mono.just(ResponseEntity.badRequest().body(response));
    });
  }
}
