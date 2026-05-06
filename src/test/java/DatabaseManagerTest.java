import com.github.morinator05.ohsheetmusic.database.Database;
import com.github.morinator05.ohsheetmusic.database.SQLiteDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    Database db = new SQLiteDatabase();

    @BeforeEach
    void setup() {
        db.setFile(new File("oh-sheet-music.db"));
        db.init();
        db.clear();
    }

    @Test
    void testInitialisation() {
        File dbFile = new File("oh-sheet-music.db");
        assertTrue(dbFile.exists(), "Die Datenbank-Datei sollte nach initDatabase() existieren.");
    }

    @Test
    void testInsert() {
        db.addPiece("Titel1", "C!", "01", "A");
        db.addPiece("Titel2", "C!","01", "B");
        db.addPiece("Titel3","C!", "03", "A");

        assertEquals(3, db.getAllPieces().size());
    }

}
