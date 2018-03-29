package cookbook;

import org.apache.wicket.IClusterable;

import java.util.Date;

/**
 * Created by q3kasper on 29.03.2018.
 */
public class Person implements IClusterable
{
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private Address address;

    /**
     * Construct.
     */
    public Person()
    {
    }

    /**
     * Construct.
     *
     * @param name
     *            name
     * @param lastName
     *            last name
     */
    public Person(String name, String lastName)
    {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * Gets the dateOfBirth.
     *
     * @return dateOfBirth
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Sets the dateOfBirth.
     *
     * @param dateOfBirth
     *            dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the lastName.
     *
     * @return lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the lastName.
     *
     * @param lastName
     *            lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the address.
     *
     * @return address
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address
     *            address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }

    /**
     * @return full name of the person
     */
    public String getFullName()
    {
        return name + " " + lastName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "[Person name=" + name + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth +
                "]";
    }
}
