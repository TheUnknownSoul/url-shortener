$(document).ready(function () {
    $("#button").click(async function () {
        let data = JSON.stringify({
            "longUrl": $("#urlinput").val()
        });

        let response = await fetch("http://localhost:8080/shortenurl", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        let shortUrl = await response.text();
        console.log(shortUrl)
        $("#shorturltext").val(data.shortUrl);
    })
})