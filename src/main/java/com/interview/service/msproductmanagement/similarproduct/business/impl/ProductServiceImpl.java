package com.interview.service.msproductmanagement.similarproduct.business.impl;

import com.interview.service.msproductmanagement.similarproduct.business.ProductService;
import com.interview.service.msproductmanagement.similarproduct.dao.ProductDao;
import com.interview.service.msproductmanagement.similarproduct.mapper.ProductMapper;
import com.interview.service.msproductmanagement.similarproduct.model.api.ProductRequest;
import com.interview.service.msproductmanagement.similarproduct.model.domain.ProductDetailDto;
import com.interview.service.msproductmanagement.similarproduct.util.constants.BusinessConstants;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
  private ProductDao productDao;
  private ProductMapper productMapper;

  /**
   * @param productRequest
   * @return
   */
  @Override
  public Flux<ProductDetailDto> getProductSimilar(ProductRequest productRequest) {
    return productDao.listSimilarProductIdUsingGet(productRequest.getProductId())
        .map(ProductServiceImpl::parseIdToListIds)
        .flatMap(Flux::fromIterable)
        .flatMap(this::searchSimilarProduct);
  }

  private Publisher<? extends ProductDetailDto> searchSimilarProduct(String id) {
    return productDao.searchProductById(id)
        .map(productMapper::parseDaoToDomain);
  }

  private static List<String> parseIdToListIds(String productId) {
    return Arrays.stream(productId.replaceAll(BusinessConstants.PATTERN_STRING_TO_LIST, StringUtils.EMPTY)
            .split(BusinessConstants.COMMA))
        .map(String::trim)
        .collect(Collectors.toList());
  }
}