package fr.perso_fabric.init;

import fr.perso_fabric.Perso;
import fr.perso_fabric.utils.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ModItems {

    public static final ItemGroup groupe=Perso.persoGroup;
    public static final List<List> itemList=new ArrayList<List>(){
        {
            add(Arrays.asList("vibranium_ingot",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_stick",new Item(new Item.Settings().group(groupe))));
        }
        {
            add(Arrays.asList("heart_plant",new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(10f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,0),1f).build()).group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_helmet",new ModCustomArmor(ModCustomArmorMaterial.VIBRANIUM, EquipmentSlot.HEAD,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_chestplate",new ModCustomArmor(ModCustomArmorMaterial.VIBRANIUM, EquipmentSlot.CHEST,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_leggings",new ModCustomArmor(ModCustomArmorMaterial.VIBRANIUM, EquipmentSlot.LEGS,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_boots",new ModCustomArmor(ModCustomArmorMaterial.VIBRANIUM, EquipmentSlot.FEET,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_axe",new ModCustomAxe(ModToolMaterial.Vibranium,1,3f,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_pickaxe",new ModCustomPickaxe(ModToolMaterial.Vibranium,1,-2.8f,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_sword",new ModCustomSword(ModToolMaterial.Vibranium,1,3f,new FabricItemSettings().group(groupe))));
        }
        {
            add(Arrays.asList("vibranium_shovel",new ModCustomShovel(ModToolMaterial.Vibranium,1.5f,-3f,new FabricItemSettings().group(groupe))));
        }
        {
            add( Arrays.asList("vibranium_hoe",new ModCustomHoe(ModToolMaterial.Vibranium,-4,0.0f,new FabricItemSettings().group(groupe))));
        }
        {
            add( Arrays.asList("vibranium_dust",new Item(new Item.Settings().group(groupe))));
        }
        {
            add( Arrays.asList("iron_string",new Item(new Item.Settings().group(groupe))));
        }





    };

    //public static final Item vibranium_shield=new FabricShieldItem((Item.Settings) new FabricItemSettings().group(Perso.persoGroup),10,13,(Item)itemList.get(0).get(1));


    public static void registerAll(){
        for(int i=0; i<itemList.size();i++){
            registerItem(itemList.get(i).get(0).toString(),(Item)itemList.get(i).get(1));
        }
        //registerItem("vibranium_shield",vibranium_shield);
    }

    public static void registerItem(String name,Item item){
        Registry.register(Registry.ITEM,new Identifier(Perso.ModId,name),item);
    }
    public static void registerItem(String name, Block block){
        Registry.register(Registry.ITEM,new Identifier(Perso.ModId,name),new BlockItem(block,new FabricItemSettings().group(Perso.persoGroup)));
    }
}
