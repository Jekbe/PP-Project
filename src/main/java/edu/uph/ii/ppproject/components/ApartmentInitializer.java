package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.Apartment;
import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.repositories.ApartmentRepository;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApartmentInitializer {
    private ApartmentRepository apartmentRepository;
    private BuildingRepository buildingRepository;

    @Autowired
    public void setApartmentRepository(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
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
}
