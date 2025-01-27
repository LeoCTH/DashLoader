package net.quantumfusion.dashloader.image;

import io.activej.serializer.annotations.Deserialize;
import io.activej.serializer.annotations.Serialize;
import net.minecraft.client.texture.Sprite;
import net.quantumfusion.dashloader.DashRegistry;
import net.quantumfusion.dashloader.data.Dashable;
import net.quantumfusion.dashloader.mixin.accessor.SpriteAnimationFrameAccessor;

public class DashSpriteAnimationFrame implements Dashable {
    @Serialize(order = 0)
    public int index;
    @Serialize(order = 1)
    public int time;

    public DashSpriteAnimationFrame(@Deserialize("index") int index,
                                    @Deserialize("time") int time) {
        this.index = index;
        this.time = time;
    }

    public DashSpriteAnimationFrame(Sprite.AnimationFrame animationFrame) {
        SpriteAnimationFrameAccessor access = ((SpriteAnimationFrameAccessor) animationFrame);
        index = access.getIndex();
        time = access.getTime();
    }

    @Override
    public <K> K toUndash(DashRegistry registry) {
        return (K) SpriteAnimationFrameAccessor.newSpriteFrame(index, time);
    }
}
