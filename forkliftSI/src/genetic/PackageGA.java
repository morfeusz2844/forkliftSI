package genetic;

import model.Package;

/**
 * Created by Patryk on 2015-05-19.
 */
public class PackageGA extends model.Package {
//    private static final String TYPE="PackageGA";
    private int identication;
    public PackageGA(Package _package, int _id) {
        super(_package.getPackageSize(), _package.getPackageType());
        this.identication = _id;
    }

    public PackageGA(PackageGA other){
        super(other.getPackageSize(),other.getPackageType());
        this.identication = other.getIdentication();
    }

    public int getIdentication(){
        return this.identication;
    }

    @Override
    public String toString() {
        return this.getPackageSize()+" "+this.getPackageType()+" "+identication;
    }
}
