package camoweed.alternativelamps;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericLamp;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.dependency.Key;
import static camoweed.alternativelamps.AlternativeLampsMod.MOD_ID;

public class AlternativeLampsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.AFTER_CLIENT_START.listen(Key.of(MOD_ID), AlternativeLampsClient::afterClientStart);
		ClientEvents.BEFORE_CLIENT_START.listen(Key.of(MOD_ID), AlternativeLampsClient::beforeClientStart);
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID), AlternativeLampsClient::initBlockModels);
	}

	public static void beforeClientStart() {
		AlternativeLampsClient.registerTextures();
	}

	public static void registerTextures() {
		for (final AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			TextureHelper.initializeAllFiles(MOD_ID, stitcher, true);
		}
	}
	public static void afterClientStart() {

	}

	public static void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(new BlockModelGenericLamp<>(AlternativeLampsBlocks.ALT_LAMP_IDLE,"alternativelamps:block/alternative_lamp/idle/%s","alternativelamps:block/alternative_lamp/active/%s"));
		dispatcher.addDispatch(new BlockModelGenericLamp<>(AlternativeLampsBlocks.ALT_LAMP_ACTIVE,"alternativelamps:block/alternative_lamp/active/%s","alternativelamps:block/alternative_lamp/active/%s"));
		dispatcher.addDispatch(new BlockModelGenericLamp<>(AlternativeLampsBlocks.ALT_LAMP_INVERTED_IDLE,"alternativelamps:block/alternative_lamp/idle_inverted/%s","alternativelamps:block/alternative_lamp/active_inverted/%s"));
		dispatcher.addDispatch(new BlockModelGenericLamp<>(AlternativeLampsBlocks.ALT_LAMP_INVERTED_ACTIVE,"alternativelamps:block/alternative_lamp/active_inverted/%s","alternativelamps:block/alternative_lamp/active_inverted/%s"));
	}
}
