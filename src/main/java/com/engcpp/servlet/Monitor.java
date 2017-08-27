/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author engcpp
 */
@WebServlet(name = "/start", urlPatterns = "/monitor/")
public class Monitor extends HttpServlet {
    private static int MEGA = 1024 * 1024;
    private static String FORMAT = " %.2f Mb";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            MemoryMXBean mx = ManagementFactory.getMemoryMXBean();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet monitor</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/style.css\"/>");
            out.println("<script>");
            out.println("setInterval(\"location.reload(true)\",2000);");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<div class='NheapM'><div class='quadro-titulo'>Monitor de Consumo de Memória</div></div>");
            out.println("<table>");
            out.print("<tr>");
                out.print("<td class='NheapM'><center>");
                    out.println("<h2><b>Memória Heap</b></h2>");
                    out.println("<b>[ TOTAL ]</b>");
                    out.println(memoryUsage(mx.getHeapMemoryUsage()));
                    out.println("<hr>");
                    out.println(memoriasPorTypo(MemoryType.HEAP));
                out.print("</td><td class='heapM'><center>");
                    out.println("<h2><b>Memória Não Heap</b></h2>");
                    out.println("<b>[ TOTAL ]</b>");
                    out.println(memoryUsage(mx.getNonHeapMemoryUsage()));
                    out.println("<hr>");
                    out.println(memoriasPorTypo(MemoryType.NON_HEAP));
                out.print("</td>");
            out.print("</tr>");
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 

    private StringBuffer memoriasPorTypo(MemoryType tipo){
        StringBuffer sb = new StringBuffer(0);

                List<MemoryPoolMXBean>list = ManagementFactory.getMemoryPoolMXBeans();
            for(MemoryPoolMXBean mpb : list){
                if(mpb.getType()==tipo){
                    sb.append("<br><b>["+mpb.getName()+"]</b>");
                    sb.append(memoryUsage(mpb.getUsage()));
                }
            }
         return sb;
    }

    private double parteConsumo(double max, double consumido){
            double valor =0;                   
                   valor = (100*consumido)/max;
            return valor;
    }

    private StringBuffer memoryUsage(MemoryUsage m){
        Locale br = new Locale("pt","br");
        StringBuffer sb = new StringBuffer(0);
                sb.append("<div class='graficos' align='left'>");
                     sb.append("<div class=\"barra\">");
                        sb.append("<div class=\"cbarra\" style=\"width:"+parteConsumo(m.getMax(), m.getCommitted())+"%;\"></div>");
                     sb.append("</div>");                   

                sb.append("<table border='0' class='dados'>");
                    sb.append("<tr>");
                    sb.append("<td>Minimo:"+String.format(br,FORMAT,(double)m.getInit()/MEGA)+"</td>");
                    sb.append("<td>Maximo:" +String.format(br,FORMAT,(double)m.getMax()/MEGA)+"</td>");
                    sb.append("</tr><tr>");
                    sb.append("<td>Atual:"  +String.format(br,FORMAT,(double)m.getCommitted()/MEGA)+"</td>");
                    sb.append("<td>Usado:"  +String.format(br,FORMAT,(double)m.getUsed()/MEGA)+"</td>");
                sb.append("</tr></table>");
                sb.append("</div>");
        return sb;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }   
}