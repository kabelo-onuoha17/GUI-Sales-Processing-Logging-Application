import com.mycompany.mattresssalesgui.ProductCalculations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCalculationsTest {
    // The sample price from the paper
    private final ProductCalculations calc = new ProductCalculations(3500);

    // 15% of 3500 = 525
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, calc.GetVat(), 0.001);
    }

    // 3500 + 525 + 175 = 4200
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, calc.GetTotal(), 0.001);
    }
}