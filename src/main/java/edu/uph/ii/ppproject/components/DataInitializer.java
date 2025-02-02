package edu.uph.ii.ppproject.components;

import edu.uph.ii.ppproject.domain.*;
import edu.uph.ii.ppproject.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {
    private BuildingRepository buildingRepository;
    private UserRepository userRepository;
    private ApartmentRepository apartmentRepository;
    private UtilityRepository utilityRepository;
    private EventRepository eventRepository;
    private FeeRepository feeRepository;
    private IssueRepository issueRepository;
    private NotificationRepository notificationRepository;

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

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setFeeRepository(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Autowired
    public void setIssueRepository(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Bean
    InitializingBean initializingBean() {
        return () -> {
            User manager = new User();
            manager.setFirstName("Jan");
            manager.setLastName("Kowalski");
            manager.setPesel("12341234123");
            manager.setEmail("jan.kowalski@spoldzielnia.com");
            manager.setPassword("haslo1234");
            if (userRepository.count() == 0) userRepository.save(manager);
        };
    }

    @Bean
    InitializingBean buildingInit(){
        return () -> {
            Address address = new Address();
            address.setCity("Siedlce");
            address.setStreet("3-ego Maja");
            address.setNumber("10");

            Building building = new Building();
            building.setManager(userRepository.getReferenceById(1L));
            building.setAddress(address);
            if (buildingRepository.count() == 0) buildingRepository.save(building);
        };
    }

    @Bean
    InitializingBean ApartmentInit(){
        return () -> {
            Apartment apartment = new Apartment();
            apartment.setNumber(1);
            apartment.setArea(50);
            apartment.setPrice(400_000);
            apartment.setNumberOfRooms(3);
            apartment.setBuilding(buildingRepository.getReferenceById(1L));
            if (apartmentRepository.count() == 0) apartmentRepository.save(apartment);
        };
    }

    @Bean
    InitializingBean UtilityInit(){
        return () -> {
            Utility utility = new Utility();
            utility.setName("Lodówka");
            utility.setPrice(500);
            utility.addApartment(apartmentRepository.getReferenceById(1L));
            if (utilityRepository.count() == 0) utilityRepository.save(utility);
        };
    }

    @Bean
    InitializingBean EventInit(){
        return () -> {
            Event event = new Event();
            event.setName("Dzień dziecka");
            event.setBuilding(buildingRepository.getReferenceById(1L));
            event.setMessage("W dniu 1 czerwca na plcu zabaw odbędzie się dzień dziecka. Serdecznie zapraszamy");
            if (eventRepository.count() == 0) eventRepository.save(event);
        };
    }

    @Bean
    InitializingBean FeeInit(){
        return () -> {
            Fee fee = new Fee();
            fee.setTittle("Prąd");
            fee.setAmount(500);
            fee.setMaturity(LocalDate.of(2025, 2, 15));
            fee.setApartment(apartmentRepository.getReferenceById(1L));
            fee.setStatus(Fee.Status.NIE_OPLACONE);
            if (feeRepository.count() == 0) feeRepository.save(fee);
        };
    }

    @Bean
    InitializingBean IssueInit(){
        return () -> {
            Issue issue = new Issue();
            issue.setDescryption("");
            issue.setDate(LocalDate.now());
            issue.setStatus(Issue.Status.NIEODCZYTANE);
            issue.setTenant(userRepository.getReferenceById(1L));
            if (issueRepository.count() == 0) issueRepository.save(issue);
        };
    }

    @Bean
    InitializingBean NotificationInit(){
        return () -> {
            Notification notification = new Notification();
            notification.setTittle("Witamy");
            notification.setContent("Witamy nowych mieszkańców naszego bloku");
            notification.addUser(userRepository.getReferenceById(1L));
            if (notificationRepository.count() == 0) notificationRepository.save(notification);
        };
    }
}
