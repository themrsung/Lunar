package civitas.celestis.object.world;

import civitas.celestis.object.base.BaseObject;
import civitas.celestis.object.unique.AbstractUnique;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class AbstractWorld extends AbstractUnique implements World {
    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     * @param objects  List of initial objects in this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects) {
        super(uniqueId);
        this.name = name;
        this.objects = objects;
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull String name) {
        this(uniqueId, name, new ArrayList<>());
    }

    @Nonnull
    private final String name;
    @Nonnull
    private final List<BaseObject> objects;

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public List<BaseObject> getObjects() {
        return new ArrayList<>(objects);
    }

    @Override
    public void addObject(@Nonnull BaseObject obj) {
        objects.add(obj);
    }

    @Override
    public void addObjects(@Nonnull Collection<BaseObject> obj) {
        objects.addAll(obj);
    }

    @Override
    public void removeObject(@Nonnull BaseObject obj) {
        objects.remove(obj);
    }

    @Override
    public void removeObjects(@Nonnull Collection<BaseObject> obj) {
        objects.removeAll(obj);
    }
}
