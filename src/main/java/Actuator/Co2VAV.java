package Actuator;

public class Co2VAV {
    private int openingDegree;

    public Co2VAV(int o)
    {
        this.openingDegree = o;
    }

    public int getOpeningDegree() {
        return openingDegree;
    }

    public void setOpeningDegree(int openingDegree) {
        this.openingDegree = openingDegree;
    }
}
