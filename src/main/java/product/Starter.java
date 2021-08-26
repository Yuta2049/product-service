package product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;

/**
 * @author Iuliia Tararueva
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ElastiCacheAutoConfiguration.class})
@EnableElastiCache({@CacheClusterConfig(name = "mymemcached", expiration = 0)})
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
