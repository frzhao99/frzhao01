package com.bcai.web.control;

public class Pagination {

 // curpage--当前所在页
 private int curpage = 1;

 // intTotalNum--记录总数
 private int intTotalNum = 0;

 // intLineNum--每页显示的记录数
 private int pagenum = 0;

 // liststep - 6
 private int liststep = 6;
 
 private String gePage = "";

 private String PREVIOUS_PAGE = "&laquo;";

 private String NEXT_PAGE = "&raquo;";

 public String getHref(int number) {
	 if(this.getGePage() != ""){
		 return "Javascript:"+this.getGePage()+"(" + number + ");"; 
	 }
	 return "Javascript:goPage(" + number + ");";
 }

 public String goHref(int number) {
  return "<a href=\"" + getHref(number) + "\">" + number + "</a>";
 }

 public String showPages() {

  StringBuffer strBuf = new StringBuffer(512);
  // 总页数
  int pagecount = (intTotalNum / pagenum) + 1;
  if ((intTotalNum % pagenum) == 0)
   pagecount = intTotalNum / pagenum;
  // 初始化值
  if (curpage == 0) {
   curpage = 1;
  } else {
   if (curpage <= 0) {
    curpage = 1;
   }
   if (curpage > pagecount) {
    curpage = pagecount;
   }
  }

  strBuf.append("<div class=\"pagination\">");

  // 显示上一页
  if (curpage > 1) {
   // 加上链接 curpage-1
   strBuf.append("<a href=\"" + getHref(curpage - 1)
     + "\" class=\"prev_page\" rel=\"prev start\">"
     + PREVIOUS_PAGE + "</a>");
  } else {
//   strBuf.append("<span class=\"disabled prev_page\">" + PREVIOUS_PAGE
//     + "</span>");
  }

  // 分页
  // 按照liststep=6计算
  if (pagecount <= liststep + 2) {
   // liststep以内，全显示
   // 1,2,3,4,5,6,7,8
   for (int i = 1; i <= pagecount; i++) {
    if (i == curpage) {
     strBuf.append("<span class=\"current\">" + i + "</span>");
    } else {
     strBuf.append(goHref(i));
    }
   }
  } else {
   if (curpage < liststep) { // 左边 1...5
    // 1,2,3,4,5,6,...,pagecount-1,pagecount
    for (int i = 1; i <= liststep; i++) {
     if (i == curpage) {
      strBuf.append("<span class=\"current\">" + i
        + "</span>");
     } else {
      strBuf.append(goHref(i));
     }
    }
    strBuf.append("<span class=\"gap\">&hellip;</span>");
    strBuf.append(goHref(pagecount - 1));
    strBuf.append(goHref(pagecount));
   } else if (curpage > pagecount - liststep + 1) { // 右边
    // 1,2,...pagecount-5,pagecount-4,pagecount-3,pagecount-2,pagecount-1,pagecount
    strBuf.append(goHref(1));
    strBuf.append(goHref(2));
    strBuf.append("<span class=\"gap\">&hellip;</span>");
    for (int i = pagecount - liststep + 1; i <= pagecount; i++) {
     if (i == curpage) {
      strBuf.append("<span class=\"current\">" + i
        + "</span>");
     } else {
      strBuf.append(goHref(i));
     }
    }
   } else { // 中间
    // 1,2,...,curpage-1,curpage,curpage+1,...,pagecount-1,pagecount
    strBuf.append(goHref(1));
    strBuf.append(goHref(2));
    strBuf.append("<span class=\"gap\">&hellip;</span>");
    int offset = (liststep - 4) / 2;
    for (int i = curpage - offset; i <= curpage + offset; i++) {
     if (i == curpage) {
      strBuf.append("<span class=\"current\">" + i
        + "</span>");
     } else {
      strBuf.append(goHref(i));
     }
    }
    strBuf.append("<span class=\"gap\">&hellip;</span>");
    strBuf.append(goHref(pagecount - 1));
    strBuf.append(goHref(pagecount));
   }
  }

  // 显示下-页
  if (curpage != pagecount) {
   // 加上链接 curpage+1
   strBuf.append("<a href=\"" + getHref(curpage + 1)
     + "\" class=\"next_page\" rel=\"next\">" + NEXT_PAGE
     + "</a>");
  } else {
//   strBuf.append("<span class=\"disabled next_page\">" + NEXT_PAGE
//     + "</span>");
  }

  strBuf.append("</div>");

  return strBuf.toString();

 }

 
 public static void main(String[] args) {
  // TODO Auto-generated method stub

  int intTotalNum = 100;
  int curpage = 1;
  int pagenum = 15;

  Pagination page = new Pagination();
  page.setIntTotalNum(intTotalNum);
  page.setCurpage(curpage);
  page.setPagenum(pagenum);
  System.out.print(page.showPages());
 }

 public int getCurpage() {
  return curpage;
 }

 public void setCurpage(int curpage) {
  this.curpage = curpage;
 }

 public int getIntTotalNum() {
  return intTotalNum;
 }

 public void setIntTotalNum(int intTotalNum) {
  this.intTotalNum = intTotalNum;
 }

 public int getListstep() {
  return liststep;
 }

 public void setListstep(int liststep) {
  this.liststep = liststep;
 }

 public int getPagenum() {
  return pagenum;
 }

 public void setPagenum(int pagenum) {
  this.pagenum = pagenum;
 }

public String getGePage() {
	return gePage;
}

public void setGePage(String gePage) {
	this.gePage = gePage;
}
 
 

}

