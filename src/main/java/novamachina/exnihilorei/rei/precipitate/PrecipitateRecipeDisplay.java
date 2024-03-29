package novamachina.exnihilorei.rei.precipitate;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.PrecipitateRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrecipitateRecipeDisplay extends SimpleBasicDisplay {
    public PrecipitateRecipeDisplay(RecipeHolder<PrecipitateRecipe> recipe) {
        super(getIngredients(recipe.value()), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.value().getOutput()))));
    }

    private static List<EntryIngredient> getIngredients(PrecipitateRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        list.add(EntryIngredients.of(recipe.getFluid().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PrecipitateRecipeCategory.PRECIPITATE;
    }
}
