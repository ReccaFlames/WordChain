$(document).ready(function() {
    $("#loader").hide();

    $("#find").click(function(event) {
        $("#loader").show();
        M.toast({html: 'Searching...', displayLength: 1000, classes: 'rounded'});
        $("#result").text("");
        getResult();

    });
});

function getResult() {
    var startWord = $("#startWord").val();
    var endWord = $("#endWord").val();

    $.ajax({
        url: "/wordChain?startWord="+startWord+"&endWord="+endWord
    }).then(function(data) {
       $("#result").text(data);
       $("#loader").hide();
    });
}