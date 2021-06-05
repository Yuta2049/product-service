package product.service;

import org.springframework.stereotype.Service;
import product.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Iuliia Tararueva
 */
@Service
public class ProductService implements IProductService {

    private List<Product> productList = new ArrayList();

    @Override
    public void createProduct(Product product) {
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
