<!DOCTYPE html>
<html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="../common/taglib.jsp" %>
<%@include file="../common/common_vague.jsp" %>
<head>
    <title>simple world</title>
    <link type="text/css" rel="stylesheet" href="${BasePath}/ui/lte/css/datatables/dataTables.bootstrap.css"></link>
    <script type="text/javascript" scr="${BasePath}/ui/lte/js/plugins/datatables/dataTables.bootstrap.js"></script>
    <script type="text/javascript" scr="${BasePath}/ui/lte/js/plugins/datatables/jquery.dataTables.js"></script>
    <script type="text/javascript" scr="${BasePath}/ui/assets/plugins/bootstrap-modal/js/bootstrap-modal.js"></script>
</head>
<body>
    <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">Hover Data Table</h3>
                  <p style="float: right;">
                  	<button class="btn btn-default" type="submit" onclick="addUser()">Add</button>
                  </p>
                  
                </div><!-- /.box-header -->
                <div class="box-body">
                  <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap"><div class="row"><div class="col-sm-6"></div><div class="col-sm-6"></div></div><div class="row"><div class="col-sm-12"><table id="example2" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="example2_info">
                    <thead>
                      <tr role="row">
                      <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending">序号</th>
                      <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >姓名</th>
                      <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >登录名(s)</th>
                      <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >邮箱</th>
                      <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" >用户类型</th></tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.results}" var="user">
                      <tr role="row" class="odd">
                        <td class="sorting_1">Gecko</td>
                        <td>Firefox 1.0</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td>1.7</td>
                        <td>A</td>
                      </tr>
                      <tr role="row" class="even">
                        <td class="sorting_1">Gecko</td>
                        <td>Firefox 1.5</td>
                        <td>Win 98+ / OSX.2+</td>
                        <td>1.8</td>
                        <td>A</td>
                      </tr>
                    </c:forEach>
                     
                    </tbody>
                    <tfoot>
                      <tr><th rowspan="1" colspan="1">Rendering engine</th>
                      <th rowspan="1" colspan="1">Browser</th>
                      <th rowspan="1" colspan="1">Platform(s)</th>
                      <th rowspan="1" colspan="1">Engine version</th>
                      <th rowspan="1" colspan="1">CSS grade</th></tr>
                    </tfoot>
                  </table></div></div>
                  <div class="row">
                  <div class="col-sm-5">
                  <div class="dataTables_info" id="example2_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div></div>
                  <div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                  <ul class="pagination">
	                  <li class="paginate_button previous disabled" id="example2_previous"><a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0">Previous</a></li>
	                  <li class="paginate_button active"><a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
	                  <li class="paginate_button "><a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0">2</a></li>
	                  <li class="paginate_button "><a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0">3</a></li>
	                  <li class="paginate_button "><a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0">4</a></li>
	                  <li class="paginate_button "><a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0">5</a></li>
	                  <li class="paginate_button "><a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0">6</a></li>
	                  <li class="paginate_button next" id="example2_next"><a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0">Next</a></li>
                  </ul>
                  </div>
                  </div>
                  </div>
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->

            </div><!-- /.col -->
          </div>
          
          
          <div id="addUser" class="modal fade">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">Modal title</h4>
			      </div>
			      <div class="modal-body">
			      <form action="${BasePath}/user/save" method="post" id="submitUser">
			          <div class="form-group">
					    <label for="loginName">name</label>
					    <input type="text" class="form-control" id="loginName" name="loginName" placeholder="Login">
				  	  </div>
					  <div class="form-group">
						    <label for="password">password</label>
						    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
					  </div>
					  <div class="form-group">
					    <label for="email">email</label>
					    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
				  	  </div>
				  	  <div class="form-group">
					    <label for="birthday">birthday</label>
					    <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Birthday">
				  	  </div>
				   </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-primary" onclick="saveUser()">Save</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div>
		<script type="text/javascript">
			function addUser(){
				$("#addUser").modal();
			}
			function saveUser(){
				$("#submitUser").submit();
				//window.location.href = basePath+"/user/list";
			}
		</script>
</body>

</html>