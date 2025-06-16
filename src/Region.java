/*
Filename: Region.java
Description: This file defines the abstract class Region, which serves as a base class for different types of regions in a disease simulation model.
*/

public abstract class Region {
    private char type;
    private double temp;

    // Constructor
    public Region(char type, double temp) {
        this.type = type;
        this.temp = temp;
    }

    // Accessor and mutator methods
    public char getName() {
        return type;
    }
    public double getTemp() {
        return temp;
    }
    public void setType(char name) {
        this.type = name;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /*
     * Compares the temperature of this region with another region.
     * @param other The other region to compare against.
     * @return A negative value if this region's temperature is lower, zero if they are equal, and a positive value if this region's temperature is higher.
     */
    public double compareToTemp(Region other) {
        return this.temp - other.getTemp();
    }

    /*
     * Abstract method to calculate the infection rate influence of a disease on this region.
     * This method must be implemented by subclasses of Region.
     * @param d The disease for which the infection rate influence is calculated.
     * @return The calculated infection rate influence as a double.
     */
    public abstract double calcInfectionRateInfluence(Disease d);

    /*
     * Returns a string representation of the Region object.
     * This includes the type and temperature of the region.
     * @return A string representation of the Region object.
     */
    public String toString() {
        return "Region{" +
                "Type=" + type +
                ", temp=" + temp +
                '}';
    }
}
