package civitas.celestis.graphics.scene;

import civitas.celestis.graphics.ray.LightRay;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.graphics.vertex.Vertex;
import civitas.celestis.object.RaySource;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;
import java.util.function.Predicate;

/**
 * <h2>Scene</h2>
 * <p>
 * An object used to hold pre-rendered information about an in-game environment.
 * All instances of this object should be placed inside an AWT component
 * to ensure the computational load is distributed to the GPU.
 * </p>
 */
public class Scene {
    /**
     * Creates a new scene.
     *
     * @param vertices   List of predetermined vertices
     * @param raySources List of ray sources
     * @param shading    List of precalculated shaders
     */
    public Scene(
            @Nonnull Collection<Vertex> vertices,
            @Nonnull Collection<RaySource> raySources,
            @Nonnull Map<Vertex, Double> shading
    ) {
        // Copy collections to prevent immutable collections throwing exceptions
        this.vertices = new ArrayList<>(vertices);
        this.raySources = new ArrayList<>(raySources);
        this.shading = new HashMap<>(shading);
    }

    /**
     * Creates a new scene.
     *
     * @param vertices List of predetermined vertices
     */
    public Scene(@Nonnull Collection<Vertex> vertices) {
        this(vertices, new ArrayList<>(), new HashMap<>());
    }

    /**
     * Creates an empty scene.
     */
    public Scene() {
        this(new ArrayList<>(), new ArrayList<>(), new HashMap<>());
    }

    //
    // Rendering
    //
    // Method implementation is separate by design (for readability)
    // Performance overhead will be negligible as the JVM will most likely inline the impl methods
    //

    /**
     * Renders this scene with provided information.
     */
    public void render() {
        renderImpl();
    }

    /**
     * Clears all data from this scene.
     * This is a destructive action, and cannot be undone.
     */
    public void clear() {
        clearImpl();
    }

    //
    // Vertices
    //

    /**
     * Adds a vertex to this scene.
     *
     * @param v Vertex to add
     */
    public void addVertex(@Nonnull Vertex v) {
        vertices.add(v);
    }

    /**
     * Adds multiple vertices to this scene.
     *
     * @param v Collection of vertices to add
     */
    public void addVertices(@Nonnull Collection<Vertex> v) {
        vertices.addAll(v);
    }

    /**
     * Removes a vertex from this scene.
     *
     * @param v Vertex to remove
     */
    public void removeVertex(@Nonnull Vertex v) {
        vertices.remove(v);
    }

    /**
     * Removes multiple vertices from this scene.
     *
     * @param v Collection of vertices to remove
     */
    public void removeVertices(@Nonnull Collection<Vertex> v) {
        vertices.removeAll(v);
    }

    /**
     * Blacklists the list of vertices in this scene.
     *
     * @param filter Filter to apply
     */
    public void removeVertexIf(@Nonnull Predicate<? super Vertex> filter) {
        vertices.removeIf(filter);
    }

    /**
     * Clears the list of vertices in this scene.
     */
    public void clearVertices() {
        vertices.clear();
        shading.clear();
    }

    //
    // Lightning
    //

    /**
     * Adds a ray source to this scene.
     *
     * @param rs Ray source to add
     */
    public void addRaySource(@Nonnull RaySource rs) {
        raySources.add(rs);
    }

    /**
     * Adds multiple ray sources to this scene.
     *
     * @param rs Collection of ray sources to add
     */
    public void addRaySources(@Nonnull Collection<RaySource> rs) {
        raySources.addAll(rs);
    }

    /**
     * Removes a ray source from this scene.
     *
     * @param rs Ray source to remove
     */
    public void removeRaySource(@Nonnull RaySource rs) {
        raySources.remove(rs);
    }

    /**
     * Removes multiple ray sources from this scene.
     *
     * @param rs Collection of ray sources to remove
     */
    public void removeRaySources(@Nonnull Collection<RaySource> rs) {
        raySources.removeAll(rs);
    }

    /**
     * Blacklists the list of ray sources in this scene.
     *
     * @param filter Filter to apply
     */
    public void removeRaySourceIf(@Nonnull Predicate<? super RaySource> filter) {
        raySources.removeIf(filter);
    }

    /**
     * Clears the list of ray sources in this scene.
     */
    public void clearRaySources() {
        raySources.clear();
    }

    //
    // Getters
    //

    /**
     * Gets a list of all vertices in this scene.
     *
     * @return List of vertices
     */
    @Nonnull
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Gets a list of all ray sources in this scene.
     *
     * @return List of ray sources
     */
    public List<RaySource> getRaySources() {
        return new ArrayList<>(raySources);
    }

    /**
     * Gets a map of shading in this scene.
     *
     * @return Map of shading
     */
    @Nonnull
    public Map<Vertex, Double> getShading() {
        return new HashMap<>(shading);
    }

    //
    // Implementation of render()
    //

    /**
     * Renders this scene.
     */
    protected void renderImpl() {
        // Prevent unnecessary computations
        if (state != State.IDLE) return;

        // Mark state as rendering
        state = State.RENDERING;

        // Shoot rays
        for (final RaySource rs : getRaySources()) {
            for (final Ray r : rs.shoot()) {
                shootRay(r, 10, null);
            }
        }

        // Apply shaders
        shading.forEach((v, i) -> {
            // Loop once for now
            for (int j = 0; j < 1; j++) {
                vertices.remove(v);
                vertices.add(v.brighter());
            }
        });

        // Mark state as idle
        state = State.IDLE;
    }

    /**
     * Shoots a ray into this scene.
     *
     * @param ray    Ray to shoot
     * @param limit  Maximum allowed reflections
     * @param origin Vertex which the ray originated from
     */
    protected void shootRay(@Nonnull Ray ray, long limit, @Nullable Vertex origin) {
        // Terminate loop
        if (limit <= 0) return;

        // Initialize list of vertices
        final List<Vertex> vertices = getVertices();

        // Remove origin to prevent self-reflection
        if (origin != null) vertices.remove(origin);

        // Iterate through remaining vertices
        for (final Vertex v : vertices) {
            final Ray r = ray.reflect(v);
            if (r == null) continue;

            // TEMP
            if (r instanceof LightRay lr) {
                shading.put(v, shading.getOrDefault(v, 0d) + lr.intensity());
            }

            // Shoot reflecting ray
            shootRay(r, limit - 1, v);

            // Move on to next loop
            return;
        }

        // Loop will terminate here if no reflections were found
    }

    //
    // Implementation of clear()
    //

    /**
     * Clears this scene.
     */
    protected void clearImpl() {
        vertices.clear();
        shading.clear();
    }

    //
    // Variables
    //

    private final List<Vertex> vertices;
    private final List<RaySource> raySources;
    private final Map<Vertex, Double> shading;

    //
    // Markers
    //

    private State state = State.IDLE;

    public enum State {
        IDLE,
        RENDERING;
    }
}
