package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import fr.perso_fabric.recipe.SmelterRecipe;
import fr.perso_fabric.recipe.SmelterRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipe {
    public static void registerRecipe() {

        Registry.register(Registry.RECIPE_SERIALIZER,new Identifier(Perso.ModId, SmelterRecipeSerializer.ID),SmelterRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE,new Identifier(Perso.ModId, SmelterRecipe.Type.ID),SmelterRecipe.Type.INSTANCE);

    }
}
