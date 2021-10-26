package world.gregs.voidps.engine.client

import io.mockk.spyk
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import world.gregs.voidps.engine.event.eventModule
import world.gregs.voidps.engine.script.KoinMock
import world.gregs.voidps.network.NetworkQueue

internal class ConnectionQueueTest : KoinMock() {

    private lateinit var queue: NetworkQueue

    override val modules = listOf(
        eventModule,
        clientConnectionModule
    )

    @BeforeEach
    fun setup() {
        queue = spyk(ConnectionQueue(25))
    }

    @Test
    fun `Await login`() = runBlockingTest {
        launch {
            queue.await()
        }
        queue.run()
    }
}