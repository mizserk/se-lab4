package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes_DecreasesCountByAmount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(3);

        // Assert
        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_ReturnsFalseAndKeepsCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeTorpedoes_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }

    @Test
    void isEmpty_NewStoreWithTorpedoes_ReturnsFalse() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act + Assert
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_StoreWithZeroTorpedoes_ReturnsTrue() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0, 0.0);

        // Act + Assert
        assertTrue(store.isEmpty());
    }

    @Test
    void isEmpty_AfterFiringLastTorpedo_ReturnsTrue() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertTrue(store.isEmpty());
    }
}