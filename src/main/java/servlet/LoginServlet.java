package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;


import dao.UsuarioDAO;
import entity.Usuarios;
import entityUtil.HibernateUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		urlPatterns = { "/Login" }
		
		)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	private static Logger logger = LogManager.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
    	//Transaction tx = null;
		
		List<Usuarios> listaUsuarios = UsuarioDAO.getAllUsuarios(session);
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		//UsuarioDAO u = n UsuarioDAO();
		
		try {
		
			//tx = session.beginTransaction();
			
			
			for (Usuarios usuarios : listaUsuarios) {
				
				if(usuarios.getEmail().equals(usuario) && usuarios.getClave().equals(password)) {
					// si el login es correcto te muestra un mensaje de bienvenida
					logger.info("Usuario correcto y se redirige a la página de Bienvenido");
					response.sendRedirect("Bienvenido.html");
				}else {
					//si la redirección no es correcta, te lleva a la página de login
					logger.info("Usuario incorrecto se redirige a la página de inicio de Login");
					response.sendRedirect("Login.html");
				}

			}
		 
		//}catch(Exception e) {
  		//logger.error ("Error en el bucle", e);
		  //if (tx != null) {
		   // tx.rollback();
		  //}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}//
		session = HibernateUtil.getSessionFactory().openSession();
  	
  		session.close(); 
	
	}

}
