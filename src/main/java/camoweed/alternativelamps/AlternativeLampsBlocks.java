package camoweed.alternativelamps;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLamp;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Materials;
import turniplabs.halplibe.helper.BlockBuilder;

import static camoweed.alternativelamps.AlternativeLampsMod.MOD_ID;

public class AlternativeLampsBlocks {

	public static Block<?> ALT_LAMP_IDLE;
	public static Block<?> ALT_LAMP_ACTIVE;
	public static Block<?> ALT_LAMP_INVERTED_IDLE;
	public static Block<?> ALT_LAMP_INVERTED_ACTIVE;

	private AlternativeLampsBlocks(){}

	private static Block<?> addBlock(Block<?> block) {
		return block;
	}

	private static String formatTranslationKey(String key) {
		return String.format("%s.%s", MOD_ID, key);
	}

	private static String formatName(String name) {
		return String.format("%s:block/%s", MOD_ID, name);
	}
	public static void afterBlockInit() {
		ALT_LAMP_IDLE = addBlock(Blocks.register(
			formatTranslationKey("alternative.lamp.idle"),
			formatName("alternative_lamp_idle"),
			14897,
			block -> new BlockLogicAlternativeLamp(block, false, false))
		);
		ALT_LAMP_ACTIVE = addBlock(Blocks.register(
			formatTranslationKey("alternative.lamp.active"),
			formatName("alternative_lamp_active"),
			14898,
			block -> new BlockLogicAlternativeLamp(block, true, false))
			.withLightEmission(15)
			.withLightBlock(15)
		);
		ALT_LAMP_INVERTED_IDLE = addBlock(Blocks.register(
			formatTranslationKey("alternative.lamp.inverted.idle"),
			formatName("alternative_lamp_inverted_idle"),
			14899,
			block -> new BlockLogicAlternativeLamp(block, false, true))
		//	.withLightEmission(15)
		//	.withLightBlock(15)
		);
		ALT_LAMP_INVERTED_ACTIVE = addBlock(Blocks.register(
			formatTranslationKey("alternative.lamp.inverted.active"),
			formatName("alternative_lamp_inverted_active"),
			14900,
			block -> new BlockLogicAlternativeLamp(block, true, true))
			.withLightEmission(15)
			.withLightBlock(15)
		);
	}
}
