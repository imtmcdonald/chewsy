package edu.psu.sweng894.chewsy.concierge.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.psu.sweng894.chewsy.concierge.ConciergeApplication;
import edu.psu.sweng894.chewsy.concierge.domain.repository.RestaurantRepository;
import edu.psu.sweng894.chewsy.concierge.domain.service.ConciergeService;
import edu.psu.sweng894.chewsy.concierge.domain.service.DomainConciergeService;

@Configuration
@ComponentScan(basePackageClasses = ConciergeApplication.class)
public class BeanConfiguration {
    @Bean
    ConciergeService conciergeService(final RestaurantRepository restaurantRepository) {
        return new DomainConciergeService(restaurantRepository);
    }
}