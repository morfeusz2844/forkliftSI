package model;

import model.enums.PackageSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ground implements WorldElement {
    private static final int MAX_CAPACITY = 20;
    private static final String TYPE = "Ground";


    private int leftSpace;

    private List<Package> items;

    public Ground() {
        this.leftSpace = MAX_CAPACITY;
        items = new ArrayList<Package>();
    }

    public int getLeftSpace() {
        return leftSpace;
    }

    public List<Package> getItems() {
        return items;
    }

    public void addPackage(Package pack) {
        if (pack != null && leftSpace > 0
                && pack.getPackageSize().equals(PackageSize.BIG)) {
            items.add(pack);
            leftSpace= leftSpace - pack.getPackageSize().getValue();
        }
    }
    
    public Package pickPackage(){
    	Iterator<Package> iterator = items.iterator();
		if (iterator.hasNext()) {
			Package next = iterator.next();
			iterator.remove();
			leftSpace = leftSpace + next.getPackageSize().getValue();
			return next;
		} else {
			return null;
		}
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean isPassable() {
        if (leftSpace < 5) return false;
        else return true;
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public String getItemsType() {
        if (items.size() > 0)
            return this.items.get(0).toString();
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
