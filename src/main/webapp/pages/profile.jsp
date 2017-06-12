<%@ include file="header.jsp" %>
  <script type="text/javascript">

   $(document).ready(function () {
$("td", this).on("click", function () {
    var tds = $(this).parents("tr").find("td");
    $.each(tds, function (i, v) {
        $($(".data-form input")[i]).val($(v).text());
    });
});
});

   </script>
 <table  class="data-table" border="1">
        <tr>
        <td>Student ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Year Level</td>
        </tr>

            <tr>
                <td>hi</td>
                <td>hi</td>
                <td>hi</td>
                <td>hi</td>
                <td><a href='javascript:void(0);' >Edit</a></td>
            </tr>
            </table>
			<form name="frm" class="data-form" action="./StudentServlet" method="POST" onSubmit="return validateForm()">
            <tr>
                <td><strong>Student ID --></strong><input type="text" class='input1'  name="studentId" value="${student.studentId}" /> </td>
                <td><strong>First Name --></strong><input type="text" class='input2' name="firstname" value="${student.firstname}" /> </td>
                <td><strong>Last Name --></strong> <input type="text" class='input3' name="lastname" value="${student.lastname}" /> </td>
                <td><strong>Year Level --></strong><input type="text" class='input4' name="yearLevel" value="${student.yearLevel}" /> </td>
            </tr>   
      </form>
	  
  
  
  
  
  
  
  
  
  
  
  
  
  
   </div>
	<div class="col-sm-4 sidenav">
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>


