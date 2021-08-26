package product.repository;

import product.model.Product;

/**
 * @author Iuliia Tararueva
 */
public interface DBRepository {
    boolean createTables();

    void createProduct(Product product);

    Product readProductById(Long id);
}
