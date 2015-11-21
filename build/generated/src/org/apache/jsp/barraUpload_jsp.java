package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class barraUpload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>www.rafaelwendel.com - Upload de arquivos com barra de progresso</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("\n");
      out.write(".barra-area {\n");
      out.write("    position:relative;\n");
      out.write("    display:block;\n");
      out.write("    width:300px\n");
      out.write("}\n");
      out.write(".barra {\n");
      out.write("    position:relative;\n");
      out.write("    display:block;\n");
      out.write("    width:100%;\n");
      out.write("    background-color:#eee;\n");
      out.write("    padding:3px;\n");
      out.write("    -webkit-border-radius:3px;\n");
      out.write("    -moz-border-radius:3px;\n");
      out.write("    -o-border-radius:3px;\n");
      out.write("    border-radius:3px;\n");
      out.write("    -webkit-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);\n");
      out.write("    -moz-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);\n");
      out.write("    -o-box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2);\n");
      out.write("    box-shadow:inset 0 1px 3px rgba(0, 0, 0, .2)\n");
      out.write("}\n");
      out.write(".carga {\n");
      out.write("    height:20px;\n");
      out.write("    display:block;\n");
      out.write("    background-color:cornflowerblue;\n");
      out.write("    width:0%;\n");
      out.write("    border-radius:3px;\n");
      out.write("    -webkit-transition:width .8s ease;\n");
      out.write("    -moz-transition:width .8s ease;\n");
      out.write("    -o-transition:width .8s ease;\n");
      out.write("    transition:width .8s ease\n");
      out.write("}\n");
      out.write("        </style>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("var width = 0;\n");
      out.write("var tempo = 20;\n");
      out.write("var carga = document.querySelector('.carga');\n");
      out.write("var barra = setInterval(function(){\n");
      out.write("    width = width + 1;\n");
      out.write("    carga.style.width = width + '%';\n");
      out.write("    if (width === 100){ \n");
      out.write("        clearInterval(barra);\n");
      out.write("        width = 0;\n");
      out.write("    }\n");
      out.write("},tempo);\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("<div class=\"barra-area\">\n");
      out.write("    <div class=\"barra\">\n");
      out.write("            <span class=\"carga\"></span>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
