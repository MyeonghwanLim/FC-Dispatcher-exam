package ctrl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardSet;
import board.BoardVO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		ArrayList<BoardSet> datas;
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		String searchContent = request.getParameter("searchContent");
		System.out.println("받은 cnt"+request.getParameter("cnt"));
		String paramCnt=request.getParameter("cnt");
		
		if(paramCnt==null || paramCnt.equals("")){
			vo.setCnt(2);
		}
		else {
			vo.setCnt(Integer.parseInt(paramCnt));
		}
		
//		vo.setSearchCondition(request.getParameter("searchCondition"));
		vo.setSearchContent(searchContent);
			//만약 searchContent(mname)의 값이 null이라면...
		if(searchContent==null || searchContent.equals("")) {
			request.setAttribute("datasSize", dao.selectAll_size(vo).size());
			// 전체 게시글 목록의 크기를 비교
		}
		else {// searchConten(mname)의 값이 있다면
			vo.setMname(searchContent);
			request.setAttribute("datasSize", dao.selectAll_B_M_size(vo).size());
			// 해당 멤버의 게시글 목록의 비교
		}
		
		datas=dao.selectAll(vo);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", vo.getCnt());
		request.setAttribute("searchContent", request.getParameter("searchContent"));
		
		ActionForward forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
