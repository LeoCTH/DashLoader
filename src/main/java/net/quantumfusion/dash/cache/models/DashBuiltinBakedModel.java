package net.quantumfusion.dash.cache.models;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BuiltinBakedModel;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import net.quantumfusion.dash.DashException;
import net.quantumfusion.dash.cache.DashIdentifier;
import net.quantumfusion.dash.cache.atlas.DashSprite;
import net.quantumfusion.dash.cache.models.components.DashModelOverrideList;
import net.quantumfusion.dash.cache.models.components.DashModelTransformation;
import net.quantumfusion.dash.cache.models.components.DashTransformation;
import net.quantumfusion.dash.mixin.BuiltinBakedModelAccessor;
import net.quantumfusion.dash.mixin.SpriteAtlasManagerAccessor;
import net.quantumfusion.dash.mixin.SpriteAtlasTextureAccessor;

import java.util.Map;

public class DashBuiltinBakedModel implements DashBakedModel {
    DashModelTransformation transformation;
    DashModelOverrideList itemPropertyOverrides;
    DashIdentifier spritePointer;
    boolean sideLit;

    public DashBuiltinBakedModel(DashModelTransformation transformation, DashModelOverrideList itemPropertyOverrides, DashIdentifier spritePointer, boolean sideLit) {
        this.transformation = transformation;
        this.itemPropertyOverrides = itemPropertyOverrides;
        this.spritePointer = spritePointer;
        this.sideLit = sideLit;
    }

    public DashBuiltinBakedModel(BuiltinBakedModel builtinBakedModel, DashModelLoader loader) {
        BuiltinBakedModelAccessor access = ((BuiltinBakedModelAccessor)builtinBakedModel);
        transformation = new DashModelTransformation(access.getTransformation());
        itemPropertyOverrides = new DashModelOverrideList(access.getItemPropertyOverrides(),loader);
        spritePointer = new DashIdentifier(builtinBakedModel.getSprite().getId());
        sideLit = access.getSideLit();
    }


    @Override
    public BakedModel toUndash(DashModelLoader loader) {
        Identifier id = spritePointer.toUndash();
        Sprite sprite = null;
        for (Map.Entry<Identifier, SpriteAtlasTexture> entry : ((SpriteAtlasManagerAccessor) loader.atlasManagerOut).getAtlases().entrySet()) {
            SpriteAtlasTexture spriteAtlasTexture = entry.getValue();
            if (((SpriteAtlasTextureAccessor) spriteAtlasTexture).getSprites().containsKey(id)) {
                sprite = spriteAtlasTexture.getSprite(id);
            }
        }
        if(sprite == null){
            throw new DashException("Sprite not found in deserialized sprite cache: " + id);
        }
        return new BuiltinBakedModel(transformation.toUndash(),itemPropertyOverrides.toUndash(loader),sprite,sideLit);
    }
}