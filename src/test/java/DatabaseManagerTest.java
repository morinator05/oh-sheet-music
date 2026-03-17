import com.github.morinator05.ohsheetmusic.database.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    @BeforeEach
    void setup() {
        DatabaseManager.clearDatabase();
    }

    @Test
    void testInitialisation() {
        File dbFile = new File("oh-sheet-music.db");

        DatabaseManager.initDatabase();
        assertTrue(dbFile.exists(), "Die Datenbank-Datei sollte nach initDatabase() existieren.");
    }

    @Test
    void testInsert() {
        DatabaseManager.addPiece("Titel1", "C!", "01", "A");
        DatabaseManager.addPiece("Titel2", "C!","01", "B");
        DatabaseManager.addPiece("Titel3","C!", "03", "A");

        assertEquals(3, DatabaseManager.getAllPieces().size());
    }

}
