package com.amardairy.serviceImpl;

import com.amardairy.entity.Product;
import com.amardairy.repository.ProductRepository;
import com.amardairy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional  // ADD THIS
    public Product saveProduct(Product product) {
        // Ensure ID is null for new products
        if (product.getId() != null) {
            // This is an update, not a new product
            return updateProduct(product.getId(), product);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    @Transactional  // ADD THIS
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setUnit(product.getUnit());
        existing.setAvailable(product.getAvailable());

        return productRepository.save(existing);
    }

    @Override
    @Transactional  // ADD THIS
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}