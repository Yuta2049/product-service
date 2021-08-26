package product.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.model.Product;
import product.service.IProductService;

/**
 * @author Iuliia Tararueva
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {

    private IProductService productService;

    @PostMapping("/createTables")
    ResponseEntity<?> createTables() {
        boolean result = productService.createTables();
        if (result) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products/")
    ResponseEntity<?> createProduct(@RequestBody Product product) {
        log.info("Post products");
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.info("Get products");
        Product product = productService.findProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
