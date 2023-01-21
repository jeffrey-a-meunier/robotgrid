package robotgrid.entity;

public enum Height {

    None("None"),
    Low("Low"),
    High("High");

    public final String name;

    private Height(final String name) {
        this.name = name;
    }

}
