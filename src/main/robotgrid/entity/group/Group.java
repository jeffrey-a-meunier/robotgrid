package robotgrid.entity.group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import robotgrid.entity.Entity;
import robotgrid.entity.abstractEntity.AbstractEntity;

public class Group extends AbstractEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public Set<AbstractEntity> _entities = new HashSet<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Group(final String name) {
        super(name);
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public boolean add(final AbstractEntity entity) {
        return _entities.add(entity);
    }

    public int count() {
        return _entities.size();
    }

    @Override
    public void info(final List<String> strings) {
        super.info(strings);
        List<String> names = new ArrayList<>();
        for (AbstractEntity entity : _entities) {
            names.add(entity.name);
        }
        strings.add("Entities=" + names);
        strings.add("Count=" + _entities.size());
    }

    public boolean remove(final AbstractEntity entity) {
        return _entities.remove(entity);
    }

    @Override
    public String typeName() {
        return "Group";
    }

}
