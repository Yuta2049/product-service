package product.service;

import org.springframework.stereotype.Service;
import product.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iuliia Tararueva
 */
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
        System.out.println("Product was added");
        productList.add(product);
    }

    @Override
    public Product findProductById(Long id) {       // TODO: А если нет продукта?
        Optional<Product> productOptional = productList.stream().filter(product -> product.getId().equals(id)).findAny();
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }
}
