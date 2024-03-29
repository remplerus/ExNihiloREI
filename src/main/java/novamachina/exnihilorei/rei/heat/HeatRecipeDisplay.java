package novamachina.exnihilorei.rei.heat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;

import java.util.Collections;

public class HeatRecipeDisplay extends SimpleBasicDisplay {
    public HeatRecipeDisplay(RecipeHolder<HeatRecipe> recipe) {
        super(Collections.singletonList(EntryIngredients.of(recipe.value().getInputBlock())), Collections.emptyList());
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return HeatRecipeCategory.HEATING;
    }
}
