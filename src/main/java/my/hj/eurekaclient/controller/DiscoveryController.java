package my.hj.eurekaclient.controller;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {
    private Logger LOGGER = LoggerFactory.getLogger(DiscoveryController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/ping")
    public List<ServiceInstance> ping() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
        LOGGER.info("INSTANCES: count={}", instances.size());
        instances.stream()
                .forEach(it -> LOGGER.info("INSTATNCE: id={}, port={}", it.getServiceId(), it.getPort()));

        return instances;
    }
}
