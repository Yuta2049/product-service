package product.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import product.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iuliia Tararueva
 */
@Slf4j
@Service
public class ProductService implements IProductService {

    private List<Product> productList = new ArrayList();

    @PostConstruct
    private void init() {
        productList.add(Product.builder()
                .id(1L)
                .name("First")
                .comment("predefined")
                .build());
        productList.add(Product.builder()
                .id(2L)
                .name("Second")
                .comment("predefined")
                .build());
    }

    @Override
    public void createProduct(Product product) {
        log.info("Product was added");
        productList.add(product);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> productOptional = productList.stream().filter(product -> product.getId().equals(id)).findAny();
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }
}
