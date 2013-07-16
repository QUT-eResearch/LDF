package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class map_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("\n");
      out.write("<script>\n");
      out.write("var map;\n");
      out.write("var markersArray = [];\n");
      out.write("\n");
      out.write("function initialize() {\n");
      out.write("  var mapOptions = {\n");
      out.write("    center: new google.maps.LatLng(-35.274398, 133.775136),\n");
      out.write("    zoom: 4,\n");
      out.write("    mapTypeId: google.maps.MapTypeId.ROADMAP,\n");
      out.write("    mapTypeControl: false,\n");
      out.write("    scrollwheel: false,\n");
      out.write("    rotateControl: false,\n");
      out.write("    panControl: false,\n");
      out.write("    streetViewControl: false,\n");
      out.write("    zoomControl: true,\n");
      out.write("    zoomControlOptions: {\n");
      out.write("      style: google.maps.ZoomControlStyle.SMALL,\n");
      out.write("      position: google.maps.ControlPosition.RIGHT_TOP\n");
      out.write("    },\n");
      out.write("    overviewMapControl:false\n");
      out.write("  };\n");
      out.write("  map = new google.maps.Map(document.getElementById(\"map_canvas\"),\n");
      out.write("      mapOptions);\n");
      out.write("\n");
      out.write("  setFeeds();\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write("function setFeeds(){\n");
      out.write("\t$.when(\n");
      out.write("\t\t\t$.post(CONTEXTPATH+\"/map/loadProjects\"\n");
      out.write("\t\t\t\t)\n");
      out.write("\t\t   ).done(function(data) {\n");
      out.write("\t\t\t   $.each(data, function(i, data) {\n");
      out.write("\t\t\t\t   markersArray.push(data);   \n");
      out.write("\t\t\t\t  \t});\n");
      out.write("\t\t\t   listen();\n");
      out.write("\t\t\t   }\n");
      out.write("\t);\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\t\n");
      out.write("function listen(){\n");
      out.write("\tvar infowindow = new google.maps.InfoWindow();\n");
      out.write("\tvar marker, i;\n");
      out.write("\n");
      out.write("\t$.each(markersArray, function(i, markersArray) {\n");
      out.write("\t\t//console.log(i, markersArray.name);\n");
      out.write("        marker = new google.maps.Marker({\n");
      out.write("        position: new google.maps.LatLng(markersArray.latitude, markersArray.longitude),\n");
      out.write("        map: map\n");
      out.write("      });\n");
      out.write("\n");
      out.write("      google.maps.event.addListener(marker, 'click', (function(marker, i) {\n");
      out.write("        return function() {\n");
      out.write("        \t//showFeed(markersArray.feedId);\n");
      out.write("        \tshowFeedInfo(markersArray, map, marker);\n");
      out.write("          //infowindow.setContent(markersArray.name);\n");
      out.write("          //infowindow.open(map, marker);\n");
      out.write("        }\n");
      out.write("      })(marker, i));\n");
      out.write("    }); \n");
      out.write("\n");
      out.write("}\t\n");
      out.write("\t\n");
      out.write("function showFeed(id){\n");
      out.write("\t$.when(\n");
      out.write("\t\t\t$.post(CONTEXTPATH+\"/map/loadProject?idProject=\"+id\n");
      out.write("\t\t\t\t)\n");
      out.write("\t\t   ).done(function(data) {\n");
      out.write("\t\t\t   $('.feedInfo').css('display','block');\n");
      out.write("\t\t\t   $.each(data, function(i, data) {\n");
      out.write("\t\t\t\t\t   $(\".feedDetails\").html(\"<li> Feed Name:\"+data.name + \"<br/><!--<a href='/DataFeeds/charts/csvChart' >csvChart</a>--></li>\");\n");
      out.write("\t\t\t\t  \t});\n");
      out.write("\n");
      out.write("\t\t\t   }\n");
      out.write("\t);\n");
      out.write("}\t\n");
      out.write("\n");
      out.write("function showFeedInfo(markerArray, map, marker){\n");
      out.write("\t///TO DO: Change Feed to ProjectFeeds to allow ProjectFeeds 0 to many feeds\n");
      out.write("\t$.when(\n");
      out.write("\t\t\t$.post(CONTEXTPATH+\"/map/loadProject?idProject=\"+markerArray.idProject /*To obtain list of feeds it has*/\n");
      out.write("\t\t\t\t)\n");
      out.write("\t\t   ).done(function(data) {\n");
      out.write("\t\t\t\tvar infowindow = new google.maps.InfoWindow();\n");
      out.write("\t\t\t   $('.feedInfo').css('display','block');\n");
      out.write("\t\t\t   var content = \"<div id='\"+markerArray.idProject+\"infoWindowContent'>\";\n");
      out.write("\t\t\t   content = content + \"<p class='center title'><strong>\" + markerArray.name + \"</strong></p><ul class='projectInfo'>\";\n");
      out.write("\t\t\t   $.each(data, function(i, data) {\n");
      out.write("\t\t\t\t   //TO DO: List all feed types in this FEED (Project/marker)\n");
      out.write("\t\t\t\t   content = content + \"<li> \";\n");
      out.write("\t\t\t\t   content = content + \"<table id='\"+markerArray.idProject+\"_\"+data.idFeed+\"_feedElements' class='listfeedElements'><tr>\";\n");
      out.write("\t\t\t\t   content = content + \"<td>\"+data.name+\" </td> \";\n");
      out.write("\t\t\t\t   //content = content +\"<a href='javascript:buildContentInfoWindow(\";\n");
      out.write("\t\t\t\t   \n");
      out.write("\t\t\t\t   //content = content + \"&quot;\";\n");
      out.write("\t\t\t\t   //content = content + data.idFeed +\",\"+markerArray.idProject+\",\"+data.type+\",\"+data.name;\n");
      out.write("\t\t\t\t   //content = content + \"&quot;)'> open feed \"+data.name+\" </a>\";\n");
      out.write("\t\t\t\t   \n");
      out.write("\t\t\t\t   content = content +\"<td><a class='mapButton' href='javascript:buildContentInfoWindow(\"+markerArray.idProject+\", &quot;\"+markerArray.name+\"&quot;, \"+data.idFeed+\",&quot;\"+data.type+\"&quot;,&quot;\"+data.name+\"&quot;)'\";\n");
      out.write("\t\t\t\t   content = content + \"> open </a></td></tr>\";\n");
      out.write("\t\t\t\t   content = content + \"</table>\";\n");
      out.write("\t\t\t\t  \t});\n");
      out.write("\t\t\t   content = content + \"</ul></div>\";\n");
      out.write("\t\t\t   infowindow.setContent(content);//$(#\"+id+\"-window-content).clone()[0]);\n");
      out.write("\t\t       infowindow.open(map, marker);\n");
      out.write("\t\t\t   }\n");
      out.write("\t);\n");
      out.write("}\t\n");
      out.write("function buildContentInfoWindow(idProject, projectName, idFeed, type, name){\n");
      out.write("\n");
      out.write("\t//console.log(idFeed, idProject, type, name);\n");
      out.write("\t$.when(\n");
      out.write("\t\t\t$.post(CONTEXTPATH+\"/map/loadFeeds/\"+idProject+\"/\"+idFeed) /*To obtain list of elements (charts,gauges,meter, etc) it has*/\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t   ).done(function(data) {\n");
      out.write("\t\t\t   var new_div = document.createElement('div');\n");
      out.write("\t\t\t\tdocument.body.appendChild(new_div);\n");
      out.write("\t\t\t\tclassName = idProject+\"_\"+ idFeed+ \"_feedWindow\";\n");
      out.write("\t\t\t\tnew_div.className = className;\n");
      out.write("\t\t\t\tnew_div.setAttribute('id', className);\n");
      out.write("\t\t\t\t var content = \"<p>\"+projectName + \" > \" + name+\"</p><ul class='feedElements'>\";\n");
      out.write("\t\t\t   \n");
      out.write("\t\t\t\t$.each(data, function(i, data) {\n");
      out.write("\t\t\t\t\tif(data.type.localeCompare('image')==0){\n");
      out.write("\t\t\t\t\t \tcontent = content + \"<li> <a href='javascript:loadImage(\"+idProject+\",\"+data.idFeed+\",\"+data.idChart+\",&quot;\"+data.shortName+\"&quot;)'>  \"+data.shortName+\"  </a></li>\";\t\t\t\t\t\n");
      out.write("\t\t\t\t\t }\n");
      out.write("\t\t\t\t\t else{\n");
      out.write("\t\t\t\t\t\t content = content + \"<li> <a href='javascript:loadChart(\"+idProject+\",\"+data.idFeed+\",\"+data.idChart+\",&quot;\"+data.shortName+\"&quot;)'>  \"+data.shortName+\"  </a></li>\";\t\t\t\t\t\n");
      out.write("\t\t\t\t\t }\n");
      out.write("\t\t\t\t\t//$(\"#\"+className).append(\"<li> <a href='javascript:buildDialogForFeedElements(\"+idProject+\",\"+data.idFeed+\",\"+data.idChart+\",&quot;\"+data.name+\"&quot;)'>  \"+data.shortName+\"  </a></li>\");\n");
      out.write("\t\t\t\t\t//$(\"#\"+idProject+\"_\"+idFeed+\"_feedElements\").append(\"<li> <a href='javascript:buildDialogForFeedElements(\"+idProject+\",\"+data.idFeed+\",\"+data.idChart+\",&quot;\"+data.name+\"&quot;)'>  \"+data.name+\"  </a></li>\");\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\tcontent = content + \"</ul>\";\n");
      out.write("\t\t\t\t//new_div.setContent(content);\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$(\"#\"+className).html(content);\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$( \"#\"+className ).dialog({\n");
      out.write("\t\t\t\t\ttitle: name,\n");
      out.write("\t\t \t        close: function(event, ui)\n");
      out.write("\t\t\t \t        {\n");
      out.write("\t\t\t \t        \t$(this).dialog('close').remove();//removed destroy instead of close so it can get called again byt the scheduller\n");
      out.write("\t\t\t \t        },\n");
      out.write("\t\t\t \t    width:'auto'\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t  \n");
      out.write("\t\t});\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("function buildDialogForFeedElements(idProject, idFeed, idChart, name){\t\n");
      out.write("\tvar new_div = document.createElement('div');\n");
      out.write("\tdocument.body.appendChild(new_div);\n");
      out.write("\tclassName = idChart+\"infoWindowContentInner\";\n");
      out.write("\tnew_div.className = className;\n");
      out.write("\t//new_div.innerHTML = '<u>'+feedId+'</u>';\n");
      out.write("\t//TO DO: get the necesary headers from the chart like chartTitle, columnIds and names to generate content\n");
      out.write("\tvar content = \"<h3>\"+name+\"</h3>\";\n");
      out.write("\tcontent = content + \"<div style='width:100%'>\";\n");
      out.write("\tcontent = content + \"<div id='chartsPanel'>\";\n");
      out.write("\tcontent = content + \"<div class='center'>\";\n");
      out.write("\tcontent = content + \"<div class='chartBox'>\";\n");
      out.write("\tcontent = content + \"<h2 class='chartBoxName'></h2>\";\n");
      out.write("\tcontent = content + \"</div>\";\n");
      out.write("\t/*$( \".\"+className ).dialog({\n");
      out.write(" \t        title: name,\n");
      out.write(" \t        close: function(event, ui)\n");
      out.write(" \t        {\n");
      out.write(" \t        \t$(this).dialog('destroy').remove();\n");
      out.write(" \t        },\n");
      out.write("          minHeight: 400,\n");
      out.write("          minWidth: 500,\n");
      out.write("          maxWidth: 700,\n");
      out.write("          maxHeight: 600\n");
      out.write("\t            \t\t\n");
      out.write("\t});*/\n");
      out.write("\tcontent = content + \"<div class='chart'>\";\n");
      out.write("\tcontent = content + '<div id='+idChart+'_Chart style=width:100%;height:300px;></div>';\n");
      out.write("\tcontent = content + \"</div>\";\n");
      out.write("\tcontent = content + \"</div>\";\n");
      out.write("\t\n");
      out.write("\tnew_div.innerHTML = content;\n");
      out.write("\t//console.log(content);\n");
      out.write("\t//TODO: IF type of feed load appropiate function\n");
      out.write("\t\n");
      out.write("\tloadImage(idProject, idFeed, idChart, name);\n");
      out.write("\t//loadChart(idProject, idFeed, idChart);\n");
      out.write("\tnew_div.style.cssText = 'display:none;';\n");
      out.write("}\n");
      out.write("\n");
      out.write("function loadScript() {\n");
      out.write("\tvar script = document.createElement(\"script\");\n");
      out.write("\tscript.type = \"text/javascript\";\n");
      out.write("\tscript.src = \"http://maps.googleapis.com/maps/api/js?key=AIzaSyB5z4Crv2MCH1n1e0Uvuzy2yVnvXyetURg&sensor=false&callback=initialize\";\n");
      out.write("\tdocument.body.appendChild(script);\n");
      out.write("\n");
      out.write("}\n");
      out.write("function reCenter(){\n");
      out.write("\tvar center = map.getCenter(); \n");
      out.write("    google.maps.event.trigger(map, 'resize'); \n");
      out.write("    map.setCenter(center); \n");
      out.write("}\n");
      out.write("\n");
      out.write("window.onload = loadScript;\n");
      out.write("window.onresize = reCenter;\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("<div class=\"map\">\n");
      out.write("<div id=\"map_canvas\" style=\"width:100%; height:100%\"></div>\n");
      out.write("<div class=\"feedInfo\">\n");
      out.write("<div>\n");
      out.write("<ul  class=\"feedDetails\"></ul>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
