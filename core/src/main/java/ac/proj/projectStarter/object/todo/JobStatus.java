package ac.proj.projectStarter.object.todo;

public enum JobStatus {
    ACTIVE(1),FINISHED(2),CANCELLED(3);
    private final int value;
    private JobStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
