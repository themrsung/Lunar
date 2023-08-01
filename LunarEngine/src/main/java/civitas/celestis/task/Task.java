package civitas.celestis.task;

/**
 * <h2>Task</h2>
 * <p>
 * A task.
 * Tasks can be scheduled to be executed in regular intervals.
 * </p>
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Duration between the last execution and now (in milliseconds)
     */
    void execute(long delta);

    /**
     * Gets the interval of this task.
     *
     * @return Interval of this task in milliseconds
     */
    default long interval() {return 1;}
}
