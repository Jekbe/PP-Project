package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.Utility;
import edu.uph.ii.ppproject.repositories.UtilityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilityInitializer {
    private UtilityRepository utilityRepository;

    @Autowired
    public void setUtilityInitializer(UtilityRepository utilityRepository){
        this.utilityRepository = utilityRepository;
    }

    @Bean
    InitializingBean UtilityInit(){
        return () -> {
            Utility utility = new Utility();
            utility.setUtilityId(1L);
            utility.setType("Lodówka");
            utility.setPrice(500);
            if (utilityRepository.count() == 0) utilityRepository.save(utility);
        };
    }
}
