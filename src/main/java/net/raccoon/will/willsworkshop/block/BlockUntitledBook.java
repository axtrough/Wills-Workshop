package net.raccoon.will.willsworkshop.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockUntitledBook extends HorizontalDirectionalBlock {
    public static final MapCodec<BlockUntitledBook> CODEC = simpleCodec(BlockUntitledBook::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    //STRAIGHT
    public static final VoxelShape XZ = Block.box(2, 0, 3, 14, 4, 13);
    //SIDEWAYS
    public static final VoxelShape ZX = Block.box(3, 0, 2, 13, 4, 14);

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {return CODEC;}

    public BlockUntitledBook(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? XZ : ZX;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    
}




