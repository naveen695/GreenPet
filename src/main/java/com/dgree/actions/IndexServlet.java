package com.dgree.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dgree.model.PetDetails;
import com.dgree.userDAO.MapDao;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 MongoDatabase mongoDatabase = (MongoDatabase)request.getServletContext().getAttribute("MongoDatabase");

		MapDao mapDao=new MapDao();
		List<PetDetails> petDetails = mapDao.getPetDetails(mongoDatabase);
		String json = getJson(petDetails);
		
		request.setAttribute("petDetails", json);
		
		request.getRequestDispatcher("index").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public String getJson(List<PetDetails> petDetails){

		 JSONObject responseDetailsJson = new JSONObject();
		    JSONArray jsonArray = new JSONArray();

		    for(PetDetails p : petDetails) {
		        JSONObject formDetailsJson = new JSONObject();
		        formDetailsJson.put("id",p.getId());
		        formDetailsJson.put("latitude",p.getLatitude());
		        formDetailsJson.put("longittude",p.getLongittude());
		        formDetailsJson.put("petDesc",p.getPetDesc());
		        formDetailsJson.put("address1",p.getAddress1());
		        formDetailsJson.put("address2",p.getAddress2());
		        formDetailsJson.put("city",p.getCity());
		        formDetailsJson.put("county",p.getCounty());
		        formDetailsJson.put("counrty",p.getCountry());
		        formDetailsJson.put("zip",p.getZip());
		        formDetailsJson.put("petName",p.getPetname());
		        
		        jsonArray.add(formDetailsJson);
		    }
		    responseDetailsJson.put("perdetails", jsonArray);//Here you can see the data in json format
			return responseDetailsJson.toJSONString(); 
	}

}
