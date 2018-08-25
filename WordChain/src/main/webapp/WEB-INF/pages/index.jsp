<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring boot TEST</title>
    <link rel="stylesheet" href="resources\css\materialize.css">
        <link rel="stylesheet" href="resources\css\custom.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="section no-pad-bot">
    <div class="container">
        <h2 class="header center materialize-red-text">Word Chain Finder</h2>
        <div class="row center">
            <div class="input-field col s6">
                <input id="startWord" type="text" class="validate">
                <label for="startWord">Start Word</label>
            </div>
            <div class="input-field col s6">
                <input id="endWord" type="text" class="validate">
                <label for="endWord">End Word</label>
            </div>
        </div>
        <div class="row center">
            <a id="find" class="waves-effect waves-light btn-large materialize-red">Find Path</a>
        </div>
        <div class="row center">
            <div id="loader" class="preloader-wrapper active">
                <div class="spinner-layer spinner-red-only">
                    <div class="circle-clipper left">
                    <div class="circle"></div>
                    </div><div class="gap-patch">
                    <div class="circle"></div>
                    </div><div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
            <p id="result"></p>
        </div>
    </div>
</div>
<script type="text/javascript" src="resources\js\index.js"></script>
<script type="text/javascript" src="resources\js\materialize.min.js"></script>
</body>
</html>