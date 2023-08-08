import Type.Type;
import org.junit.Test;
import utils.FloatTools;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FloatToolsTest {
    @Test
    public void floatToolsTest1() {
        float a = (float) 2.0e10;
        int high = FloatTools.getHigh20(a);
        int low = FloatTools.getLow12(a);
        assertEquals(330064, high);
        assertEquals(761, low);
    }

    @Test
    public void floatToolsTest2() {
        float a = (float) -33000.0;
        int high = FloatTools.getHigh20(a);
        int low = FloatTools.getLow12(a);
        assertEquals(815118, high);
        assertEquals(2048, low);
    }
}
