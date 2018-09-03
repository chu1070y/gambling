package service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "WaitServlet2",urlPatterns = "/numselect")
public class WaitServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ip=request.getRemoteAddr();//해당 컴퓨터의 ip주소를 저장한다.
        GamblerSave.INSTANCE.putNum(ip);
        String ip1 = WaitSave.INSTANCE.getip(1);
        String ip2 = WaitSave.INSTANCE.getip(2);

        response.setContentType("text/html; charset=UTF-8");
        Writer out = response.getWriter();

        out.write("<h1>Waiting 상대방이 숫자를 고르는 중입니다. 기다려주세요.</h1>");
        out.write("<script>");
        out.write("setTimeout(function() {console.log('aaa'); self.location ='/numselect'; }, 3000);");
        out.write("</script>");

        if (GamblerSave.INSTANCE.getGamblerMap(ip1)[2] != -1 && GamblerSave.INSTANCE.getGamblerMap(ip1)[2] != -1){

            out.write("<form action='/gambling'>");
            out.write("<input type='submit' value='결과보기' style='height:50px; width:100px;'>");
            out.write("</form>");
        }//end if

        }//end doget
}
