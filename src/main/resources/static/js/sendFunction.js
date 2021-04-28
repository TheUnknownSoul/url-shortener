$(document).ready(function (){
   $("button").click(function (){
       var str = $('#urlinput').val();
       alert('Long url has been sent: ' + str)

       //похоже что код ниже не отрабатывает
       //long url has`t been present in response code
       $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/shortenurl',
           longUrl: str,
           contentType: "application/json; charset = utf - 8",
           success: function (shortUrl){
               $("#shorturltext").val(ShortenerService.shortUrl)
           }
       })
   })
});
//
// // $(document).ready(function() {
// //     $("button").click(function() {
// //         $.ajax({
// //             type : 'POST',
// //             url : 'http://localhost:8080/shortenurl',
// //             data : JSON.stringify({
// //                 "longUrl" : $("#urlinput").val()
// //             }),
// //             contentType : "application/json; charset=utf-8",
// //             success : function(data) {
// //                 $("#shorturltext").val(data.shortUrl);
// //             }
// //         });
// //     });
// // });