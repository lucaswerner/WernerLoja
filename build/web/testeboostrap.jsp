<%-- 
    Document   : testeboostrap
    Created on : 15/11/2015, 00:17:23
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-MfvZlkHCEqatNoGiOXveE8FIwMzZg4W85qfrfIFBfYc= sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
        <title>JSP Page</title>
        

        <style>
*{
    box-sizing:border-box;
    -moz-box-sizing:border-box;
    -webkit-box-sizing:border-box;
}

.inputBtnSection{
    display:inline-block;
    vertical-align:top;
    font-size:0;
    font-family:verdana;
}
.disableInputField{
    display:inline-block;
    vertical-align:top;
    height: 27px;
    margin: 0;
    font-size:14px;
    padding:0 3px;
}

.fileUpload {
	position: relative;
	overflow: hidden;
    border:solid 1px gray;
    display:inline-block;
    vertical-align:top;
}
.uploadBtn{
    display:inline-block;
    vertical-align:top;
    background:rgba(0,0,0,0.5);
    font-size:14px;
    padding:0 10px;
    height:25px;
    line-height:22px;
    color:#fff;
}

.fileUpload input.upload {
	position: absolute;
	top: 0;
	right: 0;
	margin: 0;
	padding: 0;
	font-size: 20px;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity=0);
}          
        </style>
            
    </head>
    <body>
        <h1>Hello World!</h1>

        <form class="form-horizontal">
            
              <div class="inputBtnSection">

<input id="uploadFile" class="disableInputField" placeholder="Choose File" disabled="disabled" />


<label class="fileUpload">
    <input id="uploadBtn" type="file" class="upload" />
    <span class="uploadBtn">Upload / Browse File ..</span>
</label>
    
    
    </div>
               
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Texto</label>
                <div class="col-sm-10">
                    <textarea class="form-control" ></textarea>
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>

           

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </div>
            </div>
        </form>

    </body>

</html>
            <script>
            
document.getElementById("uploadBtn").onchange = function () {
    document.getElementById("uploadFile").value = this.value;
};
        </script>