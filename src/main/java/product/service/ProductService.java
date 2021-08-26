package product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import product.model.Product;
import product.repository.DBRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliia Tararueva
 */
@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "mymemcached")
public class ProductService implements IProductService {

    private final List<Product> productList = new ArrayList<>();
    private final DBRepository repository;

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
        //productList.add(product);
        repository.createProduct(product);
    }

    @Override
    @Cacheable(value = "mymemcached", key = "#id.toString()", unless="#result == null")
    public Product findProductById(Long id) {
        //Optional<Product> productOptional = productList.stream().filter(product -> product.getId().equals(id)).findAny();
        //return productOptional.orElse(null);
        return repository.readProductById(id);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public boolean createTables() {
        return repository.createTables();
    }
}
