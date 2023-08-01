package civitas.celestis.event;

/**
 * <h2>HandlerPriority</h2>
 * <p>
 * The priority of a handler.
 * Lower priorities will be called first.
 * </p>
 */
public enum HandlerPriority {
    PREEMPTIVE,
    PRE_EARLY,
    EARLY,
    POST_EARLY,
    PRE_NORMAL,
    NORMAL,
    POST_NORMAL,
    PRE_LATE,
    LATE,
    POST_LATE,
    PERMISSIVE;
}
