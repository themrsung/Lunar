package civitas.celestis.graphics.scene;

import civitas.celestis.graphics.triangle.Vertex;
import jakarta.annotation.Nonnull;

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
     * @param vertices List of predetermined vertices
     * @param shading  List of precalculated shaders
     */
    public Scene(@Nonnull Collection<Vertex> vertices, @Nonnull Map<Vertex, Double> shading) {
        // Copy collections to prevent immutable collections throwing exceptions
        this.vertices = new ArrayList<>(vertices);
        this.shading = new HashMap<>(shading);
    }

    /**
     * Creates a new scene.
     *
     * @param vertices List of predetermined vertices
     */
    public Scene(@Nonnull Collection<Vertex> vertices) {
        this(vertices, new HashMap<>());
    }

    /**
     * Creates an empty scene.
     */
    public Scene() {
        this(new ArrayList<>(), new HashMap<>());
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
    public void add(@Nonnull Vertex v) {
        vertices.add(v);
    }

    /**
     * Adds multiple vertices to this scene.
     *
     * @param v Collection of vertices to add
     */
    public void add(@Nonnull Collection<Vertex> v) {
        vertices.addAll(v);
    }

    /**
     * Removes a vertex from this scene.
     *
     * @param v Vertex to remove
     */
    public void remove(@Nonnull Vertex v) {
        vertices.remove(v);
    }

    /**
     * Removes multiple vertices from this scene.
     *
     * @param v Collection of vertices to remove
     */
    public void remove(@Nonnull Collection<Vertex> v) {
        vertices.removeAll(v);
    }

    /**
     * Whitelists the list of vertices in this scene.
     *
     * @param filter Filter to apply
     */
    public void removeIf(@Nonnull Predicate<? super Vertex> filter) {
        vertices.removeIf(filter);
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
     * Gets a map of shading in this scene.
     *
     * @return Map of shading
     */
    @Nonnull
    public Map<Vertex, Double> getShading() {
        return new HashMap<>(shading);
    }

    //
    // Implementation
    //

    /**
     * Renders this scene.
     */
    protected void renderImpl() {

    }

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
    private final Map<Vertex, Double> shading;
}
