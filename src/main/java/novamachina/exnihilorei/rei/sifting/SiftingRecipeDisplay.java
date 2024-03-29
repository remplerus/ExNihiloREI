package novamachina.exnihilorei.rei.sifting;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;

import java.util.ArrayList;
import java.util.List;

public class SiftingRecipeDisplay extends SimpleBasicDisplay {
    public SiftingRecipeDisplay(RecipeHolder<SiftingRecipe> recipe) {
        super(getInputs(recipe.value()), getIngredients(recipe.value()));
    }

    private static List<EntryIngredient> getInputs(SiftingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        return list;
    }

    private static List<EntryIngredient> getIngredients(SiftingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.of(recipe.getDrop()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return SiftingRecipeCategory.SIFTING;
    }
}
