package genetic;


import model.Truck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2015-05-19.
 * <p>
 * Pojedyñczy osobnik
 */
public class WarehouseMapGA {
    private int fitness;
    private int truckPositionX;
    private int truckPositionY;

    private final List<Place> placeMap = new ArrayList<>();

    public WarehouseMapGA(int _truckPositionX,int _trackPositionY) {
        this.truckPositionX = _truckPositionX;
        this.truckPositionY = _trackPositionY;
    }
    public WarehouseMapGA(List<Place> _place, Truck _truck) {
        for (Place place : _place) {
            this.placeMap.add(new Place(place));
        }
        this.truckPositionX = _truck.getPositionx();
        this.truckPositionY = _truck.getPositiony();
    }

    public WarehouseMapGA(WarehouseMapGA other) {
        for (Place place : other.getPlaceMap()) {
            this.placeMap.add(new Place(place));
        }
        this.truckPositionX = other.getTruckPositionX();
        this.truckPositionY = other.getTruckPositionY();
    }

    private void displayMap() {
        for (Place place : this.placeMap) {
            if (place.getPackageList().size() > 0) {
                System.out.println(place.toString());
            }
        }
    }

    public List<Place> getPlaceMap() {
        return this.placeMap;
    }

    public int countFitness() {
        int fitnessTemp = 0;
        for (Place _place : this.placeMap) {
            if (_place.getPackageList().size() > 0) {
                if (_place.getPackageList().size() > 1) {
                    for (int i = 0; i < _place.getPackageList().size() - 1; i++) {
                        if (_place.getPackageList().get(i).getPackageType().toString().equals(_place.getPackageList().get(i + 1).getPackageType().toString())) {
                            fitnessTemp = fitnessTemp + 10;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (_place.getTYPEPACKAGE().equals(_place.getPackageList().get(0).getPackageType().toString())) {
                        fitnessTemp = fitnessTemp + 10;
                    } else {
                        return 0;
                    }
                }
                fitnessTemp = fitnessTemp + this.checkNeighbor(_place);
                if (fitnessTemp > 0) {
                    fitnessTemp = fitnessTemp - this.checkDistance(_place)/2;
                }
            }
        }
        return fitnessTemp;
    }

    private int checkDistance(Place _place) {
        int tempx = _place.getPositionX();
        int tempy = _place.getPositionY();
        return Math.abs((tempx-truckPositionX)+(tempy-truckPositionY));
    }

    private int checkNeighbor(Place place) {
        int temp = 0;
        for (Place item : this.placeMap) {
            if (item.getPackageList().size() > 0) {
                if (place.getPositionX() == 14 && place.getPositionY() == 14) {
                    temp = temp + 10;
                    if (item.getPositionY() == place.getPositionY() - 1) {
                        if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                item.getTYPEPACKAGE().equals("empty") ||
                                item.getTYPEPACKAGE().equals("Road")) {
                            temp = temp + 2;
                        } else {
                            temp = temp - 2;
                        }
                    } else if (item.getPositionX() == place.getPositionX() - 1) {
                        if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                item.getTYPEPACKAGE().equals("empty") ||
                                item.getTYPEPACKAGE().equals("Road")) {
                            temp = temp + 2;
                        } else {
                            temp = temp - 2;
                        }
                    } else if (item.getPositionX() == place.getPositionX() - 1 && item.getPositionY() == place.getPositionY() - 1) {
                        if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                item.getTYPEPACKAGE().equals("empty") ||
                                item.getTYPEPACKAGE().equals("Road")) {
                            temp = temp + 2;
                        } else {
                            temp = temp - 2;
                        }
                    }
                } else if (place.getPositionX() == 14 && place.getPositionY() < 14 && place.getPositionY() > 0) {
                    temp = temp + 6;
                    if (item.getPositionX() == place.getPositionX() - 1) {
                        if (item.getPositionY() == place.getPositionY() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY()) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    } else if (item.getPositionX() == place.getPositionX()) {
                        if (item.getPositionY() == place.getPositionY() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    }
                } else if (place.getPositionY() == 14 && place.getPositionX() > 0 && place.getPositionX() < 14) {
                    temp = temp + 6;
                    if (item.getPositionX() == place.getPositionX() - 1) {
                        if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                item.getTYPEPACKAGE().equals("empty") ||
                                item.getTYPEPACKAGE().equals("Road")) {
                            temp = temp + 2;
                        } else {
                            temp = temp - 2;
                        }
                    } else if (item.getPositionX() == place.getPositionX() + 1) {
                        if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                item.getTYPEPACKAGE().equals("empty") ||
                                item.getTYPEPACKAGE().equals("Road")) {
                            temp = temp + 2;
                        } else {
                            temp = temp - 2;
                        }
                    } else if (item.getPositionY() == place.getPositionY() - 1) {
                        if (item.getPositionX() == place.getPositionX() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionX() == place.getPositionX() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionX() == place.getPositionX()) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    }
                } else if (place.getPositionX() > 0 && place.getPositionX() < 14 && place.getPositionY() > 0 && place.getPositionY() < 14) {
                    if (item.getPositionX() == place.getPositionX() - 1) {
                        if (item.getPositionY() == place.getPositionY() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY()) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    } else if (item.getPositionX() == place.getPositionX()) {
                        if (item.getPositionY() == place.getPositionY() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    } else if (item.getPositionX() == place.getPositionX() + 1) {
                        if (item.getPositionY() == place.getPositionY() - 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY()) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        } else if (item.getPositionY() == place.getPositionY() + 1) {
                            if (item.getTYPEPACKAGE().equals(place.getPackageList().get(0).getPackageType().toString()) ||
                                    item.getTYPEPACKAGE().equals("empty") ||
                                    item.getTYPEPACKAGE().equals("Road")) {
                                temp = temp + 2;
                            } else {
                                temp = temp - 2;
                            }
                        }
                    }
                }
            }
        }
        return temp;
    }

    private int countFreePlaces(PackageGA _package) {
        int counter = 0;
        for (Place _place : this.placeMap) {
            if (_place.getFreeSpace() > _package.getPackageSize().getValue()) {
                counter++;
            }
        }
        return counter;

    }

    public void generateIndividual(List<PackageGA> _packageGAList) {
        for (PackageGA _package : _packageGAList) {
            int freePlaces = this.countFreePlaces(_package);
            boolean flag = false;
            while (flag == false) {
                int random = (int) (Math.random() * freePlaces);
                if (this.placeMap.get(random).getFreeSpace() > _package.getPackageSize().getValue()) {
                    this.placeMap.get(random).addToPackageList(_package);
                    flag = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getTruckPositionX() {
        return this.truckPositionX;
    }

    public int getTruckPositionY() {
        return this.truckPositionY;
    }
    public int getFitness(){
        return this.countFitness();
    }

    public void addPlace(Place _place){
        this.placeMap.add(new Place(_place));
    }
}
