import org.example.CompresorHuffman;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;

public class CompresorHuffmanTest {

    @Test
    public void testComprimirYDescomprimir() throws Exception {
        String texto = "aaabbc";
        File archivoEntrada = new File("test.txt");
        Files.writeString(archivoEntrada.toPath(), texto);

        CompresorHuffman compresor = new CompresorHuffman();
        compresor.comprimir("test.txt", "test");

        File archivoHuff = new File("test.huff");
        File archivoArbol = new File("test.hufftree");

        assertTrue(archivoHuff.exists());
        assertTrue(archivoArbol.exists());

        compresor.descomprimir("test.huff", "test.hufftree", "test_descomprimido.txt");

        File archivoDescomprimido = new File("test_descomprimido.txt");
        assertTrue(archivoDescomprimido.exists());
        assertEquals(texto, Files.readString(archivoDescomprimido.toPath()));

        // Cleanup
        archivoEntrada.delete();
        archivoHuff.delete();
        archivoArbol.delete();
        archivoDescomprimido.delete();
    }
}