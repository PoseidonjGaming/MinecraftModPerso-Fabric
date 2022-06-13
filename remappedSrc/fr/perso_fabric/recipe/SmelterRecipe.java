package fr.perso_fabric.recipe;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SmelterRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final int intputAmountA;
    private final int intputAmountB;

    public Ingredient getInputA(){
        return this.inputA;
    }
    public Ingredient getInputB(){
        return this.inputB;
    }
    public int getInputAmountA(){
        return this.intputAmountA;
    }
    public int getInputAmountB(){
        return this.intputAmountB;
    }



    public SmelterRecipe(Identifier id, ItemStack output, Ingredient inputA, Ingredient inputB, int intputAmountA, int intputAmountB) {
        this.id = id;
        this.output = output;
        this.inputA = inputA;
        this.inputB = inputB;
        this.intputAmountA = intputAmountA;

        this.intputAmountB = intputAmountB;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        boolean canCraft=false;
        if(inputA.test(inventory.getStack(0))||inputA.test(inventory.getStack(1))){
            if(inventory.getStack(0).getCount()>=intputAmountA||inventory.getStack(1).getCount()>=intputAmountA){
                if(inputB.test(inventory.getStack(0))||inputB.test(inventory.getStack(1))){
                    if(inventory.getStack(0).getCount()>=intputAmountB||inventory.getStack(1).getCount()>=intputAmountB){
                        canCraft=true;
                    }

                }

            }
        }


        return canCraft;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SmelterRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SmelterRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "smelter";
    }


}
