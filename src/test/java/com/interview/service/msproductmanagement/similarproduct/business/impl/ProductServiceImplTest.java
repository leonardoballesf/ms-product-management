package com.interview.service.msproductmanagement.similarproduct.business.impl;

import static org.mockito.ArgumentMatchers.anyString;

import com.interview.service.msproductmanagement.similarproduct.dao.ProductDao;
import com.interview.service.msproductmanagement.similarproduct.mapper.ProductMapper;
import com.interview.service.msproductmanagement.similarproduct.model.api.ProductRequest;
import com.interview.service.msproductmanagement.similarproduct.model.thirdparty.ProductDetailDao;
import com.interview.service.msproductmanagement.util.TestUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productService;
  @Mock
  ProductDao productDao;

  @Spy
  private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  @Test
  @DisplayName("Return successfully when search similar product")
  void returnSuccessFullWhenRequestParamsIsValid() {

    ProductRequest productRequest = new ProductRequest();
    productRequest.setProductId("1");

    ProductDetailDao product = TestUtil.readAsJsonDataFromResourceFile(
        "responseDao1.json", ProductDetailDao.class);

    Mockito.when(productDao.listSimilarProductIdUsingGet(anyString()))
        .thenReturn(Flux.just("[2]"));

    Mockito.when(productDao.searchProductById(anyString()))
        .thenReturn(Mono.just(product));

    StepVerifier.create(productService.getProductSimilar(productRequest))
        .expectNextMatches(response -> response.getAvailability().equals(true)
            && response.getName().equals("Dress")
            && response.getPrice().equals(BigDecimal.valueOf(19.99))
            && response.getId().equals("2"))
        .expectComplete()
        .verify();
  }
}
