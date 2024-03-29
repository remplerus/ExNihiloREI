package novamachina.exnihilorei.rei.melting;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeltingRecipeDisplay extends SimpleBasicDisplay {
    public MeltingRecipeDisplay(RecipeHolder<MeltingRecipe> recipe) {
        super(getIngredients(recipe.value()), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.value().getResultFluid().getFluid()))));
    }

    private static List<EntryIngredient> getIngredients(MeltingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MeltingRecipeCategory.MELTING;
    }
}
