import com.github.morinator05.ohsheetmusic.database.DatabaseManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseManagerTest {

    @Test
    void testInitialisation() {
        File dbFile = new File("oh-sheet-music.db");

        DatabaseManager.initDatabase();
        assertTrue(dbFile.exists(), "Die Datenbank-Datei sollte nach initDatabase() existieren.");
    }

}
