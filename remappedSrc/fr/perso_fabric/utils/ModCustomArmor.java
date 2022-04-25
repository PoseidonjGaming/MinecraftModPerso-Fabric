package fr.perso_fabric.utils;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ModCustomArmor extends ArmorItem {
    public ModCustomArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    List<Map<ArmorMaterial,StatusEffectInstance>> listEffect=Arrays.asList(
            (new ImmutableMap.Builder<ArmorMaterial,StatusEffectInstance>()).put(ModCustomArmorMaterial.VIBRANIUM,new StatusEffectInstance(StatusEffects.STRENGTH,1,1)).build(),
            (new ImmutableMap.Builder<ArmorMaterial,StatusEffectInstance>()).put(ModCustomArmorMaterial.VIBRANIUM,new StatusEffectInstance(StatusEffects.SPEED,1,1)).build()
    );



    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient){
            if(entity instanceof PlayerEntity){
                PlayerEntity player=(PlayerEntity) entity;
                if(hasFullOfArmorOn(player)){
                    evaluateArmorEffect(player);
                }
            }
        }
        super.inventoryTick(stack,world,entity,slot,selected);
    }

    private void evaluateArmorEffect(PlayerEntity player){
       for(int i=0; i<listEffect.size();i++){
           for(Map.Entry<ArmorMaterial,StatusEffectInstance> entry: listEffect.get(i).entrySet()){
               ArmorMaterial mapArmorMaterial= entry.getKey();
               StatusEffectInstance mapStatusEffect=entry.getValue();
               if(hasCorrectArmorOn(mapArmorMaterial,player)){
                   addStatusEffectForMaterial(player,mapArmorMaterial,mapStatusEffect);
               }
           }
       }



    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial,StatusEffectInstance mapStatusEffect){
       boolean hasPlayerEffect=player.hasStatusEffect(mapStatusEffect.getEffectType());
       if(hasCorrectArmorOn(mapArmorMaterial,player)&& !hasPlayerEffect){
           player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),mapStatusEffect.getDuration(),mapStatusEffect.getAmplifier()));
       }
    }

    private boolean hasFullOfArmorOn(PlayerEntity player){
        ItemStack boots=player.getInventory().getArmorStack(0);
        ItemStack leggings=player.getInventory().getArmorStack(1);
        ItemStack chestPlate=player.getInventory().getArmorStack(2);
        ItemStack helmet=player.getInventory().getArmorStack(3);
        return !helmet.isEmpty() && !chestPlate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player){
        ArmorItem boots=((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings=((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem chestPlate=((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet=((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial()==material && leggings.getMaterial()==material && chestPlate.getMaterial()==material && boots.getMaterial()==material;
    }


}
