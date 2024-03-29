package novamachina.exnihilorei.rei.harvest;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HarvestRecipeDisplay extends SimpleBasicDisplay {
    public HarvestRecipeDisplay(RecipeHolder<HarvestRecipe> recipe) {
        super(Collections.singletonList(EntryIngredient.of(EntryIngredients.ofIngredient(recipe.value().getInput()))), getIngredients(recipe.value()));
    }

    private static List<EntryIngredient> getIngredients(HarvestRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        for (ItemStackWithChance entry : recipe.getDrops()) {
            list.add(EntryIngredients.of(entry.getStack()));
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return HarvestRecipeCategory.HARVESTING;
    }
}
