package net.tuna.experiment_three.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import java.util.Random;


public class LightningStick extends Item {
    private final Random rand;
    private final int boundX = 5; //sets the lightning bolt spread
    private final int boundY = 5; //spread = (boundX - 1) * (boundY - 1)

    public LightningStick(Properties properties) {
        super(properties);
        rand = new Random();
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        //Ensure lightning doesn't spawn only on clientside
        if(world.isClientSide())
            return InteractionResult.PASS;

        int randomFactorX = rand.nextInt(0, boundX);
        int randomFactorZ = rand.nextInt(0, boundY);
        BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10).offset(randomFactorX, 0, randomFactorZ);
        int surfaceY = world.getHeight(Heightmap.Types.WORLD_SURFACE, frontOfPlayer.getX(), frontOfPlayer.getZ());
        BlockPos ground = frontOfPlayer.atY(surfaceY);
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
        lightningBolt.setPos(ground.getCenter());
        world.addFreshEntity(lightningBolt);
        return InteractionResult.SUCCESS;
    }
}
