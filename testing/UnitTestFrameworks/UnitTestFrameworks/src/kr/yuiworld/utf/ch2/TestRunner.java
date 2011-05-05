package kr.yuiworld.utf.ch2;

public class TestRunner {
    public static void main(String[] args) {
    	new TestRunner();
    }

    public TestRunner() {
		try {
		    UnitTest test = new BookTest();
		    test.runTest();
		    new LibraryTest().runTest();
		    System.out.println("SUCCESS");
		} catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("FAILURE");
		}

		System.out.println(UnitTest.getNumSuccess() + " tests completed successfully");
    }
}