
package io.github.thebusybiscuit.exoticgarden;
 
 import com.narcissu14.sanity.Sanity;
 import me.mrCookieSlime.Slimefun.Lists.RecipeType;
 import me.mrCookieSlime.Slimefun.Objects.Category;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.potion.PotionEffect;
 
 
 
 
 
 
 public class CustomWine
   extends EGPlant
 {
   float food;
   float sanity;
   int alcohol;
   PotionEffect[] effects = null;
   
   public CustomWine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, int food, float sanity, int alcohol, PotionEffect[] effects) {
     super(category, item, name, recipeType, true, recipe);
     this.food = food;
     this.sanity = sanity;
     this.alcohol = alcohol;
     this.effects = effects;
   }
   
   public CustomWine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, int food, float sanity, int alcohol) {
     super(category, item, name, recipeType, true, recipe);
     this.food = food;
     this.sanity = sanity;
     this.alcohol = alcohol;
   }
   
   public CustomWine(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, int food, int alcohol) {
     super(category, item, name, recipeType, true, recipe);
     this.food = food;
     this.sanity = food;
     this.alcohol = alcohol;
   }
 
   
   public void restoreHunger(Player p) {
     int level = p.getFoodLevel() + (int)this.food;
     p.setFoodLevel((level > 20) ? 20 : level);
     p.setSaturation(this.food);
     if (ExoticGarden.instance.isSanityEnabled()) {
       Sanity.getInstance().addSanity(p, this.sanity);
     }
     ((PlayerAlcohol)ExoticGarden.drunkPlayers.get(p.getName())).addAlcohol(this.alcohol);
     if (this.effects != null) {
       for (PotionEffect potion : this.effects) {
         p.addPotionEffect(potion);
       }
     }
     int alcohol = ((PlayerAlcohol)ExoticGarden.drunkPlayers.get(p.getName())).getAlcohol();
     if (alcohol < 100 && alcohol > 50) {
       p.sendMessage("§8[§a异域森林§8] §e你已经半醉了，请适度饮酒！");
     } else if (alcohol >= 100) {
       p.sendMessage("§8[§a异域森林§8] §e你醉了！可以尝试食用一些可以§b解酒§e的消耗品");
       ExoticGarden.sendDrunkMessage(p);
     } 
   }
 }


