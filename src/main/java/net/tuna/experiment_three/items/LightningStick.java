package net.tuna.experiment_three.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;


public class LightningStick extends Item {
    public LightningStick(Properties properties) {
        super(properties);
    }

    private Vec3i getSurfaceBlock(Level world, int x, int z) {
        int surfaceY = world.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
        return new Vec3i(x, surfaceY, z);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        //Ensure lightning doesn't spawn only on clientside
        if(world.isClientSide())
            return InteractionResult.PASS;

        BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10);
        int surfaceY = world.getHeight(Heightmap.Types.WORLD_SURFACE, frontOfPlayer.getX(), frontOfPlayer.getZ());
        BlockPos ground = frontOfPlayer.atY(surfaceY);
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPos(ground.getCenter());
        world.addFreshEntity(lightningBolt);
        return InteractionResult.SUCCESS;
    }
}
