package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.Address;
import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.domain.Manager;
import edu.uph.ii.ppproject.repositories.ApartmentRepository;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.ManagerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BuildingInitializer {
    private BuildingRepository buildingRepository;
    private ManagerRepository managerRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    public void setManagerRepository(ManagerRepository managerRepository){
        this.managerRepository = managerRepository;
    }

    @Bean
    InitializingBean buildingInit(){
        return () -> {
            Manager manager = new Manager();
            manager.setUserId(1L);
            manager.setFirstName("Jan");
            manager.setLastName("Kowalski");
            manager.setPesel("12341234123");
            manager.setEmail("jan.kowalski@spoldzielnia.com");
            if (managerRepository.count() == 0)
                managerRepository.save(manager);

            Address address = new Address();
            address.setCity("Siedlce");
            address.setStreat("3-ego Maja");
            address.setNumber("10");

            Building building = new Building();
            building.setBuildingId(1L);
            building.setManager(manager);
            building.setAddress(address);
            if (buildingRepository.count() == 0) buildingRepository.save(building);
        };
    }
}
