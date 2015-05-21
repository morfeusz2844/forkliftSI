package genetic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2015-05-20.
 */
public class Place {
    private String TYPE;
    private String TYPEPACKAGE;
    private String SIZEPACKAGE;
    private int freeSpace;
    private int positionX;
    private int positionY;

    private List<PackageGA> packageList = new ArrayList<>();

    public Place(String _type, String _typepackage, String _sizepackage, int _freeSpace, int _positionX, int _positionY) {
        this.TYPE = _type;
        this.TYPEPACKAGE = _typepackage;
        this.SIZEPACKAGE = _sizepackage;
        this.freeSpace = _freeSpace;
        this.positionX = _positionX;
        this.positionY = _positionY;
    }
    public Place(String _type, int _positionX, int _positionY) {
        this.TYPE = _type;
        this.TYPEPACKAGE = new String("empty");
        this.SIZEPACKAGE = new String("empty");
        this.freeSpace = 0;
        this.positionX = _positionX;
        this.positionY = _positionY;
    }
    public Place(Place other){
        this.TYPE = other.getTYPE();
        this.TYPEPACKAGE = other.getTYPEPACKAGE();
        this.SIZEPACKAGE = other.getSIZEPACKAGE();
        this.freeSpace = other.getFreeSpace();
        this.positionX = other.getPositionX();
        this.positionY = other.getPositionY();
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public String getTYPE() {
        return TYPE;
    }

    public String getTYPEPACKAGE() {
        return TYPEPACKAGE;
    }

    public String getSIZEPACKAGE() {
        return SIZEPACKAGE;
    }
    public int getPositionX(){
        return this.positionX;
    }
    public  int getPositionY(){
        return this.positionY;
    }
    public void addToPackageList(PackageGA _package) {
        this.packageList.add(_package);
        this.freeSpace = this.freeSpace - _package.getPackageSize().getValue();
        this.TYPEPACKAGE = _package.getPackageType().toString();
    }

    @Override
    public String toString() {
        return "Wolna przestrzen: " + this.freeSpace + " rodzaj pola: " + this.getTYPE()
                + " co siedzi: " + this.getTYPEPACKAGE() + " w rozmiarze " + this.getSIZEPACKAGE()
                +" dolozono "+this.packageList.size() +" pozycja "+this.getPositionX()+" "+this.getPositionY();
    }

    public List<PackageGA> getPackageList() {
        return packageList;
    }
}
