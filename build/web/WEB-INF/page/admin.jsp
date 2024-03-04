<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="card mb-4">
                <h1 class="card-header">Admin Login Successful!</h1>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="dropdown">Select Time Period:</label>
                            <select id="data" class="form-control">
                                <option value="pm">Select</option>
                                <option value="cm">Current month</option>
                                <option value="py">Previous month</option>
                                <option value="cy">This Year</option>
                            </select>
                            <a href="http://localhost:8080/mp/fc?type=model&page=LogOutModel">Log Out</a>
                        </div>
                        <div class="card mt-4">
                    <div class="card-body">
                <canvas id="myCanvas" width="760" height="500"></canvas>
            </div>
        </div>
        <script src="../secured-js/admin-async.js" type="text/javascript"></script>
    </body>
</html>