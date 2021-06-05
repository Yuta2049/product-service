package product.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.model.Product;
import product.service.IProductService;

/**
 * @author Iuliia Tararueva
 */
@RestController
@AllArgsConstructor
public class ProductController {

    private IProductService productService;

    @PostMapping("/products/")
    ResponseEntity<?> createProduct(@RequestBody Product product) {
        System.out.println("Post products");
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id) {
        System.out.println("Get products");
        Product product = productService.findProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
