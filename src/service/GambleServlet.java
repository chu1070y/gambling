package service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


@WebServlet(name = "GambleServlet",urlPatterns = "/gambling")
public class GambleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ip=request.getRemoteAddr();//해당 컴퓨터의 ip주소를 저장한다.

        String ip1 = WaitSave.INSTANCE.getip(1);
        String ip2 = WaitSave.INSTANCE.getip(2);

        response.setContentType("text/html; charset=UTF-8");
        Writer out = response.getWriter();

            int num1 = GamblerSave.INSTANCE.getNum(ip1);
            int num2 = GamblerSave.INSTANCE.getNum(ip2);

        System.out.println("NUM1 : " + num1);
        System.out.println("NUM2 : " + num2);

            String result;

            if (num1 > num2) {
                result = "IP:" + ip1 + "분께서 이겼습니다.";
            } else if (num2 > num1) {
                result = "IP:" + ip2 + "분께서 이겼습니다.";
            } else {
                result = "동점입니다.";
            }

            if (result.contains(ip1)) {
                System.out.println("------------------------------이김" + ip1+":" +GamblerSave.INSTANCE.getGamblerMap(ip1)[0]);
                GamblerSave.INSTANCE.win(ip1,num1);
                GamblerSave.INSTANCE.lose(ip2,num2);
            } else {
                System.out.println("------------------------------이김" + ip2+":" +GamblerSave.INSTANCE.getGamblerMap(ip2)[0]);
                GamblerSave.INSTANCE.win(ip2,num2);
                GamblerSave.INSTANCE.lose(ip1,num1);
            }
            response.setContentType("text/html; charset=UTF-8");


            if (GamblerSave.INSTANCE.getChip(ip) <= 0) {
                response.setContentType("text/html; charset=UTF-8");
                out.write("<h1>파산하셨네요ㅠㅠ</h1>");
                out.write("<img src=img/lose-1.jpg>");
                out.write("<img src=img/lose-2.jpg><br>");
                out.write("<iframe width=\"300\" height=\"25\" src=\"https://www.youtube.com/embed/92c9xaQexsA?autoplay=1&controls=0&rel=0&showinfo=0\" frameborder=\"0\" allowfullscreen hidden></iframe>");
            } else if (GamblerSave.INSTANCE.getChip(ip) >= 40) {
                response.setContentType("text/html; charset=UTF-8");
                out.write("<h1>축하합니다, 우승입니다^^</h1>");
                out.write("<img src=img/win-1.jpg>");
                out.write("<img src=img/win-2.jpg>");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                out.write("<form action='check.html' method='get'>");
                out.write("<div align='middle'>");
                out.write("<img src=img/" + num1 + ".JPG>");
                out.write("<img src=img/" + num2 + ".JPG>");
                out.write("<h1> 결과:" + result + "</h1>");
                out.write("칩이 " + GamblerSave.INSTANCE.getChip(ip) + "개 남았습니다.<br>");
                out.write("<button>다음 게임으로</button>");
                out.write("</div>");
                out.write("</form>");
            }

    }//end doget
}
