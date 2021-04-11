package net.quantumfusion.dash.cache.models;


import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.quantumfusion.dash.cache.DashIdentifier;

public class DashModelIdentifier extends DashIdentifier {
    String variant;


    public DashModelIdentifier(String namespace, String path,String variant) {
        super(namespace, path);
        this.variant = variant;
    }
    public DashModelIdentifier(ModelIdentifier identifier) {
        super(identifier);
        this.variant = identifier.getVariant();
    }

    @Override
    public Identifier toUndash() {
        return new ModelIdentifier(new Identifier(namespace,path),variant);
    }
}