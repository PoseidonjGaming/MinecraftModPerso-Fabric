package fr.perso_fabric.block;

import fr.perso_fabric.block.block_entity.VibraniumChestBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;



public class VibraniumChest extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty facing= Properties.HORIZONTAL_FACING;

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(facing,ctx.getPlayerFacing().getOpposite());
    }

    public VibraniumChest(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VibraniumChestBlockEntity(pos, state);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(facing)));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(facing,rotation.rotate(state.get(facing)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(facing);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(!world.isClient()){
            NamedScreenHandlerFactory screenHandlerFactory=state.createScreenHandlerFactory(world,pos);
            if (screenHandlerFactory!=null){
                player.openHandledScreen(screenHandlerFactory);

            }
        }


        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state,World world,BlockPos pos, BlockState newState, boolean moved){
      if(state.getBlock()!= newState.getBlock()){
          BlockEntity entity=world.getBlockEntity(pos);
          if(entity instanceof VibraniumChestBlockEntity){
              ItemScatterer.spawn(world,pos, (Inventory) entity);
              world.updateComparators(pos,this);
          }
          super.onStateReplaced(state,world,pos,newState,moved);
      }
    }
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

}
