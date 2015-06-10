package model;

import model.enums.PackageSize;

import java.util.ArrayList;
import java.util.List;

public class StorageRack implements WorldElement {


    private static final int MAX_CAPACITY = 10;
    private static final String TYPE = "StorageRack";
    private int leftSpace;

    List<Package> items;

    public StorageRack() {
        leftSpace = MAX_CAPACITY;
        items = new ArrayList<Package>();
    }

    public List<Package> getItems() {
        return items;
    }

    public void addPackage(Package pack) {
        if (pack != null
                && leftSpace > 1
                && (pack.getPackageSize().equals(PackageSize.MEDIUM) || pack
                .getPackageSize().equals(PackageSize.SMALL))) {
            items.add(pack);
            leftSpace = leftSpace - pack.getPackageSize().getValue();
        }
        else if (pack != null
                && leftSpace == 1
                && pack
                .getPackageSize().equals(PackageSize.SMALL)){
        	items.add(pack);
            leftSpace = leftSpace - pack.getPackageSize().getValue();

        }
    }

    public int getLeftSpace() {
        return leftSpace;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public String getItemsType() {
        if (items.size() > 0)
            return this.items.get(0).getPackageType().toString();
        else
            return "empty";
    }
    public String getItemsSize(){
        if (items.size()>0){
            return  this.items.get(0).getPackageSize().toString();
        } else{
            return "empty";
        }
    }
}
