package dataStructures;

public class ObservationPoint {
    private final Double distance;
    private final String type;
    public ObservationPoint(Double distance, String type) {
        this.distance = distance;
        this.type = type;
    }

    public Double getDistance() {
        return distance;
    }

    public String getType() {
        return type;
    }
}
