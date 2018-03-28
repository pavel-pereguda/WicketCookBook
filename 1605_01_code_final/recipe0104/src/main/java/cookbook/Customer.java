package cookbook;

/**
 * @author Pavel Pereguda
 */
public class Customer extends Address{
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;

    String address;

    public String getAddress(Address address) {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}