package rs.dusk.engine.model.entity.index.npc

import rs.dusk.engine.client.viewport.ViewportTask.Companion.VIEW_RADIUS
import rs.dusk.engine.model.entity.Direction
import rs.dusk.engine.model.entity.index.TrackingSet

/**
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since April 21, 2020
 */
class NPCTrackingSet(
    val tickMax: Int,
    override val maximum: Int,
    override val radius: Int = VIEW_RADIUS,
    override val add: LinkedHashSet<NPC> = LinkedHashSet(),
    override val remove: MutableSet<NPC> = mutableSetOf(),
    override val current: LinkedHashSet<NPC> = LinkedHashSet()
) : TrackingSet<NPC> {

    override var total: Int = 0

    override fun prep(self: NPC?) {
        remove.addAll(current)
        total = 0
    }

    override fun update() {
        remove.forEach {
            current.remove(it)
        }
        add.forEach {
            current.add(it)
        }
        remove.clear()
        add.clear()
        total = current.size
    }

    override fun add(self: NPC) {
        current.add(self)
    }

    override fun clear() {
        add.clear()
        remove.clear()
        current.clear()
        total = 0
    }

    override fun track(entity: NPC, self: NPC?) {
        val visible = !entity.teleporting && remove.remove(entity)
        if (visible) {
            total++
        } else if (add.size < tickMax) {
            add.add(entity)
            total++
        }
    }
}

val NPC.teleporting: Boolean
    get() = movement.delta.id != 0 && movement.walkStep == Direction.NONE && movement.runStep == Direction.NONE