package camoweed.alternativelamps;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.dependency.Key;

public class AlternativeLampsMod implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("alternativelamps", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.AFTER_BLOCK_INIT.listen(Key.of(MOD_ID), AlternativeLampsBlocks::afterBlockInit);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), AlternativeLampsRecipes::initializeNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), AlternativeLampsRecipes::initializeRecipes);
		LOGGER.info("Alternative Lamps initialized.");
	}
	public void beforeGameStart() {}
	public void afterGameStart() {}
}
