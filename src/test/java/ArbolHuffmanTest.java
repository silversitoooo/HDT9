import org.example.ArbolHuffman;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class ArbolHuffmanTest {

    @Test
    public void testGenerarCodigos() {
        Map<Character, Integer> frecuencias = Map.of('a', 5, 'b', 2, 'c', 1);
        ArbolHuffman arbol = new ArbolHuffman(frecuencias);

        Map<Character, String> codigos = arbol.getCodigos();
        assertNotNull(codigos);
        assertEquals(3, codigos.size());
        assertTrue(codigos.containsKey('a'));
        assertTrue(codigos.containsKey('b'));
        assertTrue(codigos.containsKey('c'));
    }

    @Test
    public void testDecodificar() {
        Map<Character, Integer> frecuencias = Map.of('a', 5, 'b', 2, 'c', 1);
        ArbolHuffman arbol = new ArbolHuffman(frecuencias);

        String binario = "010011"; // Ejemplo de texto codificado
        String textoDecodificado = arbol.decodificar(binario);

        assertNotNull(textoDecodificado);
    }
}