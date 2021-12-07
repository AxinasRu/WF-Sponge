package wfplugin.wfplugin.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.api.text.Text;

import java.util.List;

public class GuiUtils {
    public static int getGuiItems(ItemType type, CarriedInventory<? extends Carrier> inventory) {
        int result = 0;
        for (Inventory slot : inventory.slots())
            if (slot.contains(type)) result += slot.size();
        return result;
    }

    public static int clearGuiItems(ItemType type, int amount, CarriedInventory<? extends Carrier> inventory) {
        int result = 0;
        for (Inventory slot : inventory.slots()) {
            if (amount > 0) {
                int i = slot.totalItems();
                if (slot.contains(type)) {
                    slot.clear();
                    if (amount < i) {
                        slot.offer(ItemStack.of(type, i - amount));
                        result += amount;
                    } else
                        result += i;

                    amount -= i;
                }
            }
        }
        return result;
    }

    public static int addGuiItems(ItemType type, Text itemName, List<Text> lore, int amount, CarriedInventory<? extends Carrier> inventory) {
        Inventory freeSlots = inventory.query(QueryOperationTypes.ITEM_STACK_CUSTOM.of(stack -> stack.isEmpty() || stack.getType().equals(type)));
        int space = freeSlots.capacity() * type.getMaxStackQuantity() - freeSlots.totalItems();
        int toAdd = amount;
        if (space < toAdd)
            toAdd = space;

        ItemStack of = ItemStack.of(type, toAdd);
        if (itemName != null)
            of.offer(Keys.DISPLAY_NAME, itemName);
        if (lore != null)
            of.offer(Keys.ITEM_LORE, lore);
        inventory.offer(of);
        return toAdd;
    }

    public static int addGuiItems(ItemType type, int amount, CarriedInventory<? extends Carrier> inventory) {
        return addGuiItems(type, null, null, amount, inventory);
    }
}
