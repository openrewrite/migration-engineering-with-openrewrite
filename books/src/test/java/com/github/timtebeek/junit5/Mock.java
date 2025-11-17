package org.openrewrite.junit5;

public class Mock {
    public static Mock verify(Mock mock) {
        return new Mock();
    }

    public void method() {
        // Mock method
    }
}
