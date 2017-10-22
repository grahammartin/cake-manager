package com.waracle.cakemgr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

@WebServlet("/cakeDownload")
public class CakeDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CakeEntity> list = session.createCriteria(CakeEntity.class).list();
		session.close();
		resp.setContentType("application/json");
		resp.setHeader("Content-Disposition", "attachment;filename=cakes.json");
		resp.getWriter().println("[");

		for (CakeEntity entity : list) {
			resp.getWriter().println("\t{");

			resp.getWriter().println("\t\t\"title\" : " + entity.getTitle() + ", ");
			resp.getWriter().println("\t\t\"desc\" : " + entity.getDescription() + ",");
			resp.getWriter().println("\t\t\"image\" : " + entity.getImage());

			resp.getWriter().println("\t}");
		}

		resp.getWriter().println("]");
	}

}
