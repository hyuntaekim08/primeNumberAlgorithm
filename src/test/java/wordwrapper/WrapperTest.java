package wordwrapper;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WrapperTest {
    @Test
    public void shouldWrap() {
        assertWrap(null, 1, "");
        assertWrap("", 1, "");
        assertWrap("x", 1, "x");
        assertWrap("xx", 1, "x\nx");
        assertWrap("xxx", 1, "x\nx\nx");
        assertWrap("x x", 1, "x\nx");
        assertWrap("x xx", 3, "x\nxx");
    }

    private void assertWrap(String s, int width, String expected) {
        assertThat(wrap(s, width), is(expected));
    }

    private String wrap(String s, int width) {
        if(s == null) {
            return "";
        }
        if(s.length() <= width)
            return s;
        else {
            int breakPoint = s.lastIndexOf(" ", width);
            if(breakPoint == -1)
                breakPoint = width;
            return s.substring(0, breakPoint) + "\n" + wrap(s.substring(breakPoint).trim(), width);
        }
    }
}
