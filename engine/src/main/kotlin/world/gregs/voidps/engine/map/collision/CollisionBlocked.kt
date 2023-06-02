package world.gregs.voidps.engine.map.collision

import org.rsmod.game.pathfinder.StepValidator
import org.rsmod.game.pathfinder.flag.CollisionFlag
import world.gregs.voidps.engine.entity.Direction
import world.gregs.voidps.engine.entity.character.Character
import world.gregs.voidps.engine.entity.character.npc.NPC
import world.gregs.voidps.engine.get
import world.gregs.voidps.engine.map.Tile

fun Character.blocked(direction: Direction) = blocked(tile, direction)

fun Character.blocked(tile: Tile, direction: Direction): Boolean {
    val flag = if (this is NPC) CollisionFlag.BLOCK_PLAYERS or CollisionFlag.BLOCK_NPCS else 0
    return !get<StepValidator>().canTravel(x = tile.x,
        z = tile.y,
        level = tile.plane,
        size = size.width,
        offsetX = direction.delta.x,
        offsetZ = direction.delta.y,
        extraFlag = flag,
        collision = collision
    )
}