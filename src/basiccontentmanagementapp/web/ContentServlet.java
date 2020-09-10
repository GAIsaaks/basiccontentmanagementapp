package basiccontentmanagementapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basiccontentmanagementapp.dao.ContentDAO;
import basiccontentmanagementapp.model.Content;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContentDAO contentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        this.contentDAO = new ContentDAO();        		
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showContentForm(request, response);
			break;
		case "/insert":
			try {
				insertContent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/view":
			try {
				viewContent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteContent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showContentEditForm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateContent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				listContent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	
	/*
	 		ROUTING
	 */
	
	private void showContentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispacter = request.getRequestDispatcher("content-form.jsp");
		dispacter.forward(request, response);
	}
	
	private void insertContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String created = request.getParameter("created");
		Content content = new Content(title, contents, created);
		contentDAO.insertContent(content);
		response.sendRedirect("list");		
	}

	private void deleteContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		contentDAO.deleteContent(id);
		response.sendRedirect("list");
	}
	
	private void showContentEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Content content = contentDAO.selectContent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("content-form.jsp");
		request.setAttribute("content", content);
		dispatcher.forward(request, response);
	}
	
	private void viewContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Content content = contentDAO.selectContent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-content.jsp");
		request.setAttribute("content", content);
		dispatcher.forward(request, response);
	}
	
	private void updateContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String created = request.getParameter("created");
		
		Content content = new Content(title, contents, created);
		contentDAO.updateContent(content);
		
		response.sendRedirect("list");
	}
	
	private void listContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Content> listContent = contentDAO.selectAllUsers();
		request.setAttribute("listContent", listContent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-content.jsp");
		dispatcher.forward(request, response);
	}


}
