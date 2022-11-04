package com.ISOUR.Servlet;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ISOUR.Common.Common;
import com.ISOUR.DAO.*;
import com.ISOUR.VO.*;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Common.corsResSet(response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨짐 방지를 위해서 설정
		request.setCharacterEncoding("utf-8");
		// CORS 접근 허용
		Common.corsResSet(response);
		// 요청 메시지 받기
		StringBuffer sb = Common.reqStringBuff(request);
		// 요청 받은 메시지 JSON 파싱
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		
		// TeamAPI.js 에 작성해둔 cmd : "messageStorage" 를 가져온다.
		String reqCmd = (String)jsonObj.get("cmd");
		String reqId = (String)jsonObj.get("id");
		System.out.println("전달 받은 cmd : " + reqCmd);
		System.out.println("전달 받은 ID : " + reqId);
		
		PrintWriter out = response.getWriter();
		
		// TeamAPI.js 에 작성해둔 cmd : "messageStorage" 가 아니라면 실행될 if문
		if(!reqCmd.equals("ShowMessage")) {
			JSONObject resJson = new JSONObject();
			resJson.put("result", "TeamAPI.js에 cmd 확인 필요");
			out.print(resJson);
			return;
		}
		
		MessageDAO dao = new MessageDAO();
		List<MessageVO> list = dao.messageList(reqId);
		
		JSONArray messageArray = new JSONArray();
		
		for(MessageVO e : list) {
			JSONObject messageList = new JSONObject();
			messageList.put("name", e.getName());
			messageList.put("content", e.getContent());
			messageList.put("datetime", e.getDatetime());
			
			messageArray.add(messageList);
		}
		System.out.println(messageArray);
		out.print(messageArray);
	}
}
