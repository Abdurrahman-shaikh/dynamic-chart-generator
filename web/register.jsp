<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        /* Custom styles here */
        .mainframe {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .border-container {
            border: 2px solid #000;
            padding: 20px;
            border-radius: 10px;
        }
    </style>
</head>
<body style="background-color: #0dcaf0">
    <div class="container">
        <div class="mainframe">
            <div class="row">
                <div class="col-lg-6">
                    <div class="border-container">
                        <div class="reg text-right">
                            <form action="fc?type=model&page=RegistrationModel" method="POST">
                                <h1>Register</h1>
                                <input class="form-control mt-4" required type="text" placeholder="Mobile" name="mobileno" id="mb" />
                                <span class="check" id="msid"></span>
                                <input class="form-control mt-3" required type="email" name="email" placeholder="Email" id="em"/>
                                <span class="check" id="sid"></span>
                                <input class="form-control mt-3" required type="password" name="pass" placeholder="Password"/>
                                <input class="form-control mt-3" required type="text" placeholder="firstname" name="fname" />
                                <input class="form-control mt-3" required type="text" placeholder="lastname" name="lname" />
                                <div class="mb-3">
                                    Gender:
                                    <input type="radio" name="gender" value="Male"/>Male
                                    <input type="radio" name="gender" value="Female"/>Female
                                </div>
                                <input class="form-control mt-3" type="date" name="dob" />
                                <button class="btn btn-primary mt-3" type="submit">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="border-container">
                        <div class="log text-right">
                            <form action="fc/?type=model&page=LoginModel" method="POST">
                                <h1>Login</h1>
                                <div class="lid">
                                    <input class="form-control mt-4" type="email" name="email" placeholder="Email"/>
                                </div>
                                <div class="lid">
                                    <input class="form-control mt-3" type="password" name="pass" placeholder="Password"/>
                                </div>
                                <div class="lid">
                                    <input class="btn btn-primary mt-3" type="submit" value="Login"/>
                                </div>
                                <a href="${pageContext.request.contextPath}/WEB-INF/page/adminLogin.jsp" class="mt-3 d-block">Admin Login</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/register-async.js" type="text/javascript"></script>
</body>
</html>
