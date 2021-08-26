package product.service;

import product.model.Product;

import java.util.List;

/**
 * @author Iuliia Tararueva
 */
public interface IProductService {

    void createProduct(Product product);

    Product findProductById(Long id);

    List<Product> findAll();

    boolean createTables();
}
