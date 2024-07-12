package agenda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.controller.util.MyHttpServletRequestWrapper;
import agenda.model.DAO;
import agenda.model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// dao.testarConexao();

		// String action = request.getServletPath();
		// System.out.println(action);
		MyHttpServletRequestWrapper hsrw = new MyHttpServletRequestWrapper(request);
		String action = ((HttpServletRequest) hsrw.getRequest()).getServletPath();
		System.out.println("Resource=" + action);
		System.out.println(hsrw.toString());

		switch (action) {
		case "/main":
			contatos(request, response);
			break;
		case "/insert":
			novoContato(request, response);
			break;
		case "/select":
			obterContato(request, response);
			break;
		default:
			response.sendRedirect("index.html");
		}
	}

	protected void obterContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		printResourceMethod();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String idcon = request.getParameter("idcon");
		response.getWriter().append(idcon);
		System.out.println(idcon);
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		printResourceMethod();

		ArrayList<JavaBeans> contatos = dao.obterContatos();

		System.out.println("Listagem de contatos");
		for (JavaBeans contato : contatos) {
			System.out.println(contato);
		}
		System.out.println();

		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response); // forwards contatos to agenda.jsp
		// response.sendRedirect("agenda.jsp");
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		printResourceMethod();
		// response.sendRedirect("agenda.jsp");

		// String name = new Throwable().getStackTrace()[0].getMethodName();
		// System.out.println(getMethodName() + "@" + getClass().getSimpleName());

		String requestData[] = { "nome", "fone", "email" };

		contato.setNome(request.getParameter(requestData[0]));
		contato.setFone(request.getParameter(requestData[1]));
		contato.setEmail(request.getParameter(requestData[2]));

		dao.inserirContato(contato);
		System.out.println(contato + "\n");

		response.sendRedirect("main");
	}

	private void printResourceMethod() {
		String methodName = new Throwable().getStackTrace()[1].getMethodName();
		String className = getClass().getSimpleName();
		System.out.println(methodName + "@" + className);
	}
}
