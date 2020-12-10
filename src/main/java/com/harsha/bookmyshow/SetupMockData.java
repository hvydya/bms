package com.harsha.bookmyshow;

import com.harsha.bookmyshow.models.*;
import com.harsha.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * created on: 08/12/20
 * created by: harsha
 */

@Component
public class SetupMockData {

    Integer modelCount = 5;
    String[] cities = new String[]{"Hyderabad", "Delhi", " Bangalore"};
    String[] movies = new String[]{"Tenet", "V", "Shutter Island"};
    HashMap<String, Theatre> nameToTheatre = new HashMap<>();
    HashMap<String, City> nameToCity = new HashMap<>();
    HashMap<String, Movie> nameToMovie = new HashMap<>();
    HashMap<String, Owner> nameToOwner = new HashMap<>();
    HashMap<String, User> nameToUser = new HashMap<>();
    HashMap<Integer, Screen> idToScreen = new HashMap<>();

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    public void createMockData() {
        addUsers();
        addOwners();
        addCities();
        addMovies();
        addTheatres();
        addScreens();
        addScreening();
    }

    private void addScreens() {
        for (int i = 0; i < modelCount; i++) {
            String screenName1 = "Screen-1";
            String screenName2 = "Screen-2";
            Screen screen1 = screenRepository.save(new Screen(screenName1, nameToTheatre.get("Theatre-" + (i + 1))));
            Screen screen2 = screenRepository.save(new Screen(screenName2, nameToTheatre.get("Theatre-" + (i + 1))));
            idToScreen.put(screen1.getId(), screen1);
            idToScreen.put(screen2.getId(), screen2);
        }
    }

    private void addScreening() {
        Date cur = new Date();
        for (Screen screen : screenRepository.findAll()) {
            int index = ThreadLocalRandom.current().nextInt(0, movies.length);
            screeningRepository.save(new Screening(nameToMovie.get(movies[index]), cur, 20, screen));
        }
    }

    public void addUsers() {
        for (int i = 0; i < modelCount; i++) {
            String userName = "User-" + (i + 1);
            String email = userName + "@gmail.com";
            User user = userRepository.save(new User(userName, email));
            nameToUser.put(user.getName(), user);
        }
    }

    public void addOwners() {
        for (int i = 0; i < modelCount; i++) {
            String ownerName = "Owner-" + (i + 1);
            String email = ownerName + "@gmail.com";
            Owner owner = ownerRepository.save(new Owner(ownerName, email));
            nameToOwner.put(owner.getName(), owner);
        }
    }

    public void addCities() {
        for (String city : cities) {
            cityRepository.save(new City(city));
        }
        Iterable<City> cityList = cityRepository.findAll();
        for (City city : cityList) {
            nameToCity.put(city.getName(), city);
        }
    }

    private void addTheatres() {
        for (int i = 0; i < modelCount; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, cities.length);
            String name = "Theatre-" + (i + 1);
            Theatre t = theatreRepository.save(new Theatre(
                    name,
                    nameToCity.get(cities[index]),
                    nameToOwner.get("Owner-" + (index + 1))
            ));
            nameToTheatre.put(name, t);
        }
    }

    private void addMovies() {
        for (String movie : movies) {
            Movie m = movieRepository.save(new Movie(movie));
            nameToMovie.put(m.getName(), m);
        }
    }

    @PostConstruct
    public void init() {
        createMockData();
    }
}
