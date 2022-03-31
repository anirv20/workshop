package Actuator;

public class Ventilator {
    private Boolean status;

    public Ventilator(Boolean status)
    {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
