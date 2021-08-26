//package product;
//
//import com.amazonaws.xray.AWSXRay;
//import com.amazonaws.xray.AWSXRayRecorderBuilder;
//import com.amazonaws.xray.metrics.MetricsSegmentListener;
//import com.amazonaws.xray.plugins.EC2Plugin;
//import com.amazonaws.xray.plugins.ElasticBeanstalkPlugin;
//import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;
//import net.spy.memcached.MemcachedClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
//import javax.servlet.Filter;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.URL;
//
//
///**
// * @author Iuliia Tararueva
// */
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public Filter TracingFilter() {
//        return new AWSXRayServletFilter("product");
//    }
//
//    static {
//        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder
//                .standard()
//                .withPlugin(new EC2Plugin())
//                .withPlugin(new ElasticBeanstalkPlugin())
//                .withSegmentListener(new MetricsSegmentListener());;
//
//        URL ruleFile = WebConfig.class.getResource("/sampling-rules.json");
//        builder.withSamplingStrategy(new LocalizedSamplingStrategy(ruleFile));
//
//        AWSXRay.setGlobalRecorder(builder.build());
//    }
//
////    @Bean
////    public MemcachedClient getClient() throws IOException {
////
////        //String configEndpoint = "mycluster.fnjyzo.cfg.use1.cache.amazonaws.com";
////        String configEndpoint = "mymemcached.ba3x0b.cfg.use2.cache.amazonaws.com";
////        Integer clusterPort = 11211;
////
////        MemcachedClient client = new MemcachedClient(
////                new InetSocketAddress(configEndpoint,
////                        clusterPort));
////        // The client will connect to the other cache nodes automatically.
////
////        // Store a data item for an hour.
////        // The client will decide which cache host will store this item.
////        //client.set("theKey", 3600, "This is the data value");
////        return client;
////    }
//
//}
