package net.redcat.plushies.blocks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class AstralBasePlushie extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public AstralBasePlushie() {
        super(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> Shapes.or(box(5, 0, 6, 11, 7, 10), box(8.25, 0, 9.75, 11.25, 2, 14.75), box(4.75, 0, 9.75, 7.75, 2, 14.75), box(10.75, 0.75, 6, 12.75, 6.75, 9), box(3.25, 0.75, 6, 5.25, 6.75, 9), box(3, 7, 4, 13, 17, 12));
            case NORTH -> Shapes.or(box(5, 0, 6, 11, 7, 10), box(4.75, 0, 1.25, 7.75, 2, 6.25), box(8.25, 0, 1.25, 11.25, 2, 6.25), box(3.25, 0.75, 7, 5.25, 6.75, 10), box(10.75, 0.75, 7, 12.75, 6.75, 10), box(3, 7, 4, 13, 17, 12));
            case EAST -> Shapes.or(box(6, 0, 5, 10, 7, 11), box(9.75, 0, 4.75, 14.75, 2, 7.75), box(9.75, 0, 8.25, 14.75, 2, 11.25), box(6, 0.75, 3.25, 9, 6.75, 5.25), box(6, 0.75, 10.75, 9, 6.75, 12.75), box(4, 7, 3, 12, 17, 13));
            case WEST -> Shapes.or(box(6, 0, 5, 10, 7, 11), box(1.25, 0, 8.25, 6.25, 2, 11.25), box(1.25, 0, 4.75, 6.25, 2, 7.75), box(7, 0.75, 10.75, 10, 6.75, 12.75), box(7, 0.75, 3.25, 10, 6.75, 5.25), box(4, 7, 3, 12, 17, 13));
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        double hitX = hit.getLocation().x;
        double hitY = hit.getLocation().y;
        double hitZ = hit.getLocation().z;
        Direction direction = hit.getDirection();
        PlushieClick.execute(world, x, y, z);
        return InteractionResult.SUCCESS;
    }
}
