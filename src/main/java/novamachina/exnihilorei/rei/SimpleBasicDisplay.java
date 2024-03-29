package novamachina.exnihilorei.rei;

import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;

import java.util.List;

public abstract class SimpleBasicDisplay extends BasicDisplay {
    public SimpleBasicDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }
}
