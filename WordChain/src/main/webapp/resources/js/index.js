$(document).ready(function() {
    $("#loader").hide();

    $("#find").click(function(event) {
        $("#loader").show();
        $("#result").text("");
        getResult();

    });
});

function getResult() {
    var startWord = $("#startWord").val();
    var endWord = $("#endWord").val();

    $.ajax({
        type: "GET",
        url: "/wordChain",
        data: "startWord="+startWord+"&endWord="+endWord,
        success: function(data){
            var result = "";
            if(data.chain.length == 0) {
                result = "No Result";
            } else {
                result = data.chain.join(" - ");
            }
           $("#result").text(result);
           $("#loader").hide();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var content = "<p>"+jqXHR.responseText+"</p>";
            var content = content + "<img src='https://http.cat/"+jqXHR.status+"' style='border: 1px solid #ddd; border-radius: 4px; padding: 5px; width: 80%;'>"
            $("#result").html(content);
            $("#loader").hide();
        }
    });
}