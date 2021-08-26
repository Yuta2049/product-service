package product.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import product.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliia Tararueva
 */
@Slf4j
@Repository
public class DynamoDBRepository implements DBRepository {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(String.valueOf(Region.US_East_2)).build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "Products";
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

    @Override
    public boolean createTables() {
        try {

            //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
            //DynamoDB dynamoDB = new DynamoDB(client);

            //String tableName = "ExampleTable";

            List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("N"));
//            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Name").withAttributeType("S"));
//            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Comment").withAttributeType("S"));

            List<KeySchemaElement> keySchema = new ArrayList<>();
            keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH)); // Partition
            // key

            CreateTableRequest request = new CreateTableRequest().withTableName(tableName).withKeySchema(keySchema)
                    .withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
                            new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(6L));

            log.info("Issuing CreateTable request for " + tableName);
            Table table = dynamoDB.createTable(request);

            log.info("Waiting for " + tableName + " to be created...this may take a while...");
            table.waitForActive();

            getTableInformation();

            return true;
        }
        catch (Exception e) {
            log.info("CreateTable request failed for " + tableName);
            log.info(e.getMessage());
            return false;
        }
    }

    static void getTableInformation() {

        log.info("Describing " + tableName);

        TableDescription tableDescription = dynamoDB.getTable(tableName).describe();
        System.out.format(
                "Name: %s:\n" + "Status: %s \n" + "Provisioned Throughput (read capacity units/sec): %d \n"
                        + "Provisioned Throughput (write capacity units/sec): %d \n",
                tableDescription.getTableName(), tableDescription.getTableStatus(),
                tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
                tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
    }

    @Override
    public void createProduct(Product product) {
        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        //DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(product);
    }

    @Override
    public Product readProductById(Long id) {
        log.info("We went to DB");
        Product partitionKey = new Product();

        partitionKey.setId(id);
        DynamoDBQueryExpression<Product> queryExpression = new DynamoDBQueryExpression<Product>()
                .withHashKeyValues(partitionKey);

        List<Product> itemList = mapper.query(Product.class, queryExpression);

        if (!itemList.isEmpty()) {
            return itemList.get(0);
        } else {
            return null;
        }
    }
}
