package product.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.io.Serializable;

/**
 * @author Iuliia Tararueva
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName="Products")
public class Product implements Serializable {
    @DynamoDBHashKey(attributeName="Id")
    Long id;

    @DynamoDBAttribute(attributeName="Name")
    String name;

    @DynamoDBAttribute(attributeName="Comment")
    String comment;
}
