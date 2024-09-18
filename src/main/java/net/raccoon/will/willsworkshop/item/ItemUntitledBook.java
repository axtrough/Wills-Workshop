package net.raccoon.will.willsworkshop.item;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import org.checkerframework.checker.units.qual.Speed;


public class ItemUntitledBook extends Item {
    public ItemUntitledBook(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos().relative(context.getClickedFace());
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!level.isClientSide() && player != null) {
            BlockState blockState = WWBlockRegistry.UNTITLED_BOOK_BLOCK.get().defaultBlockState();
            Direction playerFacing = player.getDirection();

            if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, playerFacing);
                stack.shrink(1);
            }

            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel) level;

                if (serverLevel.isEmptyBlock(blockPos)) {
                    serverLevel.setBlock(blockPos, blockState, 3);
                    level.playSound(null, blockPos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide()) {

            Vec3 lookDirection = player.getLookAngle();
            double x = player.getX() + lookDirection.x * 0.5;
            double y = player.getY() + player.getEyeHeight() - 0.5;
            double z = player.getZ() + lookDirection.z * 0.75;

            SmallFireball fireball = new SmallFireball(level, x, y, z, lookDirection);
            fireball.setPos(x, y, z);
            fireball.shoot(lookDirection.x, lookDirection.y, lookDirection.z, 1.5F, 0.0F);
            level.addFreshEntity(fireball);
        }
        return InteractionResultHolder.pass(itemstack);
    }
}




