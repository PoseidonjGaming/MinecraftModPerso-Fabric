package fr.perso_fabric.block;

import fr.perso_fabric.block_entity.VibraniumChestBlockEntity;
import fr.perso_fabric.init.ModBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;



public class VibraniumChest extends BlockWithEntity implements BlockEntityProvider {
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        /*Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
        if(blockEntity.getStack(0).isEmpty()){
            blockEntity.setStack(0, player.getStackInHand(hand).copy());
            player.getStackInHand(hand).setCount(0);
        }
        else {
            player.getInventory().offerOrDrop(blockEntity.getStack(0));
            blockEntity.setStack(0, ItemStack.EMPTY);
        }*/
        NamedScreenHandlerFactory screenHandlerFactory=state.createScreenHandlerFactory(world,pos);
        if (screenHandlerFactory!=null){
            player.openHandledScreen(screenHandlerFactory);
            //MinecraftClient.getInstance().player.sendChatMessage("dsqd");
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
