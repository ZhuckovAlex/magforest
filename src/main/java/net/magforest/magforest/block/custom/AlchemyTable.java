package net.magforest.magforest.block.custom;

import net.magforest.magforest.container.LightningMachineContainer;
import net.magforest.magforest.tileentity.LightMachineTile;
import net.magforest.magforest.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class AlchemyTable extends HorizontalBlock {
    public AlchemyTable(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(8, 5, 1.1302599999999998, 13, 11, 15.13026),
            Block.makeCuboidShape(-0.4721000000000011, 4.9, 2, 7.5279000000000025, 8.9, 8),
            Block.makeCuboidShape(-13, 5, 1.1302599999999998, -8, 11, 15.13026),
            Block.makeCuboidShape(-16, 0, 0.13025999999999982, 16, 5, 16.13026),
            Block.makeCuboidShape(-16, 11, 0.13025999999999982, 16, 16, 16.13026)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(1.43487, 5, 8.56513, 15.43487, 11, 13.56513),
            Block.makeCuboidShape(8.56513, 4.9, 0.09302999999999884, 14.56513, 8.9, 8.093030000000002),
            Block.makeCuboidShape(1.43487, 5, -12.43487, 15.43487, 11, -7.43487),
            Block.makeCuboidShape(0.4348700000000001, 0, -15.43487, 16.43487, 5, 16.56513),
            Block.makeCuboidShape(0.4348700000000001, 11, -15.43487, 16.43487, 16, 16.56513)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(1.56513, 5, 2.56513, 15.56513, 11, 7.56513),
            Block.makeCuboidShape(2.43487, 4.9, 8.037229999999997, 8.43487, 8.9, 16.03723),
            Block.makeCuboidShape(1.56513, 5, 23.56513, 15.56513, 11, 28.56513),
            Block.makeCuboidShape(0.5651299999999999, 0, -0.4348700000000001, 16.56513, 5, 31.56513),
            Block.makeCuboidShape(0.5651299999999999, 11, -0.4348700000000001, 16.56513, 16, 31.56513)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(3, 5, 1, 8, 11, 15),
            Block.makeCuboidShape(8.4721, 4.9, 8.13026, 16.4721, 8.9, 14.13026),
            Block.makeCuboidShape(24, 5, 1, 29, 11, 15),
            Block.makeCuboidShape(0, 0, 0, 32, 5, 16),
            Block.makeCuboidShape(0, 11, 0, 32, 16, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }


    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
