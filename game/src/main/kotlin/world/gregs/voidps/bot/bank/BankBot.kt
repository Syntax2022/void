package world.gregs.voidps.bot.bank

import world.gregs.voidps.bot.closeInterface
import world.gregs.voidps.bot.navigation.await
import world.gregs.voidps.bot.navigation.goToNearest
import world.gregs.voidps.engine.entity.character.contain.inventory
import world.gregs.voidps.engine.entity.character.player.Bot
import world.gregs.voidps.engine.entity.definition.ItemDefinitions
import world.gregs.voidps.engine.utility.get
import world.gregs.voidps.network.instruct.EnterInt
import world.gregs.voidps.network.instruct.InteractInterface
import world.gregs.voidps.network.instruct.InteractObject
import world.gregs.voidps.world.activity.bank.bank

private fun getItemId(id: String) : Int? = get<ItemDefinitions>().getOrNull(id)?.id

suspend fun Bot.openBank() {
    goToNearest("bank")
    val bank = player.viewport.objects.first { it.def.options[1] == "Use-quickly" }
    player.instructions.emit(InteractObject(objectId = bank.def.id, x = bank.tile.x, y = bank.tile.y, option = 2))
    await("bank")
}

suspend fun Bot.depositAll() {
    player.instructions.emit(InteractInterface(interfaceId = 762, componentId = 33, itemId = -1, itemSlot = -1, option = 0))
    await("tick")
    await("tick")
}

suspend fun Bot.depositAll(item: String, slot: Int = player.inventory.indexOf(item)) {
    player.instructions.emit(InteractInterface(interfaceId = 763, componentId = 0, itemId = getItemId(item) ?: return, itemSlot = slot, option = 5))
    await("tick")
}

suspend fun Bot.deposit(item: String, slot: Int = player.inventory.indexOf(item), amount: Int = 1) {
    val option = when (amount) {
        1 -> 0
        5 -> 1
        10 -> 2
        else -> 4
    }
    player.instructions.emit(InteractInterface(interfaceId = 763, componentId = 0, itemId = getItemId(item) ?: return, itemSlot = slot, option = option))
    if (option == 4) {
        player.instructions.emit(EnterInt(value = amount))
    }
    await("tick")
}

suspend fun Bot.withdraw(item: String, slot: Int = player.bank.indexOf(item), amount: Int = 1) {
    val option = when(amount) {
        1 -> 0
        5 -> 1
        10 -> 2
        else -> 4
    }
    player.instructions.emit(InteractInterface(interfaceId = 762, componentId = 93, itemId = getItemId(item) ?: return, itemSlot = slot, option = option))
    if (option == 4) {
        player.instructions.emit(EnterInt(value = amount))
    }
    await("tick")
}

suspend fun Bot.withdrawAll(item: String, slot: Int = player.bank.indexOf(item)) {
    player.instructions.emit(InteractInterface(interfaceId = 762, componentId = 93, itemId = getItemId(item) ?: return, itemSlot = slot, option = 5))
    await("tick")
}

suspend fun Bot.withdrawAllButOne(item: String, slot: Int = player.bank.indexOf(item)) {
    val id = get<ItemDefinitions>().getOrNull(item)?.id ?: return
    player.instructions.emit(InteractInterface(interfaceId = 762, componentId = 93, itemId = getItemId(item) ?: return, itemSlot = slot, option = 6))
    await("tick")
}

suspend fun Bot.closeBank() = closeInterface(762, 43)

suspend fun Bot.withdrawCoins() {
    if (!player.inventory.contains("coins")) {
        openBank()
        withdrawAllButOne("coins")
        closeBank()
    }
}