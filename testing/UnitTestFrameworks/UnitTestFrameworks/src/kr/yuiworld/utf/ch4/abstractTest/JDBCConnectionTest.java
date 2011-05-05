package kr.yuiworld.utf.ch4.abstractTest;

public class JDBCConnectionTest extends AbstractDBConnectionTestCase {

	@Override
	public DBConnection getConnection() {
		return new JDBCConnection("jdbc:odbc:testdb");
	}
	
	public void testConnectString() {
		JDBCConnection conn = (JDBCConnection)getConnection();
		String connStr = conn.getConnectionString();
		assertTrue(connStr.equals("jdbc:odbc:testdb"));
	}
}
