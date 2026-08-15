package camoweed.alternativelamps;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryUndyeing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.helper.RecipeBuilder;

import java.util.List;

import static camoweed.alternativelamps.AlternativeLampsMod.MOD_ID;

public class AlternativeLampsRecipes {

	public static void registerItemGroup(String groupName, List<ItemStack> groupList) {
		Registries.ITEM_GROUPS.register(MOD_ID + ":" + groupName, groupList);
	}

	private static void registerBlockDyeRecipes(String recipeKey, String groupName, Block<?> blockDyed,Block<?> blockUndyed, boolean useUpperMeta) {
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				blockDyed.getDefaultStack(), useUpperMeta, false
			)
		);
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_undyeing",
			new RecipeEntryUndyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				blockUndyed.getDefaultStack()
			)
		);
	}

	public static void initializeNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);

		List<ItemStack> lamps = Registries.stackListOf(Blocks.LAMP_IDLE);

		for (DyeColor c : DyeColor.values()) {
			lamps.add(new ItemStack(AlternativeLampsBlocks.ALT_LAMP_IDLE,1 ,c.blockMeta));
		}
		registerItemGroup("lamps", lamps);
	}
	public static void initializeRecipes() {
		registerBlockDyeRecipes("alternative_lamp","lamps",AlternativeLampsBlocks.ALT_LAMP_IDLE,AlternativeLampsBlocks.ALT_LAMP_IDLE, false);
		RecipeBuilder.Shaped(MOD_ID, " D ", "DOD", " D ")
			.addInput('D', Items.DUST_REDSTONE)
			.addInput('O', Blocks.GLOWSTONE)
			.create("alternative_lamp", new ItemStack(AlternativeLampsBlocks.ALT_LAMP_IDLE));
	}
}
