package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDto;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository; 
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProductDto addProduct(Long categoryId, Product product) {
         Category category = categoryRepository.findById(categoryId)
                 . orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
         product.setImage("default.png");
         product.setCategory(category);
         double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) *  product.getPrice();
         product.setSpecialPrice(specialPrice);
         Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products =  productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);

        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Product> products = productRepository.findProductsByCategoryOrderByPriceAsc(category);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByKeyword(String keyword) {
        List<Product> products = productRepository.findProductsByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);
        return productResponse;
    }

    @Override
    public ProductDto updateProduct(Product product, Long productId) {
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product","productId",productId));
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setSpecialPrice(product.getSpecialPrice());
        Product savedProduct = productRepository.save(productFromDb);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                                .orElseThrow(() ->
                                        new ResourceNotFoundException("Product","productId", productId));
        productRepository.delete(product);
        return modelMapper.map(product, ProductDto.class) ;
    }
}
