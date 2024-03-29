package novamachina.exnihilorei.rei.transition;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.TransitionRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransitionRecipeDisplay extends SimpleBasicDisplay {
    public TransitionRecipeDisplay(RecipeHolder<TransitionRecipe> recipe) {
        super(getIngredients(recipe.value()), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.value().getResult().getFluid()))));
    }

    private static List<EntryIngredient> getIngredients(TransitionRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getCatalyst()));
        list.add(EntryIngredients.of(recipe.getFluidInTank().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TransitionRecipeCategory.TRANSITION;
    }
}
