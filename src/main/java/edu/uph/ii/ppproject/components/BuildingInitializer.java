package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.Address;
import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.domain.User;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BuildingInitializer {
    private BuildingRepository buildingRepository;
    private UserRepository userRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    InitializingBean buildingInit(){
        return () -> {
            User manager = new User();
            //manager.setUserId(1L);
            manager.setFirstName("Jan");
            manager.setLastName("Kowalski");
            manager.setPesel("12341234123");
            manager.setEmail("jan.kowalski@spoldzielnia.com");
            manager.setPassword("haslo1234");
            manager.setPasswordConfirm("haslo1234");
            manager.setEnabled(true);
            if (userRepository.count() == 0) userRepository.save(manager);

            Address address = new Address();
            address.setCity("Siedlce");
            address.setStreat("3-ego Maja");
            address.setNumber("10");

            Building building = new Building();
            //building.setBuildingId(1L);
            building.setManager(manager);
            building.setAddress(address);
            if (buildingRepository.count() == 0) buildingRepository.save(building);
        };
    }
}
