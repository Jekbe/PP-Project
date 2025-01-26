package edu.uph.ii.ppproject.services;

import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.exceptions.BuildingNotFoundException;
import edu.uph.ii.ppproject.repositories.BuildingRepository;
import edu.uph.ii.ppproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
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

    public List<Building> buildingsService(){
        return buildingRepository.findAll();
    }

    public Building buildingFormService(Long id){
        return id != null ? buildingRepository.findById(id).orElse(new Building()) : new Building();
    }

    public void addBuildingService(Building building, Long managerId){
        building.setManager(userRepository.findById(managerId).orElse(null));

        buildingRepository.save(building);
    }

    public void deleteBuildingService(Long id){
        buildingRepository.deleteById(id);
    }

    public Building buildingInfoService(Long id){
        return buildingRepository.findById(id).orElseThrow(()-> new BuildingNotFoundException(id));
    }
}
