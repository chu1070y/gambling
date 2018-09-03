package service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "WaitServlet" ,urlPatterns = "/waiting")
public class WaitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ip=request.getRemoteAddr();//해당 컴퓨터의 ip주소를 저장한다.

        GamblerSave.INSTANCE.first(ip);//enum에 해당 컴퓨터 ip를 저장하고 초기 칩을 넣어준다.

        WaitSave.INSTANCE.first(ip);
        System.out.println("OTHER: "+WaitSave.INSTANCE.getip(1)+WaitSave.INSTANCE.getip(2) );

        response.setContentType("text/html; charset=UTF-8");
        Writer out = response.getWriter();

        out.write("<h1>Waiting 상대방을 기다리는 중입니다. 기다려주세요.</h1>");
        out.write("<script>");
        out.write("setTimeout(function() {console.log('aaa'); self.location ='/waiting'; }, 3000);");
        out.write("</script>");

        response.setContentType("text/html; charset=UTF-8");

        if (WaitSave.INSTANCE.getip(2)!=null) {
            out.write("<form action='check.html'>");
            out.write("<input type='submit' value='입장하기' style='height:50px; width:100px;'>");
            out.write("</form>");
        }

    }//end doget
}//end class
