/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.26
 * Generated at: 2012-10-15 13:10:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class resource_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:contains", org.apache.taglibs.standard.functions.Functions.class, "contains", new Class[] {java.lang.String.class, java.lang.String.class});
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/admin/../foot.jsp", Long.valueOf(1350283935831L));
    _jspx_dependants.put("/admin/../taglib.jsp", Long.valueOf(1345552994159L));
    _jspx_dependants.put("/admin/../top.jsp", Long.valueOf(1350305382989L));
    _jspx_dependants.put("/admin/left.jsp", Long.valueOf(1350298710763L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t\t<title>资源管理</title>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/scripts/lib-base.jsp\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("<div id=\"left\">\r\n");
      out.write("\t<div class=\"innertube\">\r\n");
      out.write("\t\t<h1 class=\"head\">后台管理</h1>\r\n");
      out.write("\t\t<ul class=\"navigation\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<div class=\"bottom_menu\">\r\n");
      out.write("\t\t\t<ul class=\"navigation\">\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<a href=\"../\" class=\"beright\">返回主菜单</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("\t\t<div id=\"page\">\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("<div id=\"head\">\r\n");
      out.write("\t<div class=\"innertube\">\r\n");
      out.write("\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f2.setParent(null);
      // /admin/../top.jsp(4,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.user ne NULL}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
      if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t<span><a href=\"");
          out.print(request.getContextPath());
          out.write("/\">首页</a></span>\r\n");
          out.write("\t\t\t<span style=\"float: right;\"> 欢迎您:<font color=\"green\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.user.userName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</font>， \r\n");
          out.write("\t\t\t\t<a href=\"");
          out.print(request.getContextPath());
          out.write("/j_security_logout.html\">退出系统</a> </span>\r\n");
          out.write("\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      out.write("\r\n");
      out.write("\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f3.setParent(null);
      // /admin/../top.jsp(9,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.user eq NULL}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
      if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t<span> &nbsp; </span>\r\n");
          out.write("\t\t\t<span style=\"float: right;\"> <a href=\"");
          out.print(request.getContextPath());
          out.write("/login.html\">登录</a></span>\r\n");
          out.write("\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      out.write("\r\n");
      out.write("\t\t<hr style=\"clear: both;\" />\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div id=\"main\">\r\n");
      out.write("\t\t\t\t<script type=\"text/javascript\" charset=\"utf-8\">\r\n");
      out.write("\t\t\t\t\tvar t = null;\r\n");
      out.write("\t\t\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t\t\tt = new SimpleTable(\"resourceQueryTable\", {\r\n");
      out.write("\t\t\t\t\t\t\tpageSize : 10,\r\n");
      out.write("\t\t\t\t\t\t\tparam : 'searchDiv',\r\n");
      out.write("\t\t\t\t\t\t\turl : '");
      out.print(request.getContextPath());
      out.write("/admin/resource_query.html',\r\n");
      out.write("\t\t\t\t\t\t\tsortables : [\"name\"],\r\n");
      out.write("\t\t\t\t\t\t\tpagingOptions : {\r\n");
      out.write("\t\t\t\t\t\t\t\tfirst : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tend : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tgo : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tfirstHtml : '<a href=\"#\">首页</a>',\r\n");
      out.write("\t\t\t\t\t\t\t\tlastHtml : '<a href=\"#\">上一页</a>',\r\n");
      out.write("\t\t\t\t\t\t\t\tnextHtml : '<a href=\"#\">下一页</a>',\r\n");
      out.write("\t\t\t\t\t\t\t\tendHtml : '<a href=\"#\">尾页</a>'\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t//checkMode : 'single',\r\n");
      out.write("\t\t\t\t\t\t\t// scrollX : true,\r\n");
      out.write("\t\t\t\t\t\t\tcolumns : {\r\n");
      out.write("\t\t\t\t\t\t\t\tcheckbox : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '3%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\tname : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '资源名称',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '15%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\ttype : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '资源类型',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '10%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\tpriority : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '优先级',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '8%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\turl : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '资源路径',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '32%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\tdescription : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '资源描述',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '25%'\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\tops : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '操作',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : '6%'\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\tformatters : {\r\n");
      out.write("\t\t\t\t\t\t\t\tops : function(v, obj) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar html = '';\r\n");
      out.write("\t\t\t\t\t\t\t\t\thtml += \"<a href='javascript:doEdit(\" + obj.id + \");'>修改</a>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\treturn html;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\tinfo : {\r\n");
      out.write("\t\t\t\t\t\t\t\t//pageSizeSelect:'', //不显示此控件\r\n");
      out.write("\t\t\t\t\t\t\t\tpageSizeSelect : '显示{0}条记录',\r\n");
      out.write("\t\t\t\t\t\t\t\tpagingInfo : '目前显示{0}-{1}条记录，共计：{2}'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t$('#resource_add_form_div').dialog({\r\n");
      out.write("\t\t\t\t\t\t\tdraggable : true,\r\n");
      out.write("\t\t\t\t\t\t\ttitle : '添加/修改资源',\r\n");
      out.write("\t\t\t\t\t\t\twidth : 620,\r\n");
      out.write("\t\t\t\t\t\t\tmodal : true,\r\n");
      out.write("\t\t\t\t\t\t\t// position:'center', 'left', 'right', 'top', 'bottom'.  or [350,100]\r\n");
      out.write("\t\t\t\t\t\t\tresizable : false,\r\n");
      out.write("\t\t\t\t\t\t\tautoOpen : false,\r\n");
      out.write("\t\t\t\t\t\t\t// show:'slide',\r\n");
      out.write("\t\t\t\t\t\t\theight : 220,\r\n");
      out.write("\t\t\t\t\t\t\tbuttons : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\"OK\" : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tajaxSubmitForm(\"resource_add_form\", editCallback);\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\"Cancel\" : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\tfunction editCallback() {\r\n");
      out.write("\t\t\t\t\t\tt.doSearch();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\tfunction doEdit(id) {\r\n");
      out.write("\t\t\t\t\t\tdoGetAjax(\"");
      out.print(request.getContextPath());
      out.write("/admin/resource/\" + id + \".html\", '', function(data, textStatus, XMLHttpRequest) {\r\n");
      out.write("\t\t\t\t\t\t\tfillForm(\"resource_add_form\", data);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#resource_add_form_div\").dialog(\"open\");\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t<div id=\"searchDiv\" style=\"width: 90%;\">\r\n");
      out.write("\t\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\">资源名称：</td><td width=\"30%\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"name\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\">资源路径：</td><td width=\"30%\">\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"url\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"text-align: right;\">\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"searchButton\" value=\"查询\" />\r\n");
      out.write("\t\t\t\t\t<script type='text/javascript'>\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchButton\").click(function() {\r\n");
      out.write("\t\t\t\t\t\t\tt.doSearch();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t\t\t\tfunction showAddForm() {\r\n");
      out.write("\t\t\t\t\t\t\tdocument.forms['resource_add_form'].reset();\r\n");
      out.write("\t\t\t\t\t\t\tvar dialog = $('#resource_add_form_div').dialog(\"open\");\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\tfunction removeResources() {\r\n");
      out.write("\t\t\t\t\t\t\tvar rids = t.getSelected();\r\n");
      out.write("\t\t\t\t\t\t\tif(rids.length >= 1) {\r\n");
      out.write("\t\t\t\t\t\t\t\tshowConfirm(\"确认要删除选中的资源吗？\", function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tdoPostAjax(\"");
      out.print(request.getContextPath());
      out.write("/admin/resource/delete.html\", \"ids=\" + rids, function(resp) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowMsg(resp);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tt.doSearch();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tshowMsg(\"<font color='red'>请至少选择一行记录!</font>\");\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:showAddForm();\">添加新资源</a>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp; <a href=\"javascript:removeResources();\">删除选中资源</a>\r\n");
      out.write("\t\t\t\t\t<br />\r\n");
      out.write("\t\t\t\t\t<br/>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"tableDiv\" style=\"width: 100%;\">\r\n");
      out.write("\t\t\t\t\t<table id=\"resourceQueryTable\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"resource_add_form_div\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t<form id=\"resource_add_form\" action=\"");
      out.print(request.getContextPath());
      out.write("/admin/resource_edit.html\"  method=\"post\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" style=\"display: none;\" name=\"id\" />\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>资源名称：</td><td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"name\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>资源类型：</td><td>\r\n");
      out.write("\t\t\t\t\t\t\t<select name=\"type\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"query\">查询</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"create\">创建</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"edit\">编辑</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"delete\">删除</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"get\">获取</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"static\">静态页面</option>\r\n");
      out.write("\t\t\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>资源路径：</td><td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"url\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>优先级：</td><td>\r\n");
      out.write("\t\t\t\t\t\t\t<select name=\"priority\" style=\"width: 90px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"1\">1</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"2\">2</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"3\">3</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"4\">4</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"5\">5</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"6\">6</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"7\">7</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"8\">8</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"9\">9</option>\r\n");
      out.write("\t\t\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>资源描述：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" size=\"54\" name=\"description\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("<div id=\"foot\" class=\"noprint\">\r\n");
      out.write("\t<div style=\"text-align: center;\">\r\n");
      out.write("\t\t© Copyright 2012 Hymer. All Rights Reserved.\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /admin/left.jsp(6,3) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menus}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /admin/left.jsp(6,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("menu");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /admin/left.jsp(7,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.level eq 2}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /admin/left.jsp(8,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:contains(menu.link, \"/admin/\")}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<li>\r\n");
        out.write("\t\t\t\t\t\t\t<a class='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(pageContext.request.requestURI eq menu.link) ? \"currentPage\" : \"\"}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("' href=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.link}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write('"');
        out.write('>');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</a>\r\n");
        out.write("\t\t\t\t\t\t</li>\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }
}
