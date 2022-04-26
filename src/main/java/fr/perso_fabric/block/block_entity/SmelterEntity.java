package fr.perso_fabric.block.block_entity;

import fr.perso_fabric.block.Smelter;
import fr.perso_fabric.init.ModBlockEntity;
import fr.perso_fabric.inventory.ImplementedInventory;
import fr.perso_fabric.recipe.SmelterRecipe;
import fr.perso_fabric.screen.SmelterScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SmelterEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    protected final PropertyDelegate propertyDelegate;
    private int progress=0;
    private int maxProgress=250;
    private int fuelTime=0;
    private int maxFuelTime=0;
    private final DefaultedList<ItemStack> inventory= DefaultedList.ofSize(4,ItemStack.EMPTY);
    public SmelterEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.smelter_blockEntity, pos, state);
        this.propertyDelegate=new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index){
                    case 0: return SmelterEntity.this.progress;
                    case 1: return SmelterEntity.this.maxProgress;
                    case 2: return SmelterEntity.this.fuelTime;
                    case 3: return SmelterEntity.this.maxFuelTime;
                    default:return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0:  SmelterEntity.this.progress=value; break;
                    case 1:  SmelterEntity.this.maxProgress=value; break;
                    case 2:  SmelterEntity.this.fuelTime=value; break;
                    case 3:  SmelterEntity.this.maxFuelTime=value; break;

                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Smelter");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SmelterScreenHandler(syncId,inv,this,this.propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);

        progress=nbt.getInt("smelter.progress");
        fuelTime=nbt.getInt("smelter.fuelTime");
        maxFuelTime=nbt.getInt("smelter.maxFuelTime");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);

        nbt.putInt("smelter.progress",progress);
        nbt.putInt("smelter.fuelTime",fuelTime);
        nbt.putInt("smelter.maxFuelTime",maxFuelTime);
    }


    private void consumeFuel() {
        if(!getStack(3).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(3, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, SmelterEntity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe(entity)) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(SmelterEntity entity) {
        return !entity.getStack(3).isEmpty();
    }

    private static boolean isConsumingFuel(SmelterEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(SmelterEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SmelterRecipe> match = world.getRecipeManager().getFirstMatch(SmelterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(SmelterEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());

        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SmelterRecipe> match = world.getRecipeManager().getFirstMatch(SmelterRecipe.Type.INSTANCE, inventory, world);
        Ingredient inputA=match.get().getInputA();
        Ingredient inputB=match.get().getInputB();
        NbtCompound nbtCompound = null;

        if(match.isPresent()) {

            if(inputA.test(entity.getStack(0))){
                entity.removeStack(0,match.get().getInputAmountA());
                nbtCompound=entity.getStack(0).getNbt();
            } else if (inputB.test(entity.getStack(0))) {
                entity.removeStack(0,match.get().getInputAmountB());
            }
            if(inputA.test(entity.getStack(1))){
                entity.removeStack(1,match.get().getInputAmountA());
                nbtCompound=entity.getStack(1).getNbt();
            } else if (inputB.test(entity.getStack(1))) {
                entity.removeStack(1,match.get().getInputAmountB());
            }



            ItemStack output=new ItemStack(match.get().getOutput().getItem(),entity.getStack(2).getCount() + 1);
            output.setNbt(nbtCompound);
            entity.setStack(2,output);

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(2).getItem() == output.getItem() || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }
}
