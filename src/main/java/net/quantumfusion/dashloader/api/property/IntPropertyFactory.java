package net.quantumfusion.dashloader.api.property;

import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.quantumfusion.dashloader.DashRegistry;
import net.quantumfusion.dashloader.blockstate.property.DashIntProperty;
import net.quantumfusion.dashloader.blockstate.property.DashProperty;
import net.quantumfusion.dashloader.blockstate.property.value.DashIntValue;
import net.quantumfusion.dashloader.blockstate.property.value.DashPropertyValue;

public class IntPropertyFactory implements PropertyFactory {
    public IntPropertyFactory() {
    }

    @Override
    public <K> DashProperty toDash(Property property, DashRegistry registry, K var1) {
        return new DashIntProperty((IntProperty) property);
    }

    @Override
    public <K> DashPropertyValue toDash(Comparable<?> comparable, DashRegistry registry, K var1) {
        return new DashIntValue((Integer) comparable);
    }

    @Override
    public Class<? extends Property<?>> getType() {
        return IntProperty.class;
    }

    @Override
    public Class<? extends DashProperty> getDashType() {
        return DashIntProperty.class;
    }

    @Override
    public Class<? extends DashPropertyValue> getDashValueType() {
        return DashIntValue.class;
    }

}
