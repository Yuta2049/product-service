package product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Iuliia Tararueva
 */
@Getter
@Setter
@Builder
public class Product {
    Long id;
    String name;
    String comment;
}
