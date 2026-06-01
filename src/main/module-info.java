module se1_play {

    /*
     * Make package {@link application} accessible to other modules at compile 
     * and runtime (use <i>open</i> for compile-time access only).
     */
    exports application;

    /* Open packages to JUnit test runner and the javadoc compiler. */
    opens application;
    opens numbers;

    /*
     * External modules required by this module.
     */
    requires org.junit.jupiter.api;
    requires transitive runtimeSE;
}
