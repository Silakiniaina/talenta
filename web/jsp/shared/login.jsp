<%
    String role = (String)request.getAttribute("role");
    String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Login</title>
  <link rel="stylesheet" href="assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
  <link rel="stylesheet" href="assets/css/style.css">
  <link rel="shortcut icon" href="assets/images/favicon.png" />
</head>
  <body>
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper full-page-wrapper">
          <div class="content-wrapper d-flex align-items-center auth px-0">
            <div class="row w-100 mx-0">
              <div class="col-lg-4 mx-auto">
                <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                  <div class="brand-logo">
                    <img src="assets/images/logo.svg" alt="logo">
                  </div>
                  <p class="text-danger"><%= error != null ? error : "" %></p>
                  <h6 class="font-weight-light">Sign in to continue.</h6>
                  <form class="pt-3" action="login" method="post">
                    <input type="hidden" name="role" value="<%= role %>">
                    <div class="form-group">
                      <input type="email" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="mt-3">
                      <a class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" href="login">Sign In</a>
                    </div>
                    <div class="text-center mt-4 font-weight-light">
                      Don't have an account? <a href="register.html" class="text-primary">Create</a>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
        </div>
        <!-- page-body-wrapper ends -->
      </div>
    <%@ include file="script.jsp" %>
  </body>
</html>