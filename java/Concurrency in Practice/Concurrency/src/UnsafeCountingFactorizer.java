

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UnsafeCountingFactorizer
 */
public class UnsafeCountingFactorizer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private long count = 0;
	
	public long getCount() {
		return count;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnsafeCountingFactorizer() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
//    	super.service(request, response);
    	BigInteger i = extractFromRequest(request);
    	BigInteger[] factors = factor(i);
    	++count;
    	encodeIntoResponse(response, factors);
    }
    
    void encodeIntoResponse(HttpServletResponse res, BigInteger[] factors) throws IOException {
    	PrintWriter writer = res.getWriter();
    	for (int i = 0; i < factors.length; i++)
    		writer.println(factors[i].longValue());
    	writer.println("count: " + count);
    }

    BigInteger extractFromRequest(HttpServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
