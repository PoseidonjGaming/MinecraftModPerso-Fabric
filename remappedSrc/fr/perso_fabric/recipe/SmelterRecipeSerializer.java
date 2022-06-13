package fr.perso_fabric.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SmelterRecipeSerializer implements RecipeSerializer<SmelterRecipe> {
    public static final SmelterRecipeSerializer INSTANCE = new SmelterRecipeSerializer();
    public static final String ID = "smelter";
    // this is the name given in the json file

    private SmelterRecipeSerializer(){

    }


    @Override
    public SmelterRecipe read(Identifier id, JsonObject json) {
        SmelterRecipeJsonFormat recipeJsonFormat=new Gson().fromJson(json,SmelterRecipeJsonFormat.class);
        if (recipeJsonFormat.inputA == null || recipeJsonFormat.inputB == null || recipeJsonFormat.output == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        Ingredient inputA=Ingredient.fromJson(recipeJsonFormat.inputA);
        Ingredient inputB=Ingredient.fromJson(recipeJsonFormat.inputB);

        Item outputItem= Registry.ITEM.getOrEmpty(new Identifier(recipeJsonFormat.output)).get();
        ItemStack output=new ItemStack(outputItem,1);

        return new SmelterRecipe(id,output,inputA,inputB, recipeJsonFormat.inputAmountA, recipeJsonFormat.inputAmountB);
    }

    @Override
    public SmelterRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient inputA = Ingredient.fromPacket(buf);
        Ingredient inputB = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();

        return new SmelterRecipe(id,output,inputA,inputB, buf.getInt(2), buf.getInt(3));

    }

    @Override
    public void write(PacketByteBuf buf, SmelterRecipe recipe) {

        recipe.getInputA().write(buf);
        recipe.getInputB().write(buf);
        buf.writeInt(recipe.getInputAmountA());
        buf.writeInt(recipe.getInputAmountB());
        buf.writeItemStack(recipe.getOutput());

    }
}
