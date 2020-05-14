package ru.job4j.lsp;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class SimpleParking implements Parking {

    LinkedHashMap<String, Car> stPlaces;
    LinkedHashMap<String, Car> cargoPlaces;

    public SimpleParking(List<String> std, List<String> cargos) {
        this.cargoPlaces = new LinkedHashMap<>();
        this.stPlaces = new LinkedHashMap<>();
        for (String place : std) {
            this.stPlaces.put(place, null);
        }
        for (String place : cargos) {
            this.cargoPlaces.put(place, null);
        }
    }

    @Override
    public String addCar(Car car) {
        String rsl = null;
        if (car.getPlaces() > 1) {
            for (String place : cargoPlaces.keySet()) {
                if (cargoPlaces.get(place) == null) {
                    cargoPlaces.replace(place, car);
                    rsl = String.format("Cargo %s", place);
                }
            }
        }
        if (rsl == null) {
            LinkedList<String> collect = new LinkedList<>();
            int places = car.getPlaces();
            for (String place : stPlaces.keySet()) {
                if (stPlaces.get(place) == null) {
                    places--;
                    collect.add(place);
                } else {
                    places = car.getPlaces();
                    collect = new LinkedList<>();
                }
                if (places == 0) {
                    for (String pl : collect) {
                        stPlaces.replace(pl, car);
                    }
                    rsl = collect.size() == 1 ? collect.getFirst()
                            : String.format("%s-%s", collect.getFirst(), collect.getLast());
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public Car getCar(String number) {
        Car car = null;
        for (String place : stPlaces.keySet()) {
            if (car != null && (stPlaces.get(place) == null
                    || !stPlaces.get(place).getNumber().equals(number))) {
                break;
            }
            if (stPlaces.get(place) != null
                    && stPlaces.get(place).getNumber().equals(number)) {
                car = stPlaces.get(place);
                stPlaces.replace(place, null);
            }
        }
        if (car != null) {
            for (String place : cargoPlaces.keySet()) {
                if (cargoPlaces.get(place) != null
                        && cargoPlaces.get(place).getNumber().equals(number)) {
                    car = cargoPlaces.get(place);
                    cargoPlaces.replace(place, null);
                    break;
                }
            }
        }
        return car;
    }
}