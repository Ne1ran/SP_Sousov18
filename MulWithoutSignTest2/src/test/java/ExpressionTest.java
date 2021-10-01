import junit.framework.TestCase;
import org.junit.Test;

public class ExpressionTest extends TestCase {

    @Test
    public void testMultiply1() {
        int testx1 = 10;
        int testx2 = 21;
        Expression expressiontest = new Expression(testx1, testx2);
        assertEquals(210, expressiontest.Multiply());
    }

    @Test
    public void testMultiply2() {
        int testx1 = 0;
        int testx2 = 21;
        Expression expressiontest = new Expression(testx1, testx2);
        assertEquals(0, expressiontest.Multiply());
    }
    @Test
    public void testMultiply3() {
        int testx1 = 2;
        int testx2 = -21;
        Expression expressiontest = new Expression(testx1, testx2);
        assertEquals(-42, expressiontest.Multiply());
    }
    @Test
    public void testMultiply4() {
        int testx1 = -5;
        int testx2 = -5;
        Expression expressiontest = new Expression(testx1, testx2);
        assertEquals(25, expressiontest.Multiply());
    }
    @Test
    public void testMultiply5() {
        int testx1 = 0;
        int testx2 = 21;
        Expression expressiontest = new Expression(testx1, testx2);
        assertEquals(1, expressiontest.Multiply());
    }
}