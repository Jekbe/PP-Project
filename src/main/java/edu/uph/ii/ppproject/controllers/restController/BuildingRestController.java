package edu.uph.ii.ppproject.controllers.restController;

import edu.uph.ii.ppproject.domain.Building;
import edu.uph.ii.ppproject.exceptions.BuildingNotFoundException;
import edu.uph.ii.ppproject.services.BuildingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/rest/building")
public class BuildingRestController {
    private BuildingService buildingService;

    @Autowired
    public void setBuildingService(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Building> showDetails(@PathVariable long id) {
        var book = buildingService.buildingInfoService(id);
        return ResponseEntity.ok().//tutaj mamy status 200.
                header("Last-Modified", "Wed, 22 Dec 2021 10:36:04 GMT").
                header("MyCustomHeader", "My custom header value").
                body(book);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)//201 Created
    public void createBuilding(@RequestBody Building building) {

        buildingService.addBuildingService(building, 1L);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 No Content
    public void deleteBuilding(@PathVariable long id) {
        buildingService.buildingInfoService(id);
    }



    @ExceptionHandler({BuildingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND) //404 Not Found
    public void handleBookNotFoundErr(HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);
    }
}
