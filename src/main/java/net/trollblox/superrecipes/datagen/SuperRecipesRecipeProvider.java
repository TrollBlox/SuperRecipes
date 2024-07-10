package net.trollblox.superrecipes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class SuperRecipesRecipeProvider extends FabricRecipeProvider {
    public SuperRecipesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Crossbow Dispenser Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER, 1)
                .pattern("###")
                .pattern("#C#")
                .pattern("#D#")
                .input('#', Items.COBBLESTONE).input('D', Items.REDSTONE).input('C', Items.CROSSBOW)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(Items.DISPENSER)));
        generateMusicDiscRecipes(exporter);

        // Deepslate Dropper
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DROPPER, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("#R#")
                .input('#', Blocks.COBBLED_DEEPSLATE).input('R', Items.REDSTONE)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(Items.DROPPER)));

    }

    private static void generateMusicDiscRecipes(RecipeExporter exporter) {
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_5);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_11);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_13);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_CAT);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_CHIRP);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_BLOCKS);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_FAR);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_MALL);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_MELLOHI);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_OTHERSIDE);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_PIGSTEP);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_RELIC);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_STAL);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_STRAD);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_WAIT);
        offerMusicDiscDuplicationRecipe(exporter, Items.MUSIC_DISC_WARD);
    }

    public static void offerMusicDiscDuplicationRecipe(RecipeExporter exporter, ItemConvertible item) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, item, 1 )
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', Items.DIAMOND).input('X', item)
                .criterion(hasItem(item), conditionsFromItem(item))
                .offerTo(exporter, Identifier.of(getRecipeName(item)));
    }
}
