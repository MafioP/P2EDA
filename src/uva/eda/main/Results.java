package uva.eda.main;

public class Results {

    private long operationTime;
    private int intervalPeople;
    private int differentNames;
    private int intervalDays;

    public double getOperationTime() {
        return operationTime * 1e-9;
    }

    public void setOperationTime(long operationTime) {
        this.operationTime = operationTime;
    }

    public int getDifferentNames() {
        return differentNames;
    }

    public void setDifferentNames(int differentNames) {
        this.differentNames = differentNames;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }

    public int getIntervalPeople() {
        return intervalPeople;
    }

    public void setIntervalPeople(int intervalPeople) {
        this.intervalPeople = intervalPeople;
    }
}