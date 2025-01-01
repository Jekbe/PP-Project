package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.*;
import edu.uph.ii.ppproject.repositories.ApartmentRepository;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import edu.uph.ii.ppproject.repositories.UtilityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private BuildingRepository buildingRepository;
    private UserRepository userRepository;
    private ApartmentRepository apartmentRepository;
    private UtilityRepository utilityRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setApartmentRepository(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Autowired
    public void setUtilityInitializer(UtilityRepository utilityRepository){
        this.utilityRepository = utilityRepository;
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
            //manager.setEnabled(true);
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

    @Bean
    InitializingBean ApartmentInit(){
        return () -> {
            Building building = buildingRepository.getReferenceById(1L);

            Apartment apartment = new Apartment();
            apartment.setApartmentId(1L);
            apartment.setNumber(1);
            apartment.setArea(50);
            apartment.setPrice(400_000);
            apartment.setNumberOfRooms(3);
            apartment.setBuilding(building);
            if (apartmentRepository.count() == 0) apartmentRepository.save(apartment);
        };
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
